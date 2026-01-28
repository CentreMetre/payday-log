package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Challenge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ChallengeRepositoryTest
{
    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    void testSavingAndRetrieveChallenge()
    {
        Challenge challenge = new Challenge();
        challenge.setChallenge("Test Challenge");

        challengeRepository.save(challenge);
        Optional<Challenge> saved = challengeRepository.findById(challenge.getId());
        if (saved.isPresent())
        {
            assertThat(saved.get().getChallenge()).isEqualTo(challenge.getChallenge());
            assertThat(saved.get().getId()).isEqualTo(challenge.getId());
        }
    }

    @Test
    void testSavingAndFindByChallenge()
    {
        String name = "Test Challenge";

        Challenge challenge = new Challenge();
        challenge.setChallenge(name);

        challengeRepository.save(challenge);
        Optional<Challenge> saved = challengeRepository.findByChallenge(name);
        if (saved.isPresent())
        {
            assertThat(saved.get().getChallenge()).isEqualTo(challenge.getChallenge());
            assertThat(saved.get().getId()).isEqualTo(challenge.getId());
        }
    }

    @Test
    void testSavingAndFindByChallengeStartsWithIgnoreCaseSearchInputUpperCase()
    {
        String name = "Test Challenge.";

        Challenge challenge = new Challenge();
        challenge.setChallenge(name);

        challengeRepository.save(challenge);
        List<Challenge> challenges = challengeRepository.findByChallengeStartsWithIgnoreCase("Tes");
        assertThat(challenges.getFirst().getChallenge()).isEqualTo(name);
    }

    @Test
    void testSavingAndFindByChallengeStartsWithIgnoreCaseSearchInputLowerCase()
    {
        String name = "Test Challenge.";

        Challenge challenge = new Challenge();
        challenge.setChallenge(name);

        challengeRepository.save(challenge);
        List<Challenge> challenges = challengeRepository.findByChallengeStartsWithIgnoreCase("tes");
        assertThat(challenges.getFirst().getChallenge()).isEqualTo(name);
    }

    @Test
    void testSavingAndFindByChallengeContainsIgnoreCaseSearchInputUpperCase()
    {
        String name = "Test Challenge.";

        Challenge challenge = new Challenge();
        challenge.setChallenge(name);

        challengeRepository.save(challenge);
        List<Challenge> challenges = challengeRepository.findByChallengeContainsIgnoreCase("St cha");
        assertThat(challenges.getFirst().getChallenge()).isEqualTo(name);
    }

    @Test
    void testSavingAndFindByChallengeContainsIgnoreCaseSearchInputLowerCase()
    {
        String name = "Test Challenge.";

        Challenge challenge = new Challenge();
        challenge.setChallenge(name);

        challengeRepository.save(challenge);
        List<Challenge> challenges = challengeRepository.findByChallengeContainsIgnoreCase("st cha");
        assertThat(challenges.getFirst().getChallenge()).isEqualTo(name);
    }
}
