package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.model.Difficulty;

import java.util.List;

public interface DifficultyService
{
    List<Difficulty> getAllDifficulties();

    Difficulty getDifficultyById(int id);
}
