import {createNewDateStringForForm} from "./util.js";
import type {Challenge} from "models/challenge";
import type {CompletedHeistResponseOmitObjectIds} from "./models/completed-heist";
import type {ChallengeInstanceSubmitShape} from "./models/challenge-instance";

const challengeInstanceNewChallengeButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-new-challenge-instance-button") as HTMLButtonElement;

const challengeInstanceChallengeInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-challenge-input") as HTMLInputElement;

challengeInstanceChallengeInputEl.addEventListener("input", () => {
    challengeInstanceChallengeInputEl.title = challengeInstanceChallengeInputEl.value;
})

// challengeInstanceChallengeInputEl.append(createToolTip("Hover over the input box to see full contents."))


const challengesDataListEl: HTMLDataListElement =
    document.getElementById("challenge-data-list") as HTMLDataListElement;

const challengeInstanceIsCompletedInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-is-completed-input") as HTMLInputElement;

const challengeInstanceNotesInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-notes-input") as HTMLInputElement;

// ------------
// Time buttons
// ------------
const challengeInstanceDateTimeCompletedInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-completed-at-input") as HTMLInputElement;

const challengeInstanceDateTimeCompletedResetTimeButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-complete-at-reset-time-button") as
        HTMLButtonElement;

challengeInstanceDateTimeCompletedResetTimeButtonEl.addEventListener("click", setCompletedAndDateCompletedTime)

const challengeInstanceDateTimeCompletedInsertLatestCompletedHeistButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-completed-at-insert-time-latest-heist-completed-button") as
        HTMLButtonElement;

challengeInstanceDateTimeCompletedInsertLatestCompletedHeistButtonEl.addEventListener("click", setLatestHeistCompletedDateTime)


const challengeInstanceSubmitButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-submit-button") as HTMLButtonElement;

challengeInstanceSubmitButtonEl.addEventListener("click", submitChallengeInstance)


const challengeInstanceFormMessagePEl: HTMLParagraphElement =
    document.getElementById("challenge-instance-form-message") as HTMLParagraphElement;



/**
 * Map with the challenge name as the key, ID as its value.
 */
const challengeNameToId: Map<string, number> = new Map();

let formDateTimeComplete: string | undefined = undefined;

function setCompletedAndDateCompletedTime() {
    challengeInstanceDateTimeCompletedInputEl.value = createNewDateStringForForm();
    // TODO: store time for use if the user accidentally gets rid of, but wants to old time...? decide if this is a good idea
}


async function setLatestHeistCompletedDateTime() {
    const response = await fetch("/api/completed-heists/latest");

    const body: CompletedHeistResponseOmitObjectIds = await response.json();
    const dateTimeWithZ = new Date(body.completedAt).toISOString();
    const correctDateTime = dateTimeWithZ.replace("Z", "");

    if (challengeInstanceDateTimeCompletedInputEl.value == correctDateTime)
    {
        return;
    }
    challengeInstanceDateTimeCompletedInputEl.value = correctDateTime;
}


async function submitChallengeInstance() {
    if(!isFormValid()){
        return;
    }
    challengeInstanceFormMessagePEl.textContent = "";

    const challengeId: number = challengeNameToId.get(challengeInstanceChallengeInputEl.value)!;
    const completed: boolean = challengeInstanceIsCompletedInputEl.checked;
    const completedAt: string | null = challengeInstanceDateTimeCompletedInputEl.value ? challengeInstanceDateTimeCompletedInputEl.value : null;
    const notes: string = challengeInstanceNotesInputEl.value;

    const newChallengeInstance: ChallengeInstanceSubmitShape = {
        challengeId: challengeId, isCompleted: completed, completedAt: completedAt, notes: notes
    }

    const response = await fetch("/api/challenge-instance/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newChallengeInstance)
    })

    const body = await response.json();

    if (!response.ok) {
        challengeInstanceFormMessagePEl.textContent = `${response.status}: ${body.message}`
    }
}

function isFormValid(): boolean {
    challengeInstanceFormMessagePEl.textContent = "";
    let valid = true;

    if (!challengeInstanceChallengeInputEl.value) {
        valid = false;
        appendToFormMessage("Challenge can't be empty.")
    }

    if (challengeInstanceChallengeInputEl.value && !challengeNameToId.get(challengeInstanceChallengeInputEl.value)) {
        valid = false;
        appendToFormMessage("That challenge doesn't exist, make sure its spelt correctly.")
    }

    // !! converts to a bool, it's opposite state, then back to its current state.
    // if (challengeInstanceIsCompleteInputEl.checked !== !!challengeInstanceDateTimeCompletedInputEl.value) {
    //     valid = false;
    //     appendToFormMessage("'Completed' checkbox ")
    // }

    // Checked is true, datetime is falsy
    const test = challengeInstanceDateTimeCompletedInputEl.value
    if (challengeInstanceIsCompletedInputEl.checked && !challengeInstanceDateTimeCompletedInputEl.value)
    {
        valid = false;
        appendToFormMessage("A date & time need to be entered if the challenge was completed.")
    }

    // Checked is false, datetime is truthy
    if (!challengeInstanceIsCompletedInputEl.checked && challengeInstanceDateTimeCompletedInputEl.value)
    {
        valid = false;
        appendToFormMessage("The challenge needs to be marked as completed if the date & time was entered.")
    }

    return valid;
}

function appendToFormMessage(message: string) {

    message = message.at(-1) == "." ? message : `${message}.` // Add full stop if not one.

    const span = document.createElement("span");
    span.textContent = message;
    span.style.color = "red";

    // Append the new message while keeping old content
    challengeInstanceFormMessagePEl.appendChild(span);
    challengeInstanceFormMessagePEl.appendChild(document.createTextNode(' ')); // add space between messages
}


// Setup

async function populateChallengesList() {
    const response = await fetch("/api/challenges")
    const challenges: Challenge[] = await response.json();

    for (const challenge of challenges) {
        const optionEl = document.createElement("option");
        optionEl.value = challenge.challenge;
        challengesDataListEl.append(optionEl);

        challengeNameToId.set(challenge.challenge, challenge.id);
    }
}

await populateChallengesList()
