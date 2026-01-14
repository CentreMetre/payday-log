package dev.centremetre.paydaylog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a heist in a game that can be played.
 *
 * Different from a playing of a heist; see {@link HeistCompleted}.
 */
@Entity
@Table(name = "heists")
public class Heist
{
    @Id
    private int id;

    private String name;
}
