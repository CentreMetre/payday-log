import type {CompletedHeist} from "./models/completed-heist.js";
import type {Heist} from "./models/heist.js";
import {
    completedHeistDefaultHeaderNames,
    completedHeistDefaultRowShapeExample,
    CompletedHeistTable
} from "./completed-heist-table.js";
import {CreateNewDateStringForForm, createToolTip} from "./util.js";

/**
 * Used to keep a relation between a heist name and its ID since HTML Datalist items can't.
 */
const heistNameToId: Map<string, number> = new Map();

function prepData() {

}

type HeistFormDefaults = Omit<CompletedHeist, 'id' | 'heistId' | 'difficultyName' | 'completedAt' | 'xpAmount' | 'heistFinishStateName'> & {
    completedAt: () => string;
    xpAmount: string; //string to allow default to be "", will be converted back to number later at submission
}

const formDefaults: HeistFormDefaults = {
    xpAmount: "",
    accurateXpAmount: true,
    heistName: "",
    completedAt: CreateNewDateStringForForm,
    heistSuccess: true,
    heistFinishStateId: 6,
    allBagsSecured: false,
    difficultyId: 4, //Difficulty ID
    notes: ""
}


const devFormDefaults: HeistFormDefaults = {
    xpAmount: "",
    accurateXpAmount: true,
    heistName: "Road Rage",
    completedAt: CreateNewDateStringForForm,
    heistSuccess: true,
    heistFinishStateId: 6,
    allBagsSecured: false,
    difficultyId: 4, //Difficulty ID
    notes: "Hi"
}

const heistInstanceCreateFormEl: HTMLFormElement = document.getElementById("heist-instance-create-form") as HTMLFormElement // Have to have `as HTMLFormElement` or it won't work

const newHeistInstanceButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-new-heist-button") as HTMLButtonElement;

const heistNameInputEl: HTMLInputElement =
    document.getElementById("heist-instance-heist-name-input") as HTMLInputElement;

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

const allBagsSecuredInputEl: HTMLInputElement =
    document.getElementById("heist-instance-all-bags-secured-stealth-input") as HTMLInputElement;

const difficultySelectEl: HTMLSelectElement =
    document.getElementById("heist-instance-difficulty-select") as HTMLSelectElement;

const notesInputEl: HTMLTextAreaElement =
    document.getElementById("heist-instance-notes-input") as HTMLTextAreaElement;

const submitButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-submit-button") as HTMLButtonElement;

const forceSubmitButtonEl: HTMLButtonElement =
    document.getElementById("heist-instance-force-submit-button") as HTMLButtonElement;

const formMessage: HTMLParagraphElement =
    document.getElementById("heist-instance-form-message") as HTMLParagraphElement;

populateHeistList()

newHeistInstanceButtonEl.addEventListener('click', () => newForm());

submitButtonEl.addEventListener('click', submitForm)
forceSubmitButtonEl.addEventListener('click', forceSubmit)

const heistCompleteElements: (HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement)[] = [
    heistNameInputEl,
    xpAmountInputEl,
    accurateXpInputEl,
    completedAtInputEl,
    successfulInputEl,
    allBagsSecuredInputEl,
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
    formMessage.textContent = "";
    forceSubmitButtonEl.hidden = true;
}

function resetFormWithDefaults(defaults: HeistFormDefaults) {
    heistNameInputEl.value = defaults.heistName;
    xpAmountInputEl.value = defaults.xpAmount;
    accurateXpInputEl.checked = defaults.accurateXpAmount;
    completedAtInputEl.value = defaults.completedAt();
    successfulInputEl.checked = defaults.heistSuccess;
    finishStateSelectEl.value = defaults.heistFinishStateId.toString();
    allBagsSecuredInputEl.checked = defaults.allBagsSecured;
    difficultySelectEl.value = defaults.difficultyId.toString();
    notesInputEl.value = defaults.notes;
}

type CompletedHeistSubmit = Omit<CompletedHeist, 'id' | 'heistName' | 'difficultyName' | 'completedAt' | 'heistFinishStateName'> & {
    completedAt: string;
}

async function submitForm() {
    if (!validateForm()) {return}

    const heistCompletedData: CompletedHeistSubmit = getFormData();

    formStateDisabled(true);

    const response = await fetch("/api/completed-heists/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(heistCompletedData)
    })

    const body = await response.json();

}

async function forceSubmit() {
    debugger;
    forceSubmitButtonEl.hidden = true;
    const heistCompletedData = getFormData();

    formStateDisabled(true);

    const response = await fetch("/api/completed-heists/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(heistCompletedData)
    })

    const body = await response.json();
}

function getFormData() {
    const heistCompletedData: CompletedHeistSubmit = {
        xpAmount: parseInt(xpAmountInputEl.value),
        accurateXpAmount: accurateXpInputEl.checked,
        heistId: heistNameToId.get(heistNameInputEl.value)!, //OK to force since it's checked in validateForm.
        completedAt: completedAtInputEl.value,
        heistSuccess: successfulInputEl.checked,
        heistFinishStateId: parseInt(finishStateSelectEl.value),
        allBagsSecured: allBagsSecuredInputEl.checked,
        difficultyId: parseInt(difficultySelectEl.value),
        notes: notesInputEl.value
    }
    return heistCompletedData;
}

function validateForm() {
    // debugger;
    forceSubmitButtonEl.hidden = true;
    let valid = true;

    // Set to false if one of the if bodies makes it false, then it cant be forced. Should be set back to true in the if bodies.
    let forceable: boolean = true;
    formMessage.textContent = ""

    if (!heistNameInputEl.value) {
        appendToFormMessage("Heist name can't be empty.", false)
        valid = false;
        forceable = false;
    }

    if (heistNameInputEl.value && !heistNameToId.get(heistNameInputEl.value))
    {
        appendToFormMessage(`Heist ID not found for ${heistNameInputEl.value}`, false)
        valid = false;
        forceable = false;
    }

    if (!xpAmountInputEl.value) {
        appendToFormMessage("XP Amount can't be empty.", false)
        valid = false;
        forceable = false;
    }

    const xpAmount: number = Number(xpAmountInputEl.value);

    if (xpAmountInputEl.value && xpAmount === 0) {
        appendToFormMessage("XP Amount is 0.", true)
        valid = false;
    }

    if (xpAmount > 4000) { // The highest so far is 3710 this is a safeguard against accidental inputs.
        appendToFormMessage(`XP Amount is high at ${xpAmount}.`, true)
        valid = false;
    }

    if (xpAmount < 0) {
        appendToFormMessage("XP Amount cannot be lower than 0.", false)
        valid = false;
        forceable = false;
    }

    formMessage.appendChild(createToolTip("Reds = invalid. Yellow = forceable but not advised, there also has to be no reds (invalids)."))

    if (valid) {
        return true;
    }
    if (forceable) {
        forceSubmitButtonEl.hidden = false;
        return false;
    }
}

/**
 *
 * @param message
 * @param forceable If the invalid data can be forced. Turns the text yellow if true, red it not.
 */
function appendToFormMessage(message: string, forceable: boolean) {

    message = message.at(-1) == "." ? message : `${message}.` // Add full stop if not one.

    const span = document.createElement("span");
    span.textContent = message;

    span.style.color = forceable ? "hsl(50, 90%, 50%)" : "red"
    span.style.display = "inline"

    // Append the new message while keeping old content
    formMessage.appendChild(span);
    formMessage.appendChild(document.createTextNode(' ')); // add space between messages
}

/**
 * Populates input heist list.
 */
async function populateHeistList() {
    const response = await fetch("/api/heists");
    const heists: Heist[] = await response.json();
    for (const heist of heists) {
        const optionEl = document.createElement("option");
        optionEl.value = heist.name;
        heistsDatalistEl.append(optionEl);

        heistNameToId.set(heist.name, heist.id);
    }
}

formStateDisabled(true);

function formStateDisabled(isDisabled: boolean): void {
    heistNameInputEl.disabled = isDisabled;
    xpAmountInputEl.disabled = isDisabled;
    accurateXpInputEl.disabled = isDisabled;
    completedAtInputEl.disabled = isDisabled;
    successfulInputEl.disabled = isDisabled;
    allBagsSecuredInputEl.disabled = isDisabled;
    difficultySelectEl.disabled = isDisabled;
    notesInputEl.disabled = isDisabled;
    finishStateSelectEl.disabled = isDisabled;
    submitButtonEl.disabled = isDisabled;
}

const tableContainerDivEl: HTMLDivElement =
    document.getElementById("completed-heist-output-table-outer") as HTMLDivElement;

// const tableMaster = new CompletedHeistTable();
// tableContainerDivEl.append(tableMaster.getTable())
// tableMaster.setLatest()

const table = new CompletedHeistTable(completedHeistDefaultRowShapeExample, completedHeistDefaultHeaderNames);

tableContainerDivEl.appendChild(table.getTable());

const getLatestButton: HTMLButtonElement =
    document.getElementById("completed-heist-get-latest") as HTMLButtonElement;

getLatestButton.addEventListener('click', async () => {
    table.appendRow(await table.fetchLatestRow())});