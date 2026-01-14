package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an instance of a challenge.
 *
 * Challenge instances have a challenge, a flag saying if it was completed,
 * an optional date and time it was completed at, and optional notes.
 */
@Entity
@Table(name = "challenges_completed")
public class ChallengeInstance
{
    @Id
    private int id;

    /**
     * The challenge that was completed (or not).
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "challenge", nullable = false)
    private Challenge challenge;

    /**
     * Whether the challenge was completed.
     */
    @Column(name = "completed", nullable = false)
    private boolean isCompleted;

    /**
     * The date and time the challenge was completed at.
     */
    @Column(name = "completed_at", nullable = true)
    private LocalDateTime completedAt;

    /**
     * Optional notes.
     */
    private String notes;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Challenge getChallenge()
    {
        return challenge;
    }

    public void setChallenge(Challenge challenge)
    {
        this.challenge = challenge;
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }

    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }

    public LocalDateTime getCompletedAt()
    {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt)
    {
        this.completedAt = completedAt;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
