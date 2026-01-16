package dev.centremetre.paydaylog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChallengeCreateDto
{
    @NotNull
    @NotEmpty
    String challenge;

    public String getChallenge()
    {
        return challenge;
    }

    public void setChallenge(String challenge)
    {
        this.challenge = challenge;
    }
}
