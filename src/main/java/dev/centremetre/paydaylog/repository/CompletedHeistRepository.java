package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.model.CompletedHeist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompletedHeistRepository extends JpaRepository<CompletedHeist, Integer>
{
    // Retrieve by xpAmount

    /**
     * Find a completed heist by its exact XP amount.
     * @param xpAmount
     * @return
     */
    List<CompletedHeist> findByXpAmount(int xpAmount);

    List<CompletedHeist> findByXpAmountGreaterThan(int xpAmountIsGreaterThan);
    List<CompletedHeist> findByXpAmountLessThan(int xpAmountIsLessThan);

    // Retrieve by isAccurateXpInput
    List<CompletedHeist> findByAccurateXpInput(boolean accurateXpInput);

    // Retrieve by heist
    List<CompletedHeist> findByHeist(Heist heist);

    // Retrieve by completedAt
    List<CompletedHeist> findByCompletedAt(LocalDateTime completedAt);

    List<CompletedHeist> findByCompletedAtBefore(LocalDateTime completedAtBefore);
    List<CompletedHeist> findByCompletedAtAfter(LocalDateTime completedAtAfter);

    // Retrieve by heistSuccess
    List<CompletedHeist> findByHeistSuccess(boolean heistSuccess);

    // Retrieve by heistFinishState
    List<CompletedHeist> findByHeistFinishState(int heistFinishState);

    // Retrieve by majorityStatePlayedStealth
    List<CompletedHeist> findByMajorityStatePlayedStealth(boolean majorityStatePlayedStealth);

    // Retrieve by difficulty
    List<CompletedHeist> findByDifficulty(Difficulty difficulty);

    /**
     * Retrieve the latest completed heists. Done by sorting by ID.
     * @return The latest completed heist in a {@link CompletedHeist} instance.
     */
    CompletedHeist findTopByOrderByIdDesc();

    @Query(value = "SELECT * FROM CompletedHeist ORDER BY id DESC LIMIT 10", nativeQuery = true)
    List<CompletedHeist> getTenLatestCompletedHeists();
}
