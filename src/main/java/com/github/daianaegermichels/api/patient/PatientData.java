package com.github.daianaegermichels.api.patient;

import com.github.daianaegermichels.api.address.AddressData;
import com.github.daianaegermichels.api.physician.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\\\d{3}\\\\.?\\\\d{3}\\\\.?\\\\d{3}\\\\-?\\\\d{2}")
        String cpf,
        @NotNull @Valid AddressData address
) {
}
