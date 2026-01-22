package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.CompletedHeistCreateDto;
import dev.centremetre.paydaylog.model.CompletedHeist;
import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.model.HeistState;
import dev.centremetre.paydaylog.repository.CompletedHeistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCompletedHeistService implements CompletedHeistService
{
    private final CompletedHeistRepository completedHeistRepository;
    private final DifficultyService difficultyService;
    private final HeistService heistService;

    public DefaultCompletedHeistService(CompletedHeistRepository completedHeistRepository,
                                        DifficultyService difficultyService, HeistService heistService)
    {
        this.completedHeistRepository = completedHeistRepository;
        this.difficultyService = difficultyService;
        this.heistService = heistService;
    }

    @Override
    public CompletedHeist createCompletedHeist(CompletedHeistCreateDto completedHeistDto)
    {
        //Have to get instance of the heist and difficulty to save in the db
        Heist heist = heistService.getHeistFromId(completedHeistDto.getHeistId());

        Difficulty difficulty = difficultyService.getDifficultyById(completedHeistDto.getDifficultyId());

        HeistState finishState = HeistState.fromId(completedHeistDto.getHeistFinishStateId());

        CompletedHeist completedHeist = new CompletedHeist();

        completedHeist.setXpAmount(completedHeistDto.getXpAmount());
        completedHeist.setAccurateXpInput(completedHeistDto.getIsAccurateXpInput());
        completedHeist.setHeist(heist);
        completedHeist.setCompletedAt(completedHeistDto.getCompletedAt());
        completedHeist.setHeistSuccess(completedHeistDto.getHeistSuccess());
        completedHeist.setHeistFinishState(finishState);
        completedHeist.setMajorityStatePlayedStealth(completedHeistDto.getIsMajorityStatePlayedStealth());
        completedHeist.setDifficulty(difficulty);
        completedHeist.setNotes(completedHeistDto.getNotes());

        return completedHeistRepository.save(completedHeist);
    }

//    @Override
//    public List<CompletedHeist> getAll()
//    {
//        return completedHeistRepository.findAll();
//    }

    @Override
    public CompletedHeist getLatestCompletedHeist()
    {
        return completedHeistRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<CompletedHeist> getLatestCompletedHeists(int limit)
    {
        return completedHeistRepository.getTenLatestCompletedHeists();
    }
}
