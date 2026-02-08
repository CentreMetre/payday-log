/**
 * Flat version of a challenge instance from the backend.
 */
type ChallengeInstance = {
    id: number;

    //Challenge
    challengeId: number;
    challenge: string;

    isCompleted: boolean;
    completedAt: Date;
    notes: string;
}

//Can also be used for the shape of a response from the server.
export type ChallengeInstanceDefaultRowShape = Omit<ChallengeInstance, "challengeId" | "completedAt"> & {
    completedAt: string;
}

//Can also be used for the shape of a response of list from the server.
export type ChallengeInstanceDefaultRowShapeList = ChallengeInstanceDefaultRowShape[];

export type ChallengeInstanceSubmitShape = Omit<ChallengeInstance, "id" | "challenge" | "completedAt"> & {
    completedAt: string | null;
}