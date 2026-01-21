/**
 * Flat version of a completed heist from the backend.
 */
export type CompletedHeist = { // TODO: Decide on string or number for ID
    id: string;
    xpAmount: string;
    accurateXpAmount: boolean;

    // Flattened Heist
    heistId: string;
    heistName: string;

    completedAt: Date;
    heistSuccess: boolean;
    heistFinishState: string;
    majorityStatePlayedStealth: boolean;

    // Flattened Difficulty
    difficultyId: string;
    difficultyName: string;

    notes: string;
}

