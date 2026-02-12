package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.ChallengeInstanceCreateDto;
import dev.centremetre.paydaylog.dto.ChallengeInstanceResponseOmitObjectIdsDto;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import dev.centremetre.paydaylog.model.OldChallenge;
import dev.centremetre.paydaylog.repository.OldChallengeRepository;
import dev.centremetre.paydaylog.service.ChallengeInstanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import dev.centremetre.paydaylog.mapper.ChallengeInstanceMapper;

@RestController
@RequestMapping("/api/challenge-instance")
public class ChallengeInstanceApiController
{
    private final ChallengeInstanceService challengeInstanceService;

    private final OldChallengeRepository oldChallengeRepository;

    public ChallengeInstanceApiController(ChallengeInstanceService challengeInstanceService, OldChallengeRepository oldChallengeRepository)
    {
        this.challengeInstanceService = challengeInstanceService;
        this.oldChallengeRepository = oldChallengeRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<ChallengeInstanceResponseOmitObjectIdsDto> createNewChallengeInstance(
            @Valid @RequestBody ChallengeInstanceCreateDto challengeInstanceCreateDto)
    {
        ChallengeInstance saved = challengeInstanceService.createChallengeInstance(challengeInstanceCreateDto);

        ChallengeInstanceResponseOmitObjectIdsDto response =
                ChallengeInstanceMapper.modelToChallengeInstanceResponseOmitObjectIdsDto(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/today")
    public ResponseEntity<List<ChallengeInstanceResponseOmitObjectIdsDto>> getChallengesInstancesOfToday()
    {
        List<ChallengeInstance> saved = challengeInstanceService.getChallengesCompletedOn(LocalDate.now());

        List<ChallengeInstanceResponseOmitObjectIdsDto> response =
                ChallengeInstanceMapper.modelListToChallengeInstanceResponseOmitObjectIdsDto(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date")
    public ResponseEntity<List<ChallengeInstanceResponseOmitObjectIdsDto>> getChallengeInstancesOfDate(@RequestParam LocalDate date)
    {
        List<ChallengeInstance> saved = challengeInstanceService.getChallengesCompletedOn(date);

        List<ChallengeInstanceResponseOmitObjectIdsDto> response =
                ChallengeInstanceMapper.modelListToChallengeInstanceResponseOmitObjectIdsDto(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ChallengeInstanceResponseOmitObjectIdsDto>> getChallengeInstancesOfDateRange(@RequestParam LocalDate startDate,
                                                                                    @RequestParam LocalDate endDate)
    {
        List<ChallengeInstance> saved = challengeInstanceService.getChallengesCompletedBetweenDates(startDate, endDate);

        List<ChallengeInstanceResponseOmitObjectIdsDto> response =
                ChallengeInstanceMapper.modelListToChallengeInstanceResponseOmitObjectIdsDto(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/id")
    public ResponseEntity<List<ChallengeInstanceResponseOmitObjectIdsDto>> getChallengeInstancesOfChallengeId(@RequestParam int challengeId)
    {
        List<ChallengeInstance> saved = challengeInstanceService.getByChallengeId(challengeId);

        List<ChallengeInstanceResponseOmitObjectIdsDto> response =
                ChallengeInstanceMapper.modelListToChallengeInstanceResponseOmitObjectIdsDto(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/old/all")
    public ResponseEntity<List<OldChallenge>> getAllOldChallenges()
    {
        List<OldChallenge> oldChallenges = oldChallengeRepository.findAll();

        System.out.println(oldChallenges.getFirst());

        return ResponseEntity.ok(oldChallenges);
    }
}
