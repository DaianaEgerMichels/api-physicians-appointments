package com.github.daianaegermichels.api.domain.appointments;

import jakarta.validation.constraints.NotNull;

public record AppointmentsCancelData(
        @NotNull
        Long idAppointment,

        @NotNull
        CancellationReason reason
) {
}
