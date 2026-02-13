/**
 * This file is for converting old challenges into: new challenges and new challenge instances
 */

import {Table} from "../abstract-table.js";
import type {OldChallenge} from "./OldChallenge";
import {generateOldChallengeInstancesSqlInsertStatement} from "./sql-util.js";



// Have 2 tables, one for old data, 1 for the new data

const oldChallenges: OldChallenge[] = []

/**
 * Challenges that are correct, not types or notes in the challenge text.
 */
const approvedChallenges: string[] = []



/**
 * String is the name of the typo challenge. Number is the index of approvedChallenges.
 */
const typosMap: Map<string, number> = new Map();

async function getOldChallenges() {
    const response = await fetch("/api/challenge-instance/old/all");

    const body: OldChallenge[] = await response.json();

    for (const oldChallenge of body) {
        oldChallenges.push(oldChallenge);
    }
}

await getOldChallenges()

function test() {
    const firstChallenge = oldChallenges[0]
    console.log(firstChallenge)
    generateOldChallengeInstancesSqlInsertStatement([{
        id: 1, challenge: "c", completed_at: "ca", is_completed: "ic", notes: "n"
    },
        {
        id: 2, challenge: "c", completed_at: "ca", is_completed: "ic", notes: "n"
    },
        {
        id: 3, challenge: "c", completed_at: "ca", is_completed: "ic", notes: "n"
    },
        {
        id: 3, challenge: "hello's", completed_at: "by'es", is_completed: "''", notes: "''''"
    }])
}

test();