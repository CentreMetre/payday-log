package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a difficulty of a game.
 */
@Entity
@Table(name = "difficulties")
public class Difficulty
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * A difficulty, kept as a string.
     * Example values:
     * Normal
     * Hard
     * Very Hard
     */
    @Column(unique = true)
    @NotNull
    private String difficulty;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }
}

