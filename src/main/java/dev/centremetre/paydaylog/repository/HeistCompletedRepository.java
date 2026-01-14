package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.model.HeistCompleted;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HeistCompletedRepository extends JpaRepository<HeistCompleted, Integer>
{
    // Retrieve by xpAmount

    /**
     * Find a completed heist by its exact XP amount.
     * @param xpAmount
     * @return
     */
    List<HeistCompleted> findByXpAmount(int xpAmount);

    List<HeistCompleted> findByXpAmountGreaterThan(int xpAmountIsGreaterThan);
    List<HeistCompleted> findByXpAmountLessThan(int xpAmountIsLessThan);

    // Retrieve by isAccurateXpInput
    List<HeistCompleted> findByAccurateXpInput(boolean accurateXpInput);

    // Retrieve by heist
    List<HeistCompleted> findByHeist(Heist heist);

    // Retrieve by completedAt
    List<HeistCompleted> findByCompletedAt(LocalDateTime completedAt);

    List<HeistCompleted> findByCompletedAtBefore(LocalDateTime completedAtBefore);
    List<HeistCompleted> findByCompletedAtAfter(LocalDateTime completedAtAfter);

    // Retrieve by heistSuccess
    List<HeistCompleted> findByHeistSuccess(boolean heistSuccess);

    // Retrieve by heistFinishState
    List<HeistCompleted> findByHeistFinishState(int heistFinishState);

    // Retrieve by majorityStatePlayedStealth
    List<HeistCompleted> findByMajorityStatePlayedStealth(boolean majorityStatePlayedStealth);

    // Retrieve by difficulty
    List<HeistCompleted> findByDifficulty(Difficulty difficulty);
}
