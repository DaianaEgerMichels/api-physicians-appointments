package com.github.daianaegermichels.api.domain.appointments.validations;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateAdvanceTime implements ValidateAppointmentsScheduling {
    public void validate(AppointmentsData data) {
        var dateAppointment = data.data();

        var now = LocalDateTime.now();

        var differenceInMinutes = Duration.between(now, dateAppointment).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Appointment must be scheduled at least 30 minutes in advance!");
        }
    }
}
