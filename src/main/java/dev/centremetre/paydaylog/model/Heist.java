package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a heist in a game that can be played.
 *
 * Different from a playing of a heist; see {@link CompletedHeist}.
 */
@Entity
@Table(name = "heists")
public class Heist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
