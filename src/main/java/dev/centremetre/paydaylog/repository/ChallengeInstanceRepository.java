package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeInstanceRepository extends JpaRepository<ChallengeInstance, Integer>
{
    List<ChallengeInstance> getChallengeCompletedByChallenge(Challenge challenge);

    List<ChallengeInstance> getChallengeCompletedByCompleted(boolean completed);

    List<ChallengeInstance> getChallengeCompletedByCompletedAt(LocalDateTime completedAt);

    List<ChallengeInstance> getChallengeCompletedByCompletedAtBefore(LocalDateTime completedAtBefore);
    List<ChallengeInstance> getChallengeCompletedByCompletedAtAfter(LocalDateTime completedAtAfter);
}
