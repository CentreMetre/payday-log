package dev.centremetre.paydaylog.model;

import jakarta.persistence.Column;
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
    private int id;

    /**
     * The challenge text as shown in game.
     */
    @Column(unique = true)
    private String challenge;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getChallenge()
    {
        return challenge;
    }

    public void setChallenge(String challenge)
    {
        this.challenge = challenge;
    }
}
