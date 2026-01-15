package dev.centremetre.paydaylog.service;

import dev.centremetre.paydaylog.model.Heist;

import java.util.List;

public interface HeistService
{
    /**
     * Retrieve a {@link Heist} from its name. Has to be exact. See `heists` table for names.
     * @param name The name of the heist.
     * @return A {@link Heist} object with the supplied name.
     */
    Heist getHeistFromName(String name);

    /**
     * Get all heists.
     * @return A List of {@link Heist} objects containing all heists in the `heists` table in the DB.
     */
    List<Heist> getAllHeists();

    /**
     * Get all the heist names.
     * @return A list of all the heist names.
     */
    List<String> getAllHeistNames();

    /**
     * Create a new heist if a new one was released (HA, maybe in a year).
     * @param name The name of the heist.
     * @return The created heist row.
     */
    Heist createHeist(String name);
}
