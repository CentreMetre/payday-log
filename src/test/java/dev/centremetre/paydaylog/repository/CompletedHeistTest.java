package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.CompletedHeist;
import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.model.HeistState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
/*
 Needed to use persistent file db for test to work, doesn't work for test provided db.
 Related: https://github.com/spring-projects/spring-boot/issues/35253
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompletedHeistTest
{
    @Autowired
    CompletedHeistRepository completedRepository;

    @Autowired
    HeistRepository heistRepository;

    @Autowired
    DifficultyRepository difficultyRepository;

    CompletedHeist defaultInstance;

    int xpAmount;
    boolean accurateXpInput;
    Heist heist;
    LocalDateTime completedAt;
    boolean heistSuccess;
    HeistState heistFinishState;
    boolean majorityStatePlayedStealth;
    Difficulty difficulty;
    String notes;

    @BeforeEach
    void setUpTableData()
    {
        // Set difficulties table
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
        difficulty = difficultyRepository.save(overkill);

        // Set heists table
        Heist heistNoRestForTheWicked = new Heist();
        heistNoRestForTheWicked.setName("No Rest For The Wicked");
        heist = heistRepository.save(heistNoRestForTheWicked);

        Heist heistRoadRage = new Heist();
        heistRoadRage.setName("Road Rage");
        heistRepository.save(heistRoadRage);

        Heist heistDirtyIce = new Heist();
        heistDirtyIce.setName("Dirty Ice");
        heistRepository.save(heistDirtyIce);

        Heist heistRockTheCradle = new Heist();
        heistRockTheCradle.setName("Rock The Cradle");
        heistRepository.save(heistRockTheCradle);

        Heist heistUnderTheSurphaze = new Heist();
        heistUnderTheSurphaze.setName("Under The Surphaze");
        heistRepository.save(heistUnderTheSurphaze);

        Heist heistGoldAndSharke = new Heist();
        heistGoldAndSharke.setName("Gold & Sharke");
        heistRepository.save(heistGoldAndSharke);

        Heist heist99Boxes = new Heist();
        heist99Boxes.setName("99 Boxes");
        heistRepository.save(heist99Boxes);

        Heist heistTouchTheSky = new Heist();
        heistTouchTheSky.setName("Touch The Sky");
        heistRepository.save(heistTouchTheSky);

        //Create default completed heist
        xpAmount = 2000;
        accurateXpInput = true;
        completedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        heistSuccess = true;
        heistFinishState = HeistState.FINAL_CHARGE;
        majorityStatePlayedStealth = false;
        notes = "Test Notes";

        defaultInstance = new CompletedHeist();
        defaultInstance.setXpAmount(xpAmount);
        defaultInstance.setAccurateXpInput(accurateXpInput);
        defaultInstance.setHeist(heist);
        defaultInstance.setCompletedAt(completedAt);
        defaultInstance.setHeistSuccess(heistSuccess);
        defaultInstance.setHeistFinishState(heistFinishState);
        defaultInstance.setMajorityStatePlayedStealth(majorityStatePlayedStealth);
        defaultInstance.setDifficulty(difficulty);
        defaultInstance.setNotes(notes);
    }

    @Test
    void testSaveAndRetrieveHeistCompareFields()
    {
        CompletedHeist saved = completedRepository.save(defaultInstance);
        CompletedHeist savedRetrieved = completedRepository.findById(saved.getId()).get();


        assertThat(savedRetrieved.getXpAmount()).isEqualTo(defaultInstance.getXpAmount());
        assertThat(savedRetrieved.isAccurateXpInput()).isEqualTo(defaultInstance.isAccurateXpInput());
        assertThat(savedRetrieved.getHeist()).isEqualTo(defaultInstance.getHeist()); // assumes Heist has proper equals()
        assertThat(savedRetrieved.getCompletedAt()).isEqualTo(defaultInstance.getCompletedAt());
        assertThat(savedRetrieved.isHeistSuccess()).isEqualTo(defaultInstance.isHeistSuccess());
        assertThat(savedRetrieved.getHeistFinishState()).isEqualTo(defaultInstance.getHeistFinishState());
        assertThat(savedRetrieved.isMajorityStatePlayedStealth()).isEqualTo(defaultInstance.isMajorityStatePlayedStealth());
        assertThat(savedRetrieved.getDifficulty()).isEqualTo(defaultInstance.getDifficulty()); // assumes Difficulty has equals()
        assertThat(savedRetrieved.getNotes()).isEqualTo(defaultInstance.getNotes());
    }
}

/**
 * sql for testing in h2 console.

insert into heists (name) values ('Road Rage');
insert into difficulties (difficulty) values ('Overkill');

select * from heists;
select * from difficulties;

INSERT INTO heists_completed (completed_at, heist_finish_state, heist_success, accurate_xp_input, majority_state_played_stealth, notes, xp_amount, difficulty, heist)
values                       (CURRENT_TIMESTAMP(3), 6,          TRUE,          TRUE,              FALSE,                         'test', 2000,     1,          1);
select * from HEISTS_COMPLETED;

 */