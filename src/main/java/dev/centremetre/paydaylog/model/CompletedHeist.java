package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Represents an instance of a heist being played.
 *
 * Has an XP Amount, a flag for if the XP Amount entered was exact/accurate, a {@link Heist},
 * a date and time for when it was completed, a flag for if the heist was successful or failed,
 */
@Entity
@Table(name = "heists_completed")
public class CompletedHeist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The amount of XP gained on finishing the heist.
     */
    @Column(name = "xp_amount", nullable = false)
    @NotNull
    private int xpAmount;

    /**
     * Whether the XP amount entered was exact/accurate, in case of the number not being seen.
     */
    @Column(name = "accurate_xp_input", nullable = false)
    @NotNull
    private boolean isAccurateXpInput;

    /**
     * The heist that was played.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "heist", nullable = false, columnDefinition = "SMALLINT")
    @NotNull
    private Heist heist;

    /**
     * The date and time the heist was finished at.
     */
    @Column(name = "completed_at", nullable = false, columnDefinition = "TIMESTAMP(3)")
    @NotNull
    private LocalDateTime completedAt;

    /**
     * If the heist succeeded or failed.
     */
    @Column(name = "heist_success", nullable = false)
    @NotNull
    private boolean heistSuccess;

    /**
     * What state the heist/alarm was in at escape.
     * TODO: See if xp gain is different for pre assault negotiation, assault incoming, and between-assault negotiation
     * Converter functionality provided by  {@link HeistStateConverter}, with autoApply set to true.
     */
    @Column(name = "heist_finish_state", nullable = false, columnDefinition = "SMALLINT") // SMALLINT to save space.
    private HeistState heistFinishState;

    /**
     * Whether all bags were secured in the heist. Info should be got from the completion screen. Can be true even if heist fails.
     */
    @Column(name = "all_bags_secured", nullable = false)
    @NotNull
    private boolean allBagsSecured;

    /**
     * The difficulty the heist was played in.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "difficulty", nullable = false, columnDefinition = "SMALLINT") // SMALLINT to save space.
    @NotNull
    private Difficulty difficulty;

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

    public int getXpAmount()
    {
        return xpAmount;
    }

    public void setXpAmount(int xpAmount)
    {
        this.xpAmount = xpAmount;
    }

    public boolean isAccurateXpInput()
    {
        return isAccurateXpInput;
    }

    public void setAccurateXpInput(boolean accurateXpInput)
    {
        isAccurateXpInput = accurateXpInput;
    }

    public Heist getHeist()
    {
        return heist;
    }

    public void setHeist(Heist heist)
    {
        this.heist = heist;
    }

    public LocalDateTime getCompletedAt()
    {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt)
    {
        this.completedAt = completedAt;
    }

    public boolean isHeistSuccess()
    {
        return heistSuccess;
    }

    public void setHeistSuccess(boolean heistSuccess)
    {
        this.heistSuccess = heistSuccess;
    }

    public HeistState getHeistFinishState()
    {
        return heistFinishState;
    }

    public void setHeistFinishState(HeistState heistFinishState)
    {
        this.heistFinishState = heistFinishState;
    }

    public boolean isAllBagsSecured()
    {
        return allBagsSecured;
    }

    public void setAllBagsSecured(boolean allBagsSecured)
    {
        this.allBagsSecured = allBagsSecured;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
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