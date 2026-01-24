package dev.centremetre.paydaylog.dto;

import java.time.LocalDateTime;

/**
 * Used to return a {@link dev.centremetre.paydaylog.model.CompletedHeist}
 * to the client without unnecessary data like IDs (keeps the completed heist ID).
 */
public class CompletedHeistResponseOmitObjectIdsDto
{
    private int id;

    private String heistName;

    private int xpAmount;

    private boolean accurateXpAmount;

    private LocalDateTime completedAt;

    private boolean heistSuccess;

    private String heistFinishStateName;

    private boolean majorityStatePlayedStealth;

    private String difficultyName;

    private String notes;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getHeistName()
    {
        return heistName;
    }

    public void setHeistName(String heistName)
    {
        this.heistName = heistName;
    }

    public int getXpAmount()
    {
        return xpAmount;
    }

    public void setXpAmount(int xpAmount)
    {
        this.xpAmount = xpAmount;
    }

    public boolean isAccurateXpAmount()
    {
        return accurateXpAmount;
    }

    public void setAccurateXpAmount(boolean accurateXpAmount)
    {
        this.accurateXpAmount = accurateXpAmount;
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

    public String getHeistFinishStateName()
    {
        return heistFinishStateName;
    }

    public void setHeistFinishStateName(String heistFinishStateName)
    {
        this.heistFinishStateName = heistFinishStateName;
    }

    public boolean isMajorityStatePlayedStealth()
    {
        return majorityStatePlayedStealth;
    }

    public void setMajorityStatePlayedStealth(boolean majorityStatePlayedStealth)
    {
        this.majorityStatePlayedStealth = majorityStatePlayedStealth;
    }

    public String getDifficultyName()
    {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName)
    {
        this.difficultyName = difficultyName;
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
