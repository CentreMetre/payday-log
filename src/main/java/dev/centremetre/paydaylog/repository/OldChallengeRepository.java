package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.OldChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OldChallengeRepository extends JpaRepository<OldChallenge, Integer>
{

}
