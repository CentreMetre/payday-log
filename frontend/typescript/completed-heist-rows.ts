import type { CompletedHeist } from "./models/completed-heist";

const tableHeader = `
    <thead>
        <tr>
            <th>ID</th>
            <th>Heist Name</th>
            <th>XP Amount</th>
            <th>Accurate XP?</th>
            <th>Completed At</th>
            <th>Difficulty</th>
            <th>Heist Success?</th>
            <th>Finish State</th>
            <th>Majority Played Stealth?</th>
            <th>Notes</th>
        </tr>
    </thead>
`;

const table: HTMLTableElement = document.createElement("table");
table.insertAdjacentHTML('afterbegin', tableHeader);

type rowShape = Omit<CompletedHeist, 'heistFinishStateId' | 'heistId' | 'difficultyId' | 'completedAt'> & {
    completedAt: string; // Since the completedAt is sent in JSON as "2026-01-23T23:17:30.183",
    // it needs to be a string type here, not a date type.
    // Converting it to a date would make it a human-readable Date string, but I want it as an ISO string.

}

const getLatestCompletedHeistButtonEl: HTMLButtonElement =
    document.getElementById("get-latest-button") as HTMLButtonElement;

// getLatestCompletedHeistButtonEl.addEventListener("click", async () => appendRowToTable(await fetchLatestRow()))

export function appendRowToTable(rowData: rowShape) {
    const row = document.createElement('tr')

    const idTdEl = td();
    idTdEl.textContent = rowData.id.toString();
    idTdEl.className = "row-id";

    const heistNameTdEl = td();
    heistNameTdEl.textContent = rowData.heistName;

    const xpTdEl = td();
    xpTdEl.textContent = rowData.xpAmount.toString();

    const isXpAccurateTdEl = createBooleanCell(rowData.accurateXpAmount);

    // Because the date is returned in JSON as "2026-01-23T23:17:30.183" the T needs to be removed.
    const completedAtTdEl = td();
    const dateParts = rowData.completedAt.split('T');
    completedAtTdEl.textContent = `${dateParts[0]} ${dateParts[1]}`;

    const difficultyTdEl = td();
    difficultyTdEl.textContent = rowData.difficultyName;

    const isHeistSuccessTdEl = createBooleanCell(rowData.heistSuccess);

    const finishStateTdEl = td();
    finishStateTdEl.textContent = rowData.heistFinishStateName;

    const isMajorityPlayedStealthTdEl = createBooleanCell(rowData.majorityStatePlayedStealth);

    const notesTdEl = td();
    notesTdEl.textContent = rowData.notes;


    row.append(
        idTdEl,
        heistNameTdEl,
        xpTdEl,
        isXpAccurateTdEl,
        completedAtTdEl,
        difficultyTdEl,
        isHeistSuccessTdEl,
        finishStateTdEl,
        isMajorityPlayedStealthTdEl,
        notesTdEl
    )

    table.append(row)

    document.body.append(table)
}

/**
 * Util function to create td element.
 */
function td(): HTMLTableCellElement {
    return document.createElement('td')
}

/**
 * Creates a boolean cell with the class needed and sets its textContent based of value.
 * @param value Used to set the textContent.
 */
function createBooleanCell(value: boolean): HTMLTableCellElement {

    const element = td()

    element.className = "table-cell-boolean"

    let flag: boolean;
    if (value) {
        element.textContent = "✔"
    }
    else {
        element.textContent =  "✖"
    }

    return element;
}

export async function fetchLatestRow(): Promise<rowShape> {
    const response = await fetch("/api/completed-heists/latest")
    return await response.json()
}

/**
 * Create a table in an element.
 * @param divEl The element to put the table in.
 */
export function setupRowDisplay(divEl: HTMLDivElement) {
    divEl.append(table)
}