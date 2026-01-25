package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ChallengeInstanceRepository extends JpaRepository<ChallengeInstance, Integer>
{
    /**
     * Find all {@link ChallengeInstance}s that have a specific challenge as its challenge.
     * @param challenge The challenge to search for.
     * @return A list of all challenge instances with the provided challenge.
     */
    List<ChallengeInstance> findByChallenge(Challenge challenge);

    /**
     * Find all {@link ChallengeInstance}s that have challengeId set to id param.
     * @param id The {@link Challenge}.id to look for.
     * @return A list of challenge instances.
     */
    List<ChallengeInstance> findByChallenge_Id(int id);

    /**
     * Find all {@link ChallengeInstance}s that have their {@code isCompleted} set to the provided value.
     * @param completed Whether or not the challenge was completed.
     * @return A list of challenge instances with their {@code isCompleted} set to the provided value.
     */
    List<ChallengeInstance> findByCompleted(boolean completed);

    /**
     * Find all {@link ChallengeInstance}s completed at a specific time, down to the millisecond.
     * @param completedAt The {@link LocalDateTime} to look for.
     * @return A list of all challenge instances with the given date time.
     */
    List<ChallengeInstance> findByCompletedAt(LocalDateTime completedAt);

    /**
     *
     * @param startDateTime The {@link LocalDateTime} of time to search from.
     * @param endDateTime The {@link LocalDateTime} of time to search to.
     *                    Inclusive according to JPA behaviour for keyword Between.
     * @return A list of all challenge instances between the given time.
     */
    List<ChallengeInstance> findByCompletedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * Find all {@link ChallengeInstance} that are between two dates.
     * @param start The start date to search from, inclusive.
     * @param end The end date to search to, inclusive.
     * @return A List of {@link ChallengeInstance} with the completedAt value being between the two provided dates,
     * inclusive.
     */
    @Query(
            value = "SELECT * FROM challenge_instances" +
                    "WHERE CAST(completed_at as DATE) >= :start" +
                    "AND CAST(completed_at as DATE) <= :end",
            nativeQuery = true) //TODO: look at improving for performance. Cast cant index.
    List<ChallengeInstance> getByCompletedAtBetweenDates(@Param("start") LocalDate start, @Param("end") LocalDate end);

    List<ChallengeInstance> findByCompletedAtBefore(LocalDateTime completedAtBefore);
    List<ChallengeInstance> findByCompletedAtAfter(LocalDateTime completedAtAfter);

    /**
     * Get {@link ChallengeInstance}s that where completed between two times, regardless of date.
     * @param start The start of the period to retrieve, inclusive.
     * @param end The end of the period to retrieve, inclusive.
     * @return A list of challenge instances that match the parameters.
     */
    //TODO: Fix so end can be smaller than start for over midnight.
    @Query(value = "SELECT * FROM challenge_instances WHERE CAST(completed_at as TIME(3)) >= :start" +
            "AND CAST(completed_at as TIME(3)) <= :end",
            nativeQuery = true) // TODO: Look into making more performant.
    List<ChallengeInstance> getByCompletedAtTimeBetween(@Param("start") LocalTime start, @Param("end") LocalTime end);

}
