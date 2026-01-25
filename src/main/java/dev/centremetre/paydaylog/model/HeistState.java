package dev.centremetre.paydaylog.model;

/**
 * An enum for the states a heist can be in.
 * 0 = Stealth
 * 1 = Alarm
 * 2 = First Response Incoming
 * 3 = Negotiate to delay assault
 * 4 = Assault Incoming
 * 5 = Police Assault
 * 6 = Final Charge
 * 7 = FBI Assault
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
    private final String state;

    HeistState(int id, String state)
    {
        this.id = id;
        this.state = state;
    }

    public int getId()
    {
        return id;
    }

    public String getState()
    {
        return state;
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
