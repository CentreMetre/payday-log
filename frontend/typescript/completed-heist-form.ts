import type { CompletedHeist } from "./completed-heist";
import type { Heist } from "./heist";

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

type HeistFormDefaults = Omit<CompletedHeist, 'id' | 'heistId' | 'difficultyName' | 'completedAt'> & {
    completedAt: () => string;
}

const formDefaults: HeistFormDefaults = {
    xpAmount: "",
    accurateXpAmount: true,
    heistName: "",
    completedAt: CreateNewDateStringForForm,
    heistSuccess: true,
    heistFinishState: "6",
    majorityStatePlayedStealth: false,
    difficultyId: "4", //Difficulty ID
    notes: ""
}

// type heistDefaults = {
//
// }

const heistInstanceCreateFormEl: HTMLFormElement = document.getElementById("heist-instance-create-form") as HTMLFormElement // Have to have `as HTMLFormElement` or it won't work

const newHeistInstanceButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-new-heist-button") as HTMLButtonElement;

const heistNameInputEl: HTMLInputElement =
    document.getElementById("heist-instance-heist-name-input") as HTMLInputElement;

const heistIdInputEl: HTMLInputElement =
    document.getElementById("heist-instance-heist-id-input") as HTMLInputElement;

const heistsDatalistEl: HTMLDataListElement =
    document.getElementById("heist-instance-heist-data-list") as HTMLDataListElement;

const xpAmountInputEl: HTMLInputElement =
    document.getElementById("heist-instance-xp-amount-input") as HTMLInputElement;

const accurateXpInputEl: HTMLInputElement =
    document.getElementById("heist-instance-accurate-xp-amount-input") as HTMLInputElement;

const completedAtInputEl: HTMLInputElement =
    document.getElementById("heist-instance-completed-at-input") as HTMLInputElement;

const successfulInputEl: HTMLInputElement =
    document.getElementById("heist-instance-successful-input") as HTMLInputElement;

const finishStateSelectEl: HTMLSelectElement =
    document.getElementById("heist-instance-finish-state-select") as HTMLSelectElement;

const majorityStealthInputEl: HTMLInputElement =
    document.getElementById("heist-instance-majority-played-stealth-input") as HTMLInputElement;

const difficultySelectEl: HTMLSelectElement =
    document.getElementById("heist-instance-difficulty-select") as HTMLSelectElement;

const notesInputEl: HTMLTextAreaElement =
    document.getElementById("heist-instance-notes-input") as HTMLTextAreaElement;

const submitButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-submit-button") as HTMLButtonElement;

newHeistInstanceButtonEl.addEventListener('click', () => newForm());

submitButtonEl.addEventListener('click', submitForm)

const heistCompleteElements: (HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement)[] = [
    heistNameInputEl,
    xpAmountInputEl,
    accurateXpInputEl,
    completedAtInputEl,
    successfulInputEl,
    majorityStealthInputEl,
    difficultySelectEl,
    notesInputEl
];

function newForm() {
    clearHeistCompletedForm()
    resetFormWithDefaults(formDefaults);
    formStateDisabled(false);
}

function clearHeistCompletedForm() {
    for (const element of heistCompleteElements) {
        // Reset value if it exists
        if ('defaultValue' in element) {
            element.value = element.defaultValue;
        }
        // Reset checked if it exists
        if ('defaultChecked' in element) {
            element.checked = element.defaultChecked;
        }
    }
}

function resetFormWithDefaults(defaults: HeistFormDefaults) {
    heistNameInputEl.value = defaults.heistName;
    xpAmountInputEl.value = defaults.xpAmount.toString();
    accurateXpInputEl.checked = defaults.accurateXpAmount;
    completedAtInputEl.value = defaults.completedAt();
    successfulInputEl.checked = defaults.heistSuccess;
    finishStateSelectEl.value = defaults.heistFinishState;
    majorityStealthInputEl.checked = defaults.majorityStatePlayedStealth;
    difficultySelectEl.value = defaults.difficultyId;
    notesInputEl.value = defaults.notes;
}

type CompletedHeistSubmit = Omit<CompletedHeist, 'id' | 'heistName' | 'difficultyName'>
// TODO: Decide whether to have IDs be strings or numbers, on frontend and in transit (should be strings in frontend probably, but in transit ???)
function submitForm() {
    const heistCompletedData: CompletedHeistSubmit = {
        xpAmount: xpAmountInputEl.value,
        accurateXpAmount: accurateXpInputEl.checked,
        heistId: heistIdInputEl.value,
        completedAt: new Date(completedAtInputEl.value),
        heistSuccess: successfulInputEl.checked,
        heistFinishState: "6",
        majorityStatePlayedStealth: majorityStealthInputEl.checked,
        difficultyId: difficultySelectEl.value,
        notes: notesInputEl.value
    }
    // const response = await fetch(`/api/heists/create`, {
    //     method: 'POST',
    // })

    formStateDisabled(true);
}

function CreateNewDateStringForForm(): string {
    const date = new Date();

    const pad = (n: number, z = 2) => n.toString().padStart(z, "0");
    return (
        date.getFullYear() +
        "-" +
        pad(date.getMonth() + 1) +
        "-" +
        pad(date.getDate()) +
        "T" +
        pad(date.getHours()) +
        ":" +
        pad(date.getMinutes()) +
        ":" +
        pad(date.getSeconds()) +
        "." +
        pad(date.getMilliseconds(), 3)
    );
}

populateHeistList()

async function populateHeistList() {
    const response = await fetch("/api/heists");
    const heists: Heist[] = await response.json();
    for (const heist of heists) {
        const optionEl = document.createElement("option");
        optionEl.value = heist.name;
        heistsDatalistEl.append(optionEl)
    }
}

formStateDisabled(true);

function formStateDisabled(isDisabled: boolean): void {
    heistNameInputEl.disabled = isDisabled;
    xpAmountInputEl.disabled = isDisabled;
    accurateXpInputEl.disabled = isDisabled;
    completedAtInputEl.disabled = isDisabled;
    successfulInputEl.disabled = isDisabled;
    majorityStealthInputEl.disabled = isDisabled;
    difficultySelectEl.disabled = isDisabled;
    notesInputEl.disabled = isDisabled;
    submitButtonEl.disabled = isDisabled;
}

