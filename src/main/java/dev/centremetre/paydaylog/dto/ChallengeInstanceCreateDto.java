package dev.centremetre.paydaylog.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.centremetre.paydaylog.model.ChallengeInstance}
 */
public class ChallengeInstanceCreateDto implements Serializable
{
    private int challengeId;
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

    public boolean getIsCompleted()
    {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted)
    {
        this.isCompleted = isCompleted;
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