package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.ChallengeCreateDto;
import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.repository.ChallengeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultChallengeService implements ChallengeService
{
    private final ChallengeRepository challengeRepository;

    public DefaultChallengeService(ChallengeRepository challengeRepository)
    {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public List<Challenge> getAllChallenges()
    {
        return challengeRepository.findAll();
    }

    @Override
    public Challenge getChallengeById(int id)
    {
        Optional<Challenge> challengeOptional = challengeRepository.findById(id);

        if (challengeOptional.isPresent())
        {
            return challengeOptional.get();
        }
        throw new EntityNotFoundException("Challenge with and ID of " + id + " doesn't exist.");
    }

    @Override
    public Challenge getChallengeByChallenge(String challenge)
    {
        return challengeRepository.findByChallenge(challenge).get(); //TODO: Check it exists.
    }

    @Override
    public List<Challenge> searchForChallengesByChallengeLevenshtein(String challengeSearchTerm)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Challenge> searchForChallengesByPrefixSearch(String prefix)
    {
        return challengeRepository.findByChallengeStartsWithIgnoreCase(prefix);
    }

    @Override
    public List<Challenge> searchForChallengesByContainsSearch(String contains)
    {
        return challengeRepository.findByChallengeContainsIgnoreCase(contains);
    }

    @Override
    public Challenge createNewChallenge(ChallengeCreateDto challengeDto)
    {
        Challenge challenge = new Challenge();
        challenge.setChallenge(challengeDto.getChallenge());
        return challengeRepository.save(challenge);
    }
}
