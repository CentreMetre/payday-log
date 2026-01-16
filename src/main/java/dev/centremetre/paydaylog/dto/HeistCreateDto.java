package dev.centremetre.paydaylog.dto;

/**
 * Used to create a new heist. Needed to map from request body.
 */
public class HeistCreateDto
{
    private String heistName;

    public String getHeistName()
    {
        return heistName;
    }

    public void setHeistName(String heistName)
    {
        this.heistName = heistName;
    }
}
