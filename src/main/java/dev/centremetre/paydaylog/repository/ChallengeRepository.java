package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer>
{
    Optional<Challenge> getByChallenge(String challenge);
}
