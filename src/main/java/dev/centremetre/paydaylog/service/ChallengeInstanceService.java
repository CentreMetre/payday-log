package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.ChallengeInstanceCreateDto;
import dev.centremetre.paydaylog.model.ChallengeInstance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ChallengeInstanceService
{
    ChallengeInstance createChallengeInstance(ChallengeInstanceCreateDto challengeInstanceCreateDto);

    List<ChallengeInstance> getByChallengeId(int id);

    List<ChallengeInstance> getChallengesCompletedOn(LocalDate date);

    List<ChallengeInstance> getChallengesCompletedBetweenDates(LocalDate startDate, LocalDate endDate);

    /**
     * Get {@link ChallengeInstance}s that where completed between two times, regardless of date.
     * @param startTime The start of the period to retrieve.
     * @param endTime The end of the period to retrieve.
     * @return A list of challenge instances that match the parameters.
     */
    List<ChallengeInstance> getChallengesCompletedBetweenTimes(LocalTime startTime, LocalTime endTime);
}
