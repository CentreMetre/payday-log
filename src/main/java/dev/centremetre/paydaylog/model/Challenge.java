package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The challenge text as shown in game.
     */
    @Column(unique = true, nullable = false)
    @NotNull
    private String challenge;

    @OneToOne
    @JoinColumn(name = "renamed_to")
    private Challenge renamedTo;

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
