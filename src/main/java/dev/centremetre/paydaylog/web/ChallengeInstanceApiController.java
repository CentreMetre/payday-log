package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.ChallengeInstanceCreateDto;
import dev.centremetre.paydaylog.model.ChallengeInstance;
import dev.centremetre.paydaylog.service.ChallengeInstanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/completed-challenges")
public class ChallengeInstanceApiController
{
    private final ChallengeInstanceService challengeInstanceService;

    public ChallengeInstanceApiController(ChallengeInstanceService challengeInstanceService)
    {
        this.challengeInstanceService = challengeInstanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<ChallengeInstance> createNewChallengeInstance(
            @Valid @RequestBody ChallengeInstanceCreateDto challengeInstanceCreateDto)
    {
        return ResponseEntity.ok(challengeInstanceService.createChallengeInstance(challengeInstanceCreateDto));
    }

    @GetMapping("/date/today")
    public ResponseEntity<List<ChallengeInstance>> getChallengesInstancesOfToday()
    {
        return ResponseEntity.ok(challengeInstanceService.getChallengesCompletedOn(LocalDate.now()));
    }

    @GetMapping("/date")
    public ResponseEntity<List<ChallengeInstance>> getChallengeInstancesOfDate(@RequestParam LocalDate date)
    {
        return ResponseEntity.ok(challengeInstanceService.getChallengesCompletedOn(date));
    }
}
