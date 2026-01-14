package dev.centremetre.paydaylog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a difficulty of a game.
 */
@Entity
@Table(name = "difficulties")
public class Difficulty
{
    @Id
    private int id;

    /**
     * A difficulty, kept as a string.
     * Example values:
     * Normal
     * Hard
     * Very Hard
     */
    private String difficulty;
}
