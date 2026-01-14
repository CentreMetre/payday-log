package dev.centremetre.paydaylog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an in game challenge.
 *
 * Has an ID and the challenge text.
 */
@Entity
@Table(name = "challenges")
public class Challenge
{
    @Id
    int id;

    /**
     * The challenge text as shown in game.
     */
    String challenge;
}
