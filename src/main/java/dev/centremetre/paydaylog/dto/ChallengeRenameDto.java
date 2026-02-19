package dev.centremetre.paydaylog.dto;

import jakarta.validation.constraints.NotNull;

public class ChallengeRenameDto
{
    @NotNull
    String oldText;

    @NotNull(message = "New text needed to rename challenge.")
    String newText;

    public String getOldText()
    {
        return oldText;
    }

    public void setOldText(String oldText)
    {
        this.oldText = oldText;
    }

    public String getNewText()
    {
        return newText;
    }

    public void setNewText(String newText)
    {
        this.newText = newText;
    }
}
