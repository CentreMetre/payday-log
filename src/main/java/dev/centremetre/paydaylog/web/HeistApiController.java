package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.HeistCreateDto;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.service.HeistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO/NOTE: Use @Valid where needed. Look at readme notes for more info.
@RestController
@RequestMapping("/api/heists")
public class HeistApiController
{
    private final HeistService heistService;

    public HeistApiController(HeistService heistService)
    {
        this.heistService = heistService;
    }

    /**
     * Get all heists from the DB.
     * @return A response entity that has a list of heists in the game.
     */
    @GetMapping("")
    public ResponseEntity<List<Heist>> getAllHeists()
    {
        return ResponseEntity.ok(heistService.getAllHeists());
    }

    /**
     * JSON body should look like this.
     * {
     *     heistName: "Heist Name"
     * }
     * @param heistName The name of the heist.
     * @return The created heist object in the DB.
     */
    @PostMapping("/create")
    public ResponseEntity<Heist> createNewHeist(@RequestBody HeistCreateDto heistName)
    {
        return ResponseEntity.ok(heistService.createHeist(heistName.getHeistName()));
    }
}
