package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.CompletedHeistCreateDto;
import dev.centremetre.paydaylog.model.CompletedHeist;
import dev.centremetre.paydaylog.service.CompletedHeistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/completed-heists")
public class CompletedHeistApiController
{
    private final CompletedHeistService completedHeistService;

    public CompletedHeistApiController(CompletedHeistService completedHeistService)
    {
        this.completedHeistService = completedHeistService;
    }

    /**
     * Create a new completed heist in the DB.
     * @param completedHeist Data that is the same shape as {@link CompletedHeistCreateDto}, with valid data.
     * @return The completed heist that was added to the DB.
     */
    @PostMapping("/create")
    public ResponseEntity<CompletedHeist> createNewCompletedHeist(@Valid @RequestBody CompletedHeistCreateDto completedHeist)
    {
        return ResponseEntity.ok(completedHeistService.createCompletedHeist(completedHeist));
    }

    /**
     * Get the latest completed heist from the DB.
     * For multiple latest completed heists, use {@link #getLatestCompletedHeists(int)}.
     *
     * @return A ResponseEntity with the status ok and containing the latest completed heist.
     */
    @GetMapping("/latest")
    public ResponseEntity<CompletedHeist> getLatestCompletedHeist()
    {
        return ResponseEntity.ok(completedHeistService.getLatestCompletedHeist());
    }

    /**
     * Get multiple latest completed heists from the DB.
     * For just the latest completed heist, use {@link #getLatestCompletedHeist()}
     * @param count The amount of latest completed heists to get from the DB.
     * @return A ResponseEntity with the status ok and containing the list of completed heists.
     */
    @GetMapping(value = "/latest", params = "count")
    public ResponseEntity<List<CompletedHeist>> getLatestCompletedHeists(@RequestParam int count)
    {
        return ResponseEntity.ok(completedHeistService.getLatestCompletedHeists(count));
    }
}
