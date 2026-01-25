package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        Difficulty saved = difficultyRepository.getReferenceById(difficulty.getId());
        assertThat(saved.getDifficulty()).isEqualTo(difficulty.getDifficulty());
        assertThat(saved.getId()).isEqualTo(difficulty.getId());
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
}
