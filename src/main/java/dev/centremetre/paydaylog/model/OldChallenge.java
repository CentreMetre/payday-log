package dev.centremetre.paydaylog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "challenges_old")
@Entity
public class OldChallenge
{
    @Id
    int id;

    @Column(name = "challenge")
    String challenge;

    @Column(name = "completed_at")
    LocalDateTime completedAt;

    @Column(name = "is_completed")
    boolean isCompleted;

    @Column(name = "notes")
    String notes;

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

    public LocalDateTime getCompletedAt()
    {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt)
    {
        this.completedAt = completedAt;
    }

    public boolean isIsCompleted()
    {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed)
    {
        this.isCompleted = completed;
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
