package com.leo.vetfind.mapper;

import com.leo.vetfind.dto.veterinarian.VeterinarianResponse;
import com.leo.vetfind.entity.Veterinarian;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeterinarianMapper {

    // entity > dto
    @Mapping(source = "user.id", target = "userId")
    VeterinarianResponse toResponseDTO(Veterinarian veterinarian);

    // Lista
    List<VeterinarianResponse> toResponseDTOList(List<Veterinarian> veterinarians);
}
