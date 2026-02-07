/**
 * Flat version of a completed heist from the backend.
 */
export type CompletedHeist = {
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

    allBagsSecured: boolean;

    // Flattened Difficulty
    difficultyId: number;
    difficultyName: string;

    notes: string;
}

export type CompletedHeistResponseOmitObjectIds =
    Omit<CompletedHeist, 'heistFinishStateId' | 'heistId' | 'difficultyId'>

export type CompletedHeistDefaultRowShape =
    Omit<CompletedHeist, 'heistFinishStateId' | 'heistId' | 'difficultyId' | 'completedAt'> & {
    completedAt: string; // Since the completedAt is sent in JSON as "2026-01-23T23:17:30.183",
    // it needs to be a string type here, not a date type.
    // Converting it to a date would make it a human-readable Date string, but I want it as an ISO string.
}