package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DifficultyRepositoryTest
{
    @Autowired
    DifficultyRepository difficultyRepository;

    @Test
    void testSavingAndRetrieveDifficulty()
    {
        Difficulty difficulty = new Difficulty();
        difficulty.setDifficulty("Test Difficulty");

        difficultyRepository.save(difficulty);
        Optional<Difficulty> saved = difficultyRepository.findById(difficulty.getId());
        if(saved.isPresent())
        {
            assertThat(saved.get().getDifficulty()).isEqualTo(difficulty.getDifficulty());
            assertThat(saved.get().getId()).isEqualTo(difficulty.getId());
        }
    }

    @Test
    void testSavingAndGetByDifficulty()
    {
        String name = "Test Difficulty";

        Difficulty difficulty = new Difficulty();
        difficulty.setDifficulty(name);

        difficultyRepository.save(difficulty);
        Optional<Difficulty> retrieved = difficultyRepository.findByDifficulty(name);
        if (retrieved.isPresent())
        {
            assertThat(retrieved.get().getDifficulty()).isEqualTo(name);
        }
    }

    @Test
    void testSavingAndViolatingUniqueConstraint()
    {
        String name = "Normal";

        Difficulty difficulty1 = new Difficulty();
        difficulty1.setDifficulty(name);

        Difficulty difficulty2 = new Difficulty();
        difficulty2.setDifficulty(name);

        difficultyRepository.saveAndFlush(difficulty1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            difficultyRepository.saveAndFlush(difficulty2);
        });
    }
}
