package dev.centremetre.paydaylog.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter class to convert from and to {@link HeistState}.
 */
@Converter(autoApply = true)
public class HeistStateConverter implements AttributeConverter<HeistState, Integer>
{
    /**
     * Converts to the ID for use in storage.
     * @param heistState The {@link HeistState} ENUM to convert from.
     * @return An {@code Integer} that belongs to the HeistState provided.
     */
    @Override
    public Integer convertToDatabaseColumn(HeistState heistState)
    {
        if (heistState == null)
        {
            throw new IllegalArgumentException("heistState cannot be null.");
        }
        return heistState.getId();
    }

    /**
     * Converts an {@code Integer} to a {@link HeistState} with the ID provided.
     * @param id The ID that belongs to the HeistState to retrieve.
     * @return {@link HeistState} That has the same ID as the ID provided.
     */
    @Override
    public HeistState convertToEntityAttribute(Integer id)
    {
        return HeistState.fromId(id);
    }
}
