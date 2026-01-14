package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.model.ChallengeCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeCompletedRepository extends JpaRepository<ChallengeCompleted, Integer>
{
    List<ChallengeCompleted> getChallengeCompletedByChallenge(Challenge challenge);

    List<ChallengeCompleted> getChallengeCompletedByCompleted(boolean completed);

    List<ChallengeCompleted> getChallengeCompletedByCompletedAt(LocalDateTime completedAt);

    List<ChallengeCompleted> getChallengeCompletedByCompletedAtBefore(LocalDateTime completedAtBefore);
    List<ChallengeCompleted> getChallengeCompletedByCompletedAtAfter(LocalDateTime completedAtAfter);
}
