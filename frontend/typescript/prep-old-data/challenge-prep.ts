/**
 * This file is for converting old challenges into: new challenges and new challenge instances
 * How this will be done:
 * The old challenges will be got from the challenge column of the old challenges in the challenge db.
 * They will be filtered so they are unique.
 * Then they will be mapped to new challenges that can then be saved.
 */

import type {OldChallenge} from "./OldChallenge";

import {levenshtein} from "./levenshtein.js"
import {Table} from "../abstract-table.js";

const oldChallengesTableContainerDivEl: HTMLDivElement =
    document.getElementById("old-challenge-table-container") as HTMLDivElement;

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

    body.forEach(el => oldChallengeInstances.push(el))
}

function removeDuplicates<T>(array: T[]): T[] {
    return Array.from(new Set(array)); // Converts to set then back to array for distinct values.
}

async function init() {
    debugger
    await setOldChallengeInstances();
    oldChallengeInstances.forEach(el => oldChallenges.push(el.challenge));
    removeDuplicates(oldChallenges).forEach(el => oldChallengesUnique.push(el));
    await setWikiChallenges();
    wikiChallenges.forEach(el => approvedChallenges.push(el));
    setTable()
}

async function setWikiChallenges() {
    const response = await fetch("wiki-daily-challenges.json")
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
    actions: string;
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
        debugger;
        let backgroundColour = ""
        const classList: string[] = []
        if (approvedChallenges.includes(data.challenge)) {
            data.levenshteinDistance = levenshtein(data.challenge, approvedChallenges[approvedChallenges.indexOf(data.challenge)]!) // should be 0
            backgroundColour = "green"
            // classList.push("")
        }
        else {
            // data.levenshteinDistance = levenshtein(data.challenge) // should be 0
            backgroundColour = data.levenshteinDistance < 5 ? "yellow" : "red";
            // row.classList.add()
        }
        const row = this.createPopulatedRow(data)
        row.style.background = backgroundColour;
        this.tableBody.appendChild(row)
    } // Do something like auto removing notes/anything after first full stop. Then a colour like purple for edited. Yellow for typos, red for no where near but no notes?
}

const oldChallengesTable = new OldChallengesTable({id: 0, challenge: "", notes: "", levenshteinDistance: 0, actions: ""}, {id: "ID", challenge: "Challenge", notes: "Notes", levenshteinDistance: "Levenshtein Dist.", actions: "Actions"})

// class NewChallengesInstancesTable extends Table<any>

function setTable() {
    oldChallengesTableContainerDivEl.appendChild(oldChallengesTable.getMessageElement()!)
    oldChallengesTableContainerDivEl.appendChild(oldChallengesTable.getTable())
    oldChallengeInstances.sort((a,b) => a.id - b.id)
    oldChallengeInstances.forEach(el => oldChallengesTable.appendRow({
        id: el.id,
        challenge: el.challenge,
        notes: el.notes,
        actions: "",
        levenshteinDistance: -1
    }))
}

init();


