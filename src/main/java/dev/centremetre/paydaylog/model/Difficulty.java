package dev.centremetre.paydaylog.model;

import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String difficulty;
}
