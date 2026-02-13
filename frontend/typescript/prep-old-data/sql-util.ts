import type {Challenge} from "../models/challenge";
import type {ChallengeInstanceDefaultRowShape} from "../models/challenge-instance";

type OldChallengeDbRow = {
    id: number;
    challenge: string;
    completed_at: string;
    /**
     * Should be the format of 2025-05-26 16:49:02.900 (no T)
     */
    is_completed: string;
    notes: string;
}

const oldChallengeDbRowExample: OldChallengeDbRow = {
    id: 0, challenge: "", completed_at: "", is_completed: "", notes: ""
}

const columnNames: Array<keyof OldChallengeDbRow> = [];

for (const key of Object.keys(oldChallengeDbRowExample) as Array<keyof OldChallengeDbRow>) {
    columnNames.push(key)
}

//pointless, just maps the old challenge to sql, need to map old challenge to new challenge/challenge instance.
// At least logic is done
export function generateOldChallengeInstancesSqlInsertStatement(challengeInstances: OldChallengeDbRow[]): string {
    // debugger;
    console.log(columnNames)
    const tableName = "challenge_instances";

    const insertRow = `INSERT INTO ${tableName} (${columnNames.join()}) VALUES`

    const sqlValueLines: string[] = []

    for (const row of challengeInstances) {
        let valueLine = ""
        let values = []
        for (const key of columnNames) {
            let value = row[key]
            if (typeof value !== "number") {
                value = `'${(value as string).replace(/'/g, "''")}'`; // Used to escape single ' (appostrohpes)
            }
            values.push(value)
        }
        valueLine = `(${values.join()})`
        sqlValueLines.push(valueLine)
    }

    const fullValues = sqlValueLines.join(",\n")

    let fullSQL = `${insertRow}\n${fullValues}`

    console.log(fullSQL)
    return fullSQL;
}