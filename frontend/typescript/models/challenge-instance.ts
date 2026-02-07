/**
 * Flat version of a challenge instance from the backend.
 */
type ChallengeInstance = {
    id: number;

    //Challenge
    challengeId: number;
    challengeText: string;

    isCompleted: boolean;
    completedAt: Date;
    notes: string;
}

export type ChallengeInstanceDefaultRowShape = Omit<ChallengeInstance, "challengeId" | "completedAt"> & {
    completedAt: string;
}

export type ChallengeInstanceDefaultRowShapeList = ChallengeInstanceDefaultRowShape[];

export type ChallengeInstanceSubmitShape = Omit<ChallengeInstance, "id" | "challengeText" | "completedAt"> & {
    completedAt: string | null;
}