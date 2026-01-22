package com.leo.vetfind.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Address information")
public class AddressDTO {

    @NotBlank(message = "Street is required")
    @Schema(description = "Street name", example = "Av. Leonardo")
    private String street;

    @NotBlank(message = "Number is required")
    @Schema(description = "Street number", example = "10")
    private String number;

    @Schema(description = "Complement (optional)", example = "Apto 67")
    private String complement;

    @NotBlank(message = "Neighborhood is required")
    @Schema(description = "Neighborhood", example = "Padre Anchieta")
    private String neighborhood;

    @NotBlank(message = "City is required")
    @Schema(description = "City name", example = "São Paulo")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "[A-Z]{2}", message = "State must be a valid UF ( SP, RJ)")
    @Schema(description = "State (UF)", example = "SP", maxLength = 2)
    private String state;

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "\\d{8}", message = "ZIP code must have 8 digits")
    @Schema(description = "ZIP code (CEP) without hyphen", example = "13010100", maxLength = 8)
    private String zipCode;

}
