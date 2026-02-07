import {createNewDateStringForForm} from "./util.js";
import type {Challenge} from "models/challenge";
import type {CompletedHeistResponseOmitObjectIds} from "./models/completed-heist";

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

const challengeInstanceIsCompleteInputEl: HTMLInputElement =
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
    // challengeInstanceIsCompleteInputEl.checked = true;
    // challengeInstanceDateTimeCompletedInputEl.value = createNewDateStringForForm();
}

function validateFormData(): boolean {
    let valid = true;



    return false;
}

async function setLatestHeistCompletedDateTime() {
    debugger
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


function submitChallengeInstance() {
    validateFormData()
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
