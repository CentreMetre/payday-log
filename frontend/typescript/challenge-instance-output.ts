import {ChallengeInstanceTable, challengeInstanceDefaultHeaderNames, challengeInstanceDefaultRowShapeExample} from
        "./challenge-instance-table.js"

// ========
//  Table
// ========
const tableContainerDivEl: HTMLDivElement =
    document.getElementById("challenge-instance-output-table-outer") as HTMLDivElement;

const table = new ChallengeInstanceTable(challengeInstanceDefaultRowShapeExample, challengeInstanceDefaultHeaderNames);

tableContainerDivEl.appendChild(table.getMessageElement()!);
tableContainerDivEl.appendChild(table.getTable());

//
//
//

const getTodayButton: HTMLButtonElement = document.getElementById("challenge-instance-get-today") as HTMLButtonElement;

getTodayButton.addEventListener("click", async () => {
    const data = await table.fetchToday();
    data.length === 0 ? table.setMessage("No date received from today.") : table.appendRows(data)
});