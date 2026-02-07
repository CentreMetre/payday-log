package dev.centremetre.paydaylog.dto;

import dev.centremetre.paydaylog.model.Challenge;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.centremetre.paydaylog.model.ChallengeInstance}
 */
public class ChallengeInstanceCreateDto implements Serializable
{
    @NotNull(message = "A challenge needs to exist on a challenge instance to record what challenge is being stored.")
    @Min(value = 1, message = "Challenge ID must be greater than 0.")
    private int challengeId; // NOTE: not challenge instance id. Is int so cant be null anyaway. TODO: change to integer

    private boolean isCompleted;

    /**
     * Can be nullable for if the challenge isn't completed yet, instead it's just an entry of the challenge.
     */
    private LocalDateTime completedAt;

    private String notes;

    public int getChallengeId()
    {
        return challengeId;
    }

    public void setChallengeId(int challengeId)
    {
        this.challengeId = challengeId;
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