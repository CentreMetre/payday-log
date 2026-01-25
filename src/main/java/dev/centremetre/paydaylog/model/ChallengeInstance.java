package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Represents an instance of a challenge.
 *
 * Challenge instances have a challenge, a flag saying if it was completed,
 * an optional date and time it was completed at, and optional notes.
 */
@Entity
@Table(name = "challenges_instances")
public class ChallengeInstance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The challenge that was completed (or not).
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "challenge", nullable = false)
    @NotNull
    private Challenge challenge;

    /**
     * Whether the challenge was completed.
     * If this is set to true, {@link #completedAt} cannot be null. This is enforced by {@link #isCompletedAtValid}.
     */
    @Column(name = "completed", nullable = false)
    @NotNull
    private boolean completed;

    /**
     * The date and time the challenge was completed at.
     */
    @Column(name = "completed_at", nullable = true)
    private LocalDateTime completedAt;

    /**
     * Optional notes.
     */
    @Column(name = "notes", nullable = true)
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
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
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

    // Constraint to make it so completedAt cannot be null if isCompleted is true.
    @AssertTrue(message = "completedAt must be set when challenge is completed")
    private boolean isCompletedAtValid() {
        return !completed || completedAt != null;
    }
}
