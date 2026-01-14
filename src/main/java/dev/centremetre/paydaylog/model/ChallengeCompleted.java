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
public class ChallengeCompleted
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
}
