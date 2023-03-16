package com.github.daianaegermichels.api.domain.appointments.validations.canceller;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsCancelData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateAdvanceSchedule implements ValidateAppointmentsCanceller {

    @Autowired
    private AppointmentsRepository repository;
    @Override
    public void validate(AppointmentsCancelData data) {
        var appointment = repository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getData()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Appointment can only be canceled at least 24 hours in advance!");
        }
    }
}
