package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer>
{
    Challenge findChallengeById(int id);

    Optional<Challenge> findChallengeByChallenge(@NotNull String challenge);

    /**
     * Retrieves challenges with the prefix provided. Letter case not important.
     * Generated with JPABuddy.
     * @param challenge The prefix to search for.
     * @return A list of {@link Challenge} that starts with the given prefix.
     */
    List<Challenge> findByChallengeStartsWithIgnoreCase(String challenge);

    /**
     * Retrieves challenges that contain the string provided. Letter case not important.
     * Generated with JPABuddy.
     * @param challenge The string to search for.
     * @return A list of {@link Challenge} that contains with the given string.
     */
    List<Challenge> findByChallengeContainsIgnoreCase(String challenge);

}
