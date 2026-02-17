/**
 * This file is for converting old challenges into: new challenges and new challenge instances
 * How this will be done:
 * The old challenges will be got from the challenge column of the old challenges in the challenge db.
 * They will be filtered so they are unique.
 * Then they will be mapped to new challenges that can then be saved.
 */

import type {ChallengeInstanceDefaultRowShape} from "../models/challenge-instance"

import {levenshtein, levenshteinClosest} from "./levenshtein.js"
import {Table} from "../abstract-table.js";
export type OldChallenge = ChallengeInstanceDefaultRowShape

const oldChallengesTableContainerDivEl: HTMLDivElement =
    document.getElementById("old-challenge-table-container") as HTMLDivElement;

const intermediateChallengeInstancesTableContainerDivEl: HTMLDivElement =
    document.getElementById("intermediate-challenge-instance-table-container") as HTMLDivElement;

const wikiChallenges: string[] = []

// Have 2 tables, one for old data, 1 for the new data

const oldChallengeInstances: OldChallenge[] = []

const oldChallenges: string[] = []

const oldChallengesUnique: string[] = []

/**
 * Challenges that are correct, not types or notes in the challenge text.
 */
const approvedChallenges: string[] = []

/**
 * Stores each old challenge to a new value it should be stored as.
 */
const oldToNewChallenges: Map<string, string | undefined> = new Map();

/**
 * String is the name of the typo challenge. Number is the index of approvedChallenges.
 */
const typosMap: Map<string, number> = new Map();

async function setOldChallengeInstances() {
    const response = await fetch("/api/challenge-instance/old/all");

    const body: OldChallenge[] = await response.json();

    let firstFifty = body.slice(0, 50);

    firstFifty.forEach(el => oldChallengeInstances.push(el))
    // body.forEach(el => oldChallengeInstances.push(el))
}

function removeDuplicates<T>(array: T[]): T[] {
    return Array.from(new Set(array)); // Converts to set then back to array for distinct values.
}

async function init() {
    const prepMessage = document.createElement("p")
    prepMessage.textContent = "Preparing data"
    oldChallengesTableContainerDivEl.appendChild(prepMessage)

    await setOldChallengeInstances();
    oldChallengeInstances.forEach(el => oldChallenges.push(el.challenge));
    removeDuplicates(oldChallenges).forEach(el => oldChallengesUnique.push(el));
    await setWikiChallenges();
    wikiChallenges.forEach(el => approvedChallenges.push(el));
    oldChallengesTableContainerDivEl.textContent = ""
    setTables()
}

async function setWikiChallenges() {
    const response = await fetch("wiki-daily-challenges-typos-fixed.json")
    const body = await response.json();
    for (const challenge of body) {
        wikiChallenges.push(challenge)
    }
    console.log(wikiChallenges)
}

type OldChallengesTableRowShape = {
    id: number;
    challenge: string;
    notes: string;
    levenshteinDistance: number;
    levenshteinNearest: string;
    actions: HTMLButtonElement[];
}

class OldChallengesTable extends Table<OldChallengesTableRowShape> {

    /**
     * Loads the CSS for tables into the html file for proper styling.
     */
    override loadCSS() {
        const link = document.createElement("link");
        link.rel = "stylesheet"
        link.href = "../css/prep-old-data/table.css"; // Relative path from JS folder.
        link.type = 'text/css';
        document.head.appendChild(link);
    }

    /**
     * Appends one more row to the current rows.
     * @param data Data to append as a row.
     */
    override appendRow(data: OldChallengesTableRowShape): void {
        // debugger;
        let backgroundColour = ""
        const oldChallenge: OldChallenge = oldChallengeInstances.find(item => item.id === data.id)!
        if (approvedChallenges.includes(data.challenge)) {
            // debugger;
            data.levenshteinDistance = levenshtein(data.challenge, approvedChallenges[approvedChallenges.indexOf(data.challenge)]!) // should be 0
            backgroundColour = "green"
            const zeroDistanceOutput = document.getElementById("no-distance-count")!;
            let currentCount = Number(zeroDistanceOutput!.textContent)
            zeroDistanceOutput.textContent = String(++currentCount)
            // classList.push("")

            const newIntermediateChallenge: IntermediateChallenge = oldChallenge;
            newIntermediateChallenge.isCompleted = oldChallenge.isCompleted;
            intermediateChallengeTable.appendRow(newIntermediateChallenge);
            // console.log(newIntermediateChallenge)
            // console.log(oldChallenge)
        }
        else {

            // Levenshtein
            const distances = levenshteinClosest(data.challenge, approvedChallenges)
            const distanceKeys = [...distances.keys()]
            data.levenshteinNearest = distanceKeys.join("\n\n")
            data.levenshteinNearest = `Amount: ${distanceKeys.length}\n\n${data.levenshteinNearest}`
            const firstValue = distances.values().next().value;
            const distance = firstValue !== undefined ? firstValue : -1;
            data.levenshteinDistance = distance;

            const oneToFiveOutput = document.getElementById("one-to-five-distance-count")!;
            const sixPlusOutput = document.getElementById("six-and-above-distance-count")!;

            if (distance > 5) {
                let currentCount = Number(oneToFiveOutput!.textContent)
                oneToFiveOutput.textContent = String(++currentCount)
            }
            else {
                let currentCount = Number(sixPlusOutput!.textContent)
                sixPlusOutput.textContent = String(++currentCount)
            }

            // Action buttons
            const setHighlightedAsNewButtonEl: HTMLButtonElement = document.createElement("button");
            setHighlightedAsNewButtonEl.addEventListener("click", () => {
                const highlighted = getHighlightedText();
                console.log(highlighted);
            })
            setHighlightedAsNewButtonEl.textContent = "Set Highlighted As New"
            data.actions.push(setHighlightedAsNewButtonEl)

            const approveButtonEl: HTMLButtonElement = document.createElement("button");
            approveButtonEl.addEventListener("click", () => {});
            approveButtonEl.textContent = "Approve This Challenge";
            data.actions.push(approveButtonEl);

            if (distanceKeys.length === 1) {
                const setAsClosestButtonEl: HTMLButtonElement = document.createElement("button");
                setAsClosestButtonEl.addEventListener("click", () => {})
                setAsClosestButtonEl.textContent = "Set As Closest"
                data.actions.push(setAsClosestButtonEl)
            }

            const undoButtonEl: HTMLButtonElement = document.createElement("button");
            undoButtonEl.addEventListener("click", () => {
                data.actions.forEach(el => el.disabled = true)})
            undoButtonEl.textContent = "Undo";
            undoButtonEl.disabled = true
            data.actions.push(undoButtonEl)

            backgroundColour = data.levenshteinDistance < 5 ? "yellow" : "red";
        }
        const row = this.createPopulatedRow(data)
        row.style.background = backgroundColour;
        row.dataset.id = data.id.toString();
        this.tableBody.appendChild(row)
    } // Do something like auto removing notes/anything after first full stop. Then a colour like purple for edited. Yellow for typos, red for no where near but no notes?
}

const oldChallengesTable = new OldChallengesTable({id: 0, challenge: "", notes: "", levenshteinDistance: 0, levenshteinNearest: "", actions: []},
    {id: "ID", challenge: "Challenge", notes: "Notes", levenshteinDistance: "Levenshtein Dist.", levenshteinNearest: "Nearest", actions: "Actions"})

// class NewChallengesInstancesTable extends Table<any>

function setTables() {
    oldChallengesTableContainerDivEl.appendChild(oldChallengesTable.getMessageElement()!)
    oldChallengesTableContainerDivEl.appendChild(oldChallengesTable.getTable())
    oldChallengeInstances.sort((a,b) => a.id - b.id)
    oldChallengeInstances.forEach(el => oldChallengesTable.appendRow({
        id: el.id,
        challenge: el.challenge,
        notes: el.notes,
        actions: [],
        levenshteinDistance: -1,
        levenshteinNearest: ""
    }))

    intermediateChallengeInstancesTableContainerDivEl.appendChild(intermediateChallengeTable.getMessageElement()!);
    intermediateChallengeInstancesTableContainerDivEl.appendChild(intermediateChallengeTable.getTable());
}

function getHighlightedText(): string {
    const selection = window.getSelection();
    return selection ? selection.toString() : '';
}

type IntermediateChallenge = ChallengeInstanceDefaultRowShape;
const intermediateChallengeExample: IntermediateChallenge = {
    id: 0,
    challenge: "",
    notes: "",
    completedAt: "",
    isCompleted: false
}
// const intermediateChallengeHeaders: Record<keyof IntermediateChallenge, string> = {
//     challenge: "", completedAt: "", id: "", isCompleted: "", notes: ""
// }

class IntermediateChallengeTable extends Table<IntermediateChallenge> {
}

const intermediateChallengeTable = new IntermediateChallengeTable(intermediateChallengeExample)

init();

