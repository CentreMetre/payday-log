package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.HeistStateDto;
import dev.centremetre.paydaylog.model.Heist;
import dev.centremetre.paydaylog.model.HeistState;
import dev.centremetre.paydaylog.repository.CompletedHeistRepository;
import dev.centremetre.paydaylog.repository.HeistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultHeistService implements HeistService
{
    private final HeistRepository heistRepository;

    public DefaultHeistService(HeistRepository heistRepository, CompletedHeistRepository completedHeistRepository)
    {
        this.heistRepository = heistRepository;
    }

    @Override
    public Heist getHeistFromName(String name)
    {
        Optional<Heist> heist = heistRepository.findByName(name);

        if (heist.isEmpty())
        {
            throw new NoSuchElementException("There is no heist with the name " + name + ". Please check the spelling.");
        }

        return heist.get();
    }

    @Override
    public List<Heist> getAllHeists()
    {
        return heistRepository.findAll();
    }

    @Override
    public List<String> getAllHeistNames()
    {
        List<Heist> heists = heistRepository.findAll();
        List<String> names = new ArrayList<>();

        for (Heist heist : heists)
        {
            names.add(heist.getName());
        }

        return names;
    }

    @Override
    public Heist createHeist(String name)
    {
        Heist newHeist = new Heist();

        newHeist.setName(name);

        return heistRepository.save(newHeist);
    }

    @Override
    public Heist getHeistFromId(int id)
    {
        return heistRepository.findById(id).get(); //TODO: Check it exists.
    }

    @Override
    public List<HeistStateDto> getHeistStates()
    {
        List<HeistStateDto> heistStates = new ArrayList<>();
        for (HeistState state : dev.centremetre.paydaylog.model.HeistState.values())
        {
            HeistStateDto dto = new HeistStateDto();
            dto.setId(state.getId());
            dto.setState(state.getState());
            heistStates.add(dto);
        }
        return heistStates;
    }
}
