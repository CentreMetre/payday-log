package dev.centremetre.paydaylog.mapper;

import dev.centremetre.paydaylog.dto.ChallengeInstanceResponseOmitObjectIdsDto;
import dev.centremetre.paydaylog.model.ChallengeInstance;

import java.util.ArrayList;
import java.util.List;

public class ChallengeInstanceMapper
{
    public static ChallengeInstanceResponseOmitObjectIdsDto modelToChallengeInstanceResponseOmitObjectIdsDto(
            ChallengeInstance modelInstance)
    {
        ChallengeInstanceResponseOmitObjectIdsDto dto = new ChallengeInstanceResponseOmitObjectIdsDto();

        dto.setId(modelInstance.getId());
        dto.setChallenge(modelInstance.getChallenge().getChallenge());
        dto.setIsCompleted(modelInstance.isCompleted());
        dto.setCompletedAt(modelInstance.getCompletedAt());
        dto.setNotes(modelInstance.getNotes());

        return dto;
    }

    public static List<ChallengeInstanceResponseOmitObjectIdsDto> modelListToChallengeInstanceResponseOmitObjectIdsDto(
            List<ChallengeInstance> modelInstances)
    {
        List<ChallengeInstanceResponseOmitObjectIdsDto> dtoList = new ArrayList<>();

        for (ChallengeInstance modelInstance : modelInstances)
        {
            ChallengeInstanceResponseOmitObjectIdsDto dto =
                    modelToChallengeInstanceResponseOmitObjectIdsDto(modelInstance);

            dtoList.add(dto);
        }

        return dtoList;
    }
}
