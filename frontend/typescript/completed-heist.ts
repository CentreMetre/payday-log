/**
 * Flat version of a completed heist from the backend.
 */
export type CompletedHeist = { // TODO: Decide on string or number for ID
    id: number;
    xpAmount: number;
    accurateXpAmount: boolean;

    // Flattened Heist
    heistId: number;
    heistName: string;

    completedAt: Date;
    heistSuccess: boolean;

    // Flattened finish state
    heistFinishStateId: number;
    heistFinishStateName: string;

    majorityStatePlayedStealth: boolean;

    // Flattened Difficulty
    difficultyId: number;
    difficultyName: string;

    notes: string;
}