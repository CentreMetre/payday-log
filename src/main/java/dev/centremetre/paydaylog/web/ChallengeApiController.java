package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.ChallengeCreateDto;
import dev.centremetre.paydaylog.model.Challenge;
import dev.centremetre.paydaylog.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeApiController
{
    private final ChallengeService challengeService;

    public ChallengeApiController(ChallengeService challengeService)
    {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges()
    {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @PostMapping("/create")
    public ResponseEntity<Challenge> createNewChallenge(@RequestBody ChallengeCreateDto challengeCreateDto)
    {
        return ResponseEntity.ok(challengeService.createNewChallenge(challengeCreateDto));
    }
}
