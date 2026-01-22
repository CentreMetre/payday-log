package dev.centremetre.paydaylog.model;

/**
 * An enum for the states a heist can be in.
 * 0 = Stealth
 * 1 = Alarm
 * 1 = First Response Incoming
 * 2 = Negotiate to delay assault
 * 3 = Assault Incoming
 * 4 = Police Assault
 * 5 = Final Charge
 * 6 = FBI Assault
 */
public enum HeistState
{
    STEALTH(0, "Stealth"),
    ALARM(1, "Alarm"),
    FIRST_RESPONSE_INCOMING(2, "First Response Incoming"),
    NEGOTIATE_TO_DELAY_ASSAULT(3, "Negotiate To Delay Assault"),
    ASSAULT_INCOMING(4, "Assault Incoming"),
    POLICE_ASSAULT(5, "Police Assault"),
    FINAL_CHARGE(6, "Final Charge"),
    FBI_ASSAULT(7, "FBI Assault");

    private final int id;
    private final String phase;

    HeistState(int id, String phase)
    {
        this.id = id;
        this.phase = phase;
    }

    public int getId()
    {
        return id;
    }

    public String getPhase()
    {
        return phase;
    }

    public static HeistState fromId(int id)
    {
        for (HeistState phase : values()) {
            if (phase.id == id) {
                return phase;
            }
        }
        throw new IllegalArgumentException("Unknown HeistState id: " + id);
    }
}
