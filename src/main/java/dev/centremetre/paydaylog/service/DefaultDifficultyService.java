package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.model.Difficulty;
import dev.centremetre.paydaylog.repository.DifficultyRepository;

public class DefaultDifficultyService implements DifficultyService
{
    private final DifficultyRepository difficultyRepository;

    public DefaultDifficultyService(DifficultyRepository difficultyRepository)
    {
        this.difficultyRepository = difficultyRepository;
    }

    @Override
    public Difficulty getDifficultyById(int id)
    {
        return difficultyRepository.findById(id).get(); // Assume its there for now. TODO: Do proper checking.
    }
}
