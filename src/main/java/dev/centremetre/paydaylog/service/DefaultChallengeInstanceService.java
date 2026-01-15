package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.ChallengeInstanceCreateDto;
import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import dev.centremetre.paydaylog.repository.ChallengeInstanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DefaultChallengeInstanceService implements ChallengeInstanceService
{
    private final ChallengeInstanceRepository challengeInstanceRepository;

    private final ChallengeService challengeService;

    public DefaultChallengeInstanceService(ChallengeInstanceRepository challengeInstanceRepository, ChallengeService challengeService)
    {
        this.challengeInstanceRepository = challengeInstanceRepository;
        this.challengeService = challengeService;
    }

    @Override
    public ChallengeInstance createChallengeInstance(ChallengeInstanceCreateDto challengeInstanceCreateDto)
    {
        ChallengeInstance challengeInstance = new ChallengeInstance();

        Challenge challenge = challengeService.getChallengeById(challengeInstance.getId());

        challengeInstance.setChallenge(challenge);
        challengeInstance.setCompleted(challengeInstanceCreateDto.getIsCompleted());
        challengeInstance.setCompletedAt(challengeInstanceCreateDto.getCompletedAt());
        challengeInstance.setNotes(challengeInstanceCreateDto.getNotes());

        return challengeInstanceRepository.save(challengeInstance);
    }

    @Override
    public List<ChallengeInstance> getChallengesCompletedOn(LocalDate date)
    {
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIDNIGHT);
        LocalDateTime end = LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT);

        return challengeInstanceRepository.findByCompletedAtBetween(start, end);
    }

    @Override
    public List<ChallengeInstance> getChallengesCompletedBetween(LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        return challengeInstanceRepository.findByCompletedAtBetween(startDateTime, endDateTime);
    }

    @Override
    public List<ChallengeInstance> getChallengesCompletedBetweenTime(LocalTime startTime, LocalTime endTime)
    {
        return challengeInstanceRepository.getByCompletedAtTimeBetween(startTime, endTime);
    }
}
