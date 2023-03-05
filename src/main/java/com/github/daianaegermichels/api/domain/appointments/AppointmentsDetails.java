package com.github.daianaegermichels.api.domain.appointments;

import java.time.LocalDateTime;

public record AppointmentsDetails(Long id, Long idPhysician, Long idPatient, LocalDateTime data) {
}
