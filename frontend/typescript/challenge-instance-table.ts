import type {ChallengeInstanceDefaultRowShape, ChallengeInstanceDefaultRowShapeList} from "./models/challenge-instance";
import {Table} from "./abstract-table.js";
import type {CompletedHeistDefaultRowShape} from "./models/completed-heist";

export const challengeInstanceDefaultHeaderNames: Record<keyof ChallengeInstanceDefaultRowShape, string> = {
    id: "ID",
    challenge: "Challenge",
    isCompleted: "Completed?",
    completedAt: "Completed At",
    notes: "Notes"
}

export const challengeInstanceDefaultRowShapeExample: ChallengeInstanceDefaultRowShape = {
    challenge: "",
    isCompleted: false,
    completedAt: "",
    id: 0,
    notes: ""
}

export class ChallengeInstanceTable extends Table<ChallengeInstanceDefaultRowShape> {

    async fetchToday(): Promise<ChallengeInstanceDefaultRowShapeList> {
        const date = new Date().toISOString().split("T")[0];
        const response = await fetch(`/api/challenge-instance/date?date=${date}`)
        const body = await response.json();
        return body//await response.json();
    }
}