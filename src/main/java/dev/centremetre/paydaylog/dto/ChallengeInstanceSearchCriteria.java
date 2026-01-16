package dev.centremetre.paydaylog.dto;

import java.time.LocalDateTime;

/**
 * Used for searching for specific challenges. Used to map from client request in the controller.
 */
public class ChallengeInstanceSearchCriteria
{
    /**
     * The challengeId to search by.
     */
    //Has to be integer (non-primitive) so it can be null
    Integer challengeId;

    /**
     * The latest amount to search for.
     */
    //Has to be integer (non-primitive) so it can be null
    Integer latestCount;

    //Has to be Boolean (non-primitive, not boolean) so it can be null
    Boolean isCompleted;

    /**
     * The start of the period to search for. Not required to be used with {@link #endDateTime}
     */
    LocalDateTime startDateTime;

    /**
     * The end of the period to search for. Not required to be used with {@link #startDateTime}
     */
    LocalDateTime endDateTime;
}
