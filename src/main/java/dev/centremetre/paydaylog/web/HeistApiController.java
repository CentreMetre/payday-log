package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.HeistCreateDto;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.service.CompletedHeistService;
import dev.centremetre.paydaylog.service.HeistService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeistApiController
{
    private final HeistService heistService;

    public HeistApiController(HeistService heistService, CompletedHeistService completedHeistService)
    {
        this.heistService = heistService;
    }

    /**
     * Get all heists from the DB.
     * @return A response entity that has a list of heists in the game.
     */
    @GetMapping("/heists/game")
    public ResponseEntity<List<Heist>> getAllHeists()
    {
        return ResponseEntity.ok(heistService.getAllHeists());
    }

    @PostMapping
    public ResponseEntity<Heist> createNewHeist(@RequestBody HeistCreateDto heistName)
    {
        return ResponseEntity.ok(heistService.createHeist(heistName.getHeistName()));
    }
}
