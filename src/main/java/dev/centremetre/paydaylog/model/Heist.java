package dev.centremetre.paydaylog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private int id;

    @Column(unique = true)
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
