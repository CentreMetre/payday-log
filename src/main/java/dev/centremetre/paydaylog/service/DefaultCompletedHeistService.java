package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.model.CompletedHeist;
import dev.centremetre.paydaylog.repository.CompletedHeistRepository;

import java.util.List;

public class DefaultCompletedHeistService implements CompletedHeistService
{
    private final CompletedHeistRepository completedHeistRepository;

    public DefaultCompletedHeistService(CompletedHeistRepository completedHeistRepository)
    {
        this.completedHeistRepository = completedHeistRepository;
    }

    @Override
    public CompletedHeist createCompletedHeist(CompletedHeist completedHeist)
    {
        return null;
    }

    @Override
    public List<CompletedHeist> getAll()
    {
        return completedHeistRepository.findAll();
    }

    @Override
    public CompletedHeist getLatestCompletedHeist()
    {
        return completedHeistRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<CompletedHeist> getLatestCompletedHeists(int limit)
    {
        return completedHeistRepository.getTenLatestCompletedHeists();
    }
}
