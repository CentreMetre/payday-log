/**
 * Flat version of a completed heist from the backend.
 */
type CompletedHeist = {
    id: number;
    xpAmount: number;
    accurateXpAmount: boolean;
    // Heist
    heistId: number;
    heistName: string;

    heistSuccess: boolean;
    heistFinishState: number;
    majorityStatePlayedStealth: boolean;
    // Difficulty
    difficultyId: number;
    difficultyName: string;

    notes: string;
}

