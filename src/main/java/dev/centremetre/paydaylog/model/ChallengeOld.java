package dev.centremetre.paydaylog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "challenges_old")
@Entity
public class ChallengeOld
{
    @Id
    int id;

    @Column(name = "challenge")
    String challenge;

    @Column(name = "completed_at")
    LocalDateTime completedAt;

    @Column(name = "is_completed")
    boolean completed;

    @Column(name = "notes")
    String notes;
}
