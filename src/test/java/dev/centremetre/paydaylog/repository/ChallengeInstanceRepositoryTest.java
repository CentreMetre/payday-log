package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ChallengeInstanceRepositoryTest
{
    @Autowired
    ChallengeInstanceRepository instanceRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    private Challenge challenge;

    ChallengeInstance newInstance;

    String challengeName;

    // ChallengeInstance Data:
    boolean isCompleted;
    LocalDateTime completedAt;
    String notes;

    @BeforeEach
    void setup()
    {
        challengeName = "Test challenge";
        challenge = new Challenge();
        challenge.setChallenge(challengeName);
        challengeRepository.save(challenge);

        // Challenge Instance default data. Not saved to DB so it can be edited for a test.
        isCompleted = true;
        completedAt = LocalDateTime.now();
        notes = "Test notes";
        newInstance = new ChallengeInstance();
        newInstance.setChallenge(challenge);
        newInstance.setCompleted(isCompleted);
        newInstance.setCompletedAt(completedAt);
        newInstance.setNotes(notes);
    }

    @Test
    void testSavingAndRetrievingChallengeInstanceByIdAllFieldsFilledAndValid()
    {
        Optional<ChallengeInstance> savedInstanceOptional = instanceRepository.findById(newInstance.getId());

        if (savedInstanceOptional.isPresent())
        {
            ChallengeInstance savedInstance = savedInstanceOptional.get();

            assertThat(savedInstance.getChallenge().getId()).isEqualTo(challenge.getId());
            assertThat(savedInstance.getChallenge().getChallenge()).isEqualTo(challengeName);

            assertThat(savedInstance.isCompleted()).isEqualTo(isCompleted);
            assertThat(savedInstance.getCompletedAt()).isEqualTo(completedAt);
            assertThat(savedInstance.getNotes()).isEqualTo(notes);
        }
    }

    @Test
    void testSavingAndFindByChallenge_IdZeroExists()
    {
        List<ChallengeInstance> instances = instanceRepository.findByChallenge_Id(challenge.getId());

        assertThat(instances.size()).isEqualTo(0);
    }

    @Test
    void testSavingAndFindByChallenge_IdOneExists()
    {
        instanceRepository.save(newInstance);

        List<ChallengeInstance> instances = instanceRepository.findByChallenge_Id(challenge.getId());

        assertThat(instances.size()).isEqualTo(1);
    }

    @Test
    void testSavingAndFindByChallenge_IdTwoExists()
    {
        //Save 2 for the test. Duplicate intended.

        instanceRepository.save(newInstance);

        ChallengeInstance newInstance2 = createFilledOutInstance();
        instanceRepository.save(newInstance2);

        List<ChallengeInstance> instances = instanceRepository.findByChallenge_Id(challenge.getId());

        assertThat(instances.size()).isEqualTo(2);
    }

    @Test
    void testSavingAndFindByCompletedTrueTwoExist()
    {
        instanceRepository.save(newInstance);

        ChallengeInstance newInstance2 = createFilledOutInstance();
        newInstance2.setCompleted(false);
        instanceRepository.save(newInstance2);

        ChallengeInstance newInstance3 = createFilledOutInstance();
        instanceRepository.save(newInstance3);

        List<ChallengeInstance> instances = instanceRepository.findByCompleted(true);

        assertThat(instances.size()).isEqualTo(2);
    }

    @Test
    void testSavingAndFindByCompletedFalseTwoExist()
    {
        newInstance.setCompleted(false);
        instanceRepository.save(newInstance);

        ChallengeInstance newInstance2 = createFilledOutInstance();
        instanceRepository.save(newInstance2);

        ChallengeInstance newInstance3 = createFilledOutInstance();
        newInstance3.setCompleted(false);
        instanceRepository.save(newInstance3);

        List<ChallengeInstance> instances = instanceRepository.findByCompleted(false);

        assertThat(instances.size()).isEqualTo(2);
    }

    @Test
    void testSavingAndFindByCompletedTrueZeroExist()
    {
        newInstance.setCompleted(false);
        instanceRepository.save(newInstance);

        ChallengeInstance newInstance3 = createFilledOutInstance();
        newInstance3.setCompleted(false);
        instanceRepository.save(newInstance3);

        List<ChallengeInstance> instances = instanceRepository.findByCompleted(true);

        assertThat(instances.size()).isEqualTo(0);
    }

    @Test
    void testSavingAndFindByCompletedFalseZeroExist()
    {
        ChallengeInstance newInstance = createFilledOutInstance();
        instanceRepository.save(newInstance);

        List<ChallengeInstance> instances = instanceRepository.findByCompleted(false);

        assertThat(instances.size()).isEqualTo(0);
    }

//    void testSaving

    /**
     * Util method to quickly create a new challenge instance.
     * @return A challenge instance object with all fields (except ID) filled to the data in the class fields.
     */
    ChallengeInstance createFilledOutInstance()
    {
        ChallengeInstance newInstance = new ChallengeInstance();
        newInstance.setChallenge(challenge);
        newInstance.setCompleted(isCompleted);
        newInstance.setCompletedAt(completedAt);
        newInstance.setNotes(notes);

        return newInstance;
    }
}
