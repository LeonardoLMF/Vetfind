package com.leo.vetfind.dto.veterinario;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateVeterinarioRequestDTO {

    @NotBlank(message = "O CRMV é obrigatório")
    private String crmv;

}
