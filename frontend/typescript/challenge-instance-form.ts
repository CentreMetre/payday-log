import {createNewDateStringForForm} from "./util.js";
import type {Challenge} from "models/challenge";

const challengeInstanceNewChallengeButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-new-challenge-instance-button") as HTMLButtonElement;

const challengeInstanceChallengeInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-challenge-input") as HTMLInputElement;

const challengesDataListEl: HTMLDataListElement =
    document.getElementById("challenge-data-list") as HTMLDataListElement;

const challengeInstanceIsCompleteInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-is-completed-input") as HTMLInputElement;

const challengeInstanceNotesInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-notes-input") as HTMLInputElement;

const challengeInstanceDateTimeCompletedInputEl: HTMLInputElement =
    document.getElementById("challenge-instance-completed-at-input") as HTMLInputElement;

const challengeInstanceDateTimeCompletedInsertTimeButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-completed-at-insert-time-button") as HTMLButtonElement;

challengeInstanceDateTimeCompletedInsertTimeButtonEl.addEventListener("click", setCompletedAndDateCompletedTime)

const challengeInstanceDateTimeCompletedResetTimeButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-completed-at-reset-time-button") as HTMLButtonElement;


const challengeInstanceSubmitButtonEl: HTMLButtonElement =
    document.getElementById("challenge-instance-submit-button") as HTMLButtonElement;

/**
 * Map with the challenge name as the key, id as its value.
 */
const challengeNameToId: Map<string, number> = new Map();

let dateTime: string;

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

function setCompletedAndDateCompletedTime() {
    challengeInstanceIsCompleteInputEl.checked = true;
    challengeInstanceDateTimeCompletedInputEl.value = createNewDateStringForForm();
}

function validateFormData() {

}

if (!challengeInstanceDateTimeCompletedInputEl.value) {

    console.log(false)
}
