package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.service.DifficultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/difficulties")
public class DifficultyApiController
{
    private final DifficultyService difficultyService;

    public DifficultyApiController(DifficultyService difficultyService)
    {
        this.difficultyService = difficultyService;
    }

    @GetMapping()
    public ResponseEntity<List<Difficulty>> getDifficulties()
    {
        return ResponseEntity.ok(difficultyService.getAllDifficulties());
    }
}
