import type { CompletedHeist } from "./completed-heist";

// type CompletedHeist = {
//     xpAmount: string;
//     accurateXpAmount: boolean;
//     heistName: string;
//     heistId: number;
//     completedAt: Date;
//     heistSuccess: boolean;
//     heistFinishState: number;
//     majorityStatePlayedStealth: boolean;
//     difficulty: number;
//     notes: string;
// }

// const defaults: CompletedHeist = {
//     xpAmount: "",
//     accurateXpAmount: true,
//     heistName: "",
//     heistId: 0, //0 since there is no heist id of 0
//     completedAt: new Date(),
//     heistSuccess: true,
//     heistFinishState: 3,
//     majorityStatePlayedStealth: false,
//     difficulty: 4, //Difficulty ID
//     notes: ""
// }

// type heistDefaults = {
//
// }

const heistInstanceCreateFormEl: HTMLFormElement = document.getElementById("heist-instance-create-form") as HTMLFormElement // Have to have `as HTMLFormElement` or it won't work

const newHeistInstanceButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-new-heist-button") as HTMLButtonElement;

const heistNameInputEl: HTMLInputElement =
    document.getElementById("heist-instance-name-input") as HTMLInputElement;

const xpAmountInputEl: HTMLInputElement =
    document.getElementById("heist-instance-xp-amount-input") as HTMLInputElement;

const accurateXpInputEl: HTMLInputElement =
    document.getElementById("heist-instance-accurate-xp-amount-input") as HTMLInputElement;

const completedAtInputEl: HTMLInputElement =
    document.getElementById("heist-instance-completed-at-input") as HTMLInputElement;

const successfulInputEl: HTMLInputElement =
    document.getElementById("heist-instance-successful-input") as HTMLInputElement;

const majorityStealthInputEl: HTMLInputElement =
    document.getElementById("heist-instance-majority-played-stealth-input") as HTMLInputElement;

const difficultySelectEl: HTMLSelectElement =
    document.getElementById("heist-instance-difficulty-select") as HTMLSelectElement;

const notesInputEl: HTMLTextAreaElement =
    document.getElementById("heist-instance-notes-input") as HTMLTextAreaElement;

const submitButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-submit-button") as HTMLButtonElement;

newHeistInstanceButtonEl.addEventListener('click', () => resetFormWithDefaults/*(defaults)*/);

submitButtonEl.addEventListener('click', submitForm)

function resetFormWithDefaults(defaults: CompletedHeist) {
    // heistNameInputEl.value = defaults.heistName;
    // xpAmountInputEl.value = defaults.xpAmount;
    // accurateXpInputEl.checked = defaults.accurateXpAmount;
    // completedAtInputEl.valueAsDate = defaults.completedAt;
    // successfulInputEl.checked = defaults.heistSuccess;
    // majorityStealthInputEl.checked = defaults.majorityStatePlayedStealth;
    // difficultySelectEl.value = defaults.difficulty.toString();
    // notesInputEl.value = defaults.notes;
}

type CompletedHeistSubmit = Omit<CompletedHeist, 'id' | 'heistName' | 'difficultyName'>

function submitForm() {
    const heistCompletedData: CompletedHeistSubmit = {
        xpAmount: Number(xpAmountInputEl.value),
        accurateXpAmount: accurateXpInputEl.checked,
        heistId: 0,
        completedAt: new Date(),
        heistSuccess: true,
        heistFinishState: 3,
        majorityStatePlayedStealth: false,
        difficultyId: 4,
        notes: ""
    }
    // const response = await fetch(`/api/heists/create`, {
    //     method: 'POST',
    // })
}