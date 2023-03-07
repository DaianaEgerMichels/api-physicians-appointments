package com.github.daianaegermichels.api.domain.appointments;

import com.github.daianaegermichels.api.domain.physician.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentsData(
        Long idPhysician,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime data,

        Specialty specialty) {
}
