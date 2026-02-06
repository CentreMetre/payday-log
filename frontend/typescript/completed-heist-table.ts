import type {CompletedHeist, CompletedHeistDefaultRowShape} from "./models/completed-heist.js";
import {Table} from "./abstract-table.js";

/**
 * Used for table head row.
 */
export const completedHeistDefaultHeaderNames: Record<keyof CompletedHeistDefaultRowShape, string> = {
    id: "ID",
    heistName: "Heist Name",
    xpAmount: "XP Amount",
    completedAt: "Completed At",
    allBagsSecured: "All Bags?",
    accurateXpAmount: "Accurate XP Amount?",
    difficultyName: "Difficulty",
    heistFinishStateName: "Finish State",
    heistSuccess: "Passed?",
    notes: "Notes"
}

export const completedHeistDefaultRowShapeExample: CompletedHeistDefaultRowShape = {
    accurateXpAmount: false,
    completedAt: "",
    difficultyName: "",
    heistFinishStateName: "",
    heistName: "",
    heistSuccess: false,
    id: 0,
    allBagsSecured: false,
    notes: "",
    xpAmount: 0
}

export class CompletedHeistTable extends Table<CompletedHeistDefaultRowShape> {

    async fetchLatestRow(): Promise<CompletedHeistDefaultRowShape> {
        const response = await fetch("/api/completed-heists/latest")
        return await response.json()
    }

}

