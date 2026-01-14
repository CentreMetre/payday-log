package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer>
{
    Optional<Difficulty> findByDifficulty(String difficulty);
}
