/**
 * Flat version of a challenge instance from the backend.
 */
type ChallengeInstance = {
    id: number;

    //Challenge
    challenge_id: number;
    challenge_text: string;

    completed: boolean;
    completed_at: Date;
    notes: string;
}