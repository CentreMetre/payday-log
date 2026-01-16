package dev.centremetre.paydaylog.web;

import dev.centremetre.paydaylog.dto.CompletedHeistCreateDto;
import dev.centremetre.paydaylog.model.CompletedHeist;
import dev.centremetre.paydaylog.service.CompletedHeistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/completed-heists")
public class CompletedHeistController
{
    private final CompletedHeistService completedHeistService;

    public CompletedHeistController(CompletedHeistService completedHeistService)
    {
        this.completedHeistService = completedHeistService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompletedHeist> createNewCompletedHeist(@Valid @RequestBody CompletedHeistCreateDto completedHeist)
    {
        return ResponseEntity.ok(completedHeistService.createCompletedHeist(completedHeist));
    }
}
