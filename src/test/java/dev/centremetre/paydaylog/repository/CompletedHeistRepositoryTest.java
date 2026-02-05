package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
/*
 Needed to use persistent file db for test to work, doesn't work for test provided db.
 Related: https://github.com/spring-projects/spring-boot/issues/35253
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Needed for before all so i can run it non static.
public class CompletedHeistRepositoryTest
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

    @BeforeAll
    void setUpTableData()
    {
        // These need to be here so that they don't get added before every test and end up filling it to above 128, the limit for tiny int.
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
    }

    @BeforeEach
    void setUp()
    {

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
        defaultInstance.setAllBagsSecured(majorityStatePlayedStealth);
        defaultInstance.setDifficulty(difficulty);
        defaultInstance.setNotes(notes);
    }

    /**
     * Util function to create populated CompletedHeist.
     * @return {@link CompletedHeist} object with populated fields.
     */
    CompletedHeist createNewDefaultInstance()
    {
        CompletedHeist newInstance = new CompletedHeist();
        newInstance.setXpAmount(xpAmount);
        newInstance.setAccurateXpInput(accurateXpInput);
        newInstance.setHeist(heist);
        newInstance.setCompletedAt(completedAt);
        newInstance.setHeistSuccess(heistSuccess);
        newInstance.setHeistFinishState(heistFinishState);
        newInstance.setAllBagsSecured(majorityStatePlayedStealth);
        newInstance.setDifficulty(difficulty);
        newInstance.setNotes(notes);

        return newInstance;
    }

    @Test
    void testSaveAndRetrieveCompletedHeistCompareFields()
    {
        CompletedHeist saved = completedRepository.save(defaultInstance);
        CompletedHeist savedRetrieved = completedRepository.findById(saved.getId()).get();

        assertThat(savedRetrieved.getXpAmount()).isEqualTo(defaultInstance.getXpAmount());
        assertThat(savedRetrieved.isAccurateXpInput()).isEqualTo(defaultInstance.isAccurateXpInput());
        assertThat(savedRetrieved.getHeist()).isEqualTo(defaultInstance.getHeist());
        assertThat(savedRetrieved.getCompletedAt()).isEqualTo(defaultInstance.getCompletedAt());
        assertThat(savedRetrieved.isHeistSuccess()).isEqualTo(defaultInstance.isHeistSuccess());
        assertThat(savedRetrieved.getHeistFinishState()).isEqualTo(defaultInstance.getHeistFinishState());
        assertThat(savedRetrieved.isAllBagsSecured()).isEqualTo(defaultInstance.isAllBagsSecured());
        assertThat(savedRetrieved.getDifficulty()).isEqualTo(defaultInstance.getDifficulty());
        assertThat(savedRetrieved.getNotes()).isEqualTo(defaultInstance.getNotes());
    }

    @ParameterizedTest
    @EnumSource(HeistState.class)  // automatically runs for every enum value
    void testSaveAndRetrieveCompletedHeistAllPossibleFinishState(HeistState state) {
        
        defaultInstance.setHeistFinishState(state);
        completedRepository.save(defaultInstance);
        assertThat(completedRepository.findById(defaultInstance.getId()).get().getHeistFinishState())
                .isEqualTo(state);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Normal", "Hard", "Very Hard", "Overkill"})// The 4 difficulties, that are stored in setup
    void testSaveAndRetrieveCompletedHeistAllPossibleDifficulties(String difficultyName)
    {
//        for (Difficulty difficulty : difficultyRepository.findAll())
//        {
//            System.out.println(difficulty.getId());
//            System.out.println(difficulty.getDifficulty());
//            System.out.println(" ");
//        }

        Difficulty difficulty = difficultyRepository.findByDifficulty(difficultyName).get();
        defaultInstance.setDifficulty(difficulty);
        completedRepository.save(defaultInstance);
        assertThat(completedRepository.findById(defaultInstance.getId()).get().getDifficulty()).isEqualTo(defaultInstance.getDifficulty());
    }

    ///////////////////////////////////////////
    // CompletedHeistRepository Repo Methods //
    ///////////////////////////////////////////


    ////
    // findByXpAmount
    ////
    @Test
    void testSaveAndRetrieveCompletedHeistFindByXpAmountZeroExist()
    {
        completedRepository.save(defaultInstance);

        List<CompletedHeist> retrieved = completedRepository.findByXpAmount(1000);

        assertThat(retrieved.size()).isEqualTo(0);
    }

    @Test
    void testSaveAndRetrieveCompletedHeistFindByXpAmountOneExist()
    {
        completedRepository.save(defaultInstance);

        CompletedHeist instance2 = createNewDefaultInstance();
        instance2.setXpAmount(1000);
        completedRepository.save(instance2);

        CompletedHeist instance3 = createNewDefaultInstance();
        completedRepository.save(instance3);

        List<CompletedHeist> retrieved = completedRepository.findByXpAmount(1000);

        assertThat(retrieved.size()).isEqualTo(1);
    }

    @Test
    void testSaveAndRetrieveCompletedHeistFindByXpAmountTwoExist()
    {
        completedRepository.save(defaultInstance);

        CompletedHeist instance2 = createNewDefaultInstance();
        instance2.setXpAmount(1000);
        completedRepository.save(instance2);

        CompletedHeist instance3 = createNewDefaultInstance();
        completedRepository.save(instance3);

        List<CompletedHeist> retrieved = completedRepository.findByXpAmount(2000);

        assertThat(retrieved.size()).isEqualTo(2);
    }


    ////
    // getTenLatestCompletedHeists
    ////

    @Test
    void saveAndRetrieveGetTenLatestCompletedHeistsInBoundsNine()
    {
        for (int i = 0; i < 9; i++)
        {
            CompletedHeist newInstance = createNewDefaultInstance();
            completedRepository.save(newInstance);
        }

        List<CompletedHeist> retrieved = completedRepository.getTenLatestCompletedHeists();
        assertThat(retrieved.size()).isEqualTo(9);
    }

    @Test
    void saveAndRetrieveGetTenLatestCompletedHeistsOnBoundsTen()
    {
        for (int i = 0; i < 10; i++)
        {
            CompletedHeist newInstance = createNewDefaultInstance();
            completedRepository.save(newInstance);
        }

        List<CompletedHeist> retrieved = completedRepository.getTenLatestCompletedHeists();
        assertThat(retrieved.size()).isEqualTo(10);
    }

    @Test
    void saveAndRetrieveGetTenLatestCompletedHeistsOutOfBoundsEleven()
    {
        for (int i = 0; i < 11; i++)
        {
            CompletedHeist newInstance = createNewDefaultInstance();
            completedRepository.save(newInstance);
        }

        List<CompletedHeist> retrieved = completedRepository.getTenLatestCompletedHeists();
        assertThat(retrieved.size()).isEqualTo(10);
    }
}