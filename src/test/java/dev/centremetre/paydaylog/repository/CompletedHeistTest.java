package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class CompletedHeistTest
{
    @Autowired
    CompletedHeistRepository completedRepository;

    @Autowired
    HeistRepository heistRepository;

    @Autowired
    DifficultyRepository difficultyRepository;

    @BeforeEach
    void setUpTableData()
    {
        Difficulty normal = new Difficulty();
        normal.setDifficulty("Normal");
        difficultyRepository.save(normal);

        Difficulty hard = new Difficulty();
        hard.setDifficulty("Hard");
        difficultyRepository.save(hard);

        Difficulty veryHard = new Difficulty();
        veryHard.setDifficulty("Very Hard");
        difficultyRepository.save(veryHard);

        Difficulty overkill = new Difficulty();
        overkill.setDifficulty("Overkill");
        difficultyRepository.save(overkill);
    }
}
