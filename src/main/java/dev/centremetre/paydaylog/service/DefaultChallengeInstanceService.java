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
        // TODO: Add validation for challenge instances already added today to make sure duplicate isn't added.
        // TODO: Add validation for amount challenges instances so theres no more than 3, but with an override for
        //  after midnight but before 1 in summer time. Also special cases like betas, or challenges resetting midday.
        ChallengeInstance challengeInstance = new ChallengeInstance();

        Challenge challenge = challengeService.getChallengeById(challengeInstanceCreateDto.getChallengeId());

        challengeInstance.setChallenge(challenge);
        challengeInstance.setCompleted(challengeInstanceCreateDto.isCompleted());
        challengeInstance.setCompletedAt(challengeInstanceCreateDto.getCompletedAt());
        // TODO: change so if null or empty it gets a default string of something like "Notes intentionally empty.".
        challengeInstance.setNotes(challengeInstanceCreateDto.getNotes());

        return challengeInstanceRepository.save(challengeInstance);
    }

    @Override
    public List<ChallengeInstance> getByChallengeId(int id)
    {
        return challengeInstanceRepository.findByChallenge_Id(id);
    }


    @Override
    public List<ChallengeInstance> getChallengesCompletedOn(LocalDate date)
    {
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIDNIGHT);
        LocalDateTime end = LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT);

        return challengeInstanceRepository.findByCompletedAtBetween(start, end);
    }

    @Override
    public List<ChallengeInstance> getChallengesCompletedBetweenDates(LocalDate startDate, LocalDate endDate)
    {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIDNIGHT);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        return challengeInstanceRepository.findByCompletedAtBetween(startDateTime, endDateTime);
    }

    @Override
    public List<ChallengeInstance> getChallengesCompletedBetweenTimes(LocalTime startTime, LocalTime endTime)
    {
        return challengeInstanceRepository.getByCompletedAtTimeBetween(startTime, endTime);
    }
}
