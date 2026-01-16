package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.dto.CompletedHeistCreateDto;
import dev.centremetre.paydaylog.model.CompletedHeist;

import java.util.List;

public interface CompletedHeistService
{
    /**
     * Creates a {@link CompletedHeist} in the DB.
     * @param completedHeist The {@link CompletedHeist} to enter into the
     * @return The completed heist that was entered into the DB in a {@link CompletedHeist} instance.
     */
    CompletedHeist createCompletedHeist(CompletedHeistCreateDto completedHeist);

    /**
     * Get the latest completed heist.
     * @return The latest completed heist.
     */
    CompletedHeist getLatestCompletedHeist();

    /**
     * Get a specific amount of heists from the DB from the latest.
     * @param limit The number of heists to get from the latest heists.
     * @return A list of {@link CompletedHeist}s containing the amount supplied.
     */
    List<CompletedHeist> getLatestCompletedHeists(int limit);

}
