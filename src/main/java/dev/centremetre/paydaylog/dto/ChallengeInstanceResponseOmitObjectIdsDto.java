package dev.centremetre.paydaylog.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.centremetre.paydaylog.model.ChallengeInstance}
 */
public class ChallengeInstanceResponseOmitObjectIdsDto implements Serializable
{
    private int id;
    private String challenge;
    private boolean isCompleted;
    private LocalDateTime completedAt;
    private String notes;

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