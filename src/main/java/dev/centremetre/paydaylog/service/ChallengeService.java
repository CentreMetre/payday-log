package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.model.Challenge;

import java.util.List;

public interface ChallengeService
{
    Challenge getChallengeById(int id);

    /**
     * Get a challenge by its challenge text. E.g. "Stun 8 enemies."
     * Has to be exactly correct, otherwise use {@link #searchForChallengesByChallengeLevenshtein(String)}
     * @param challenge The challenge to retrieve.
     * @return The challenge in {@link Challenge} instance.
     */
    Challenge getChallengeByChallenge(String challenge);

    /**
     * Searches for challenges based on the search term. Uses levenshtein distance.
     * @param challengeSearchTerm The challenge to search for.
     * @return A list of challenges that are in the levenshtein distance.
     */
    List<Challenge> searchForChallengesByChallengeLevenshtein(String challengeSearchTerm);

    /**
     * Searches for challenges based on a prefix search. Case unimportant.
     * E.g. inputting "Place " would return "Place the Micro Camera 4 times.", "Place the ECM Jammer 4 times."
     * @param prefix The prefix to search for.
     * @return A list of challenges that match the prefix.
     */
    List<Challenge> searchForChallengesByPrefixSearch(String prefix);

    /**
     * Searches for challenges based on if the challenge string has the provided sub string. Case unimportant.
     * @param contains The substring to search for.
     * @return A list of challenges that contain the substring.
     */
    List<Challenge> searchForChallengesByContainsSearch(String contains);

    Challenge createNewChallenge(String challengeText);
}
