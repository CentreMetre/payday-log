/**
 * Flat version of a challenge instance from the backend.
 */
type ChallengeInstance = {
    id: number;

    //Challenge
    challengeId: number;
    challengeText: string;

    completed: boolean;
    completedAt: Date;
    notes: string;
}

export type ChallengeInstanceDefaultRowShape = Omit<ChallengeInstance, "challengeId" | "completedAt"> & {
    completedAt: string;
}