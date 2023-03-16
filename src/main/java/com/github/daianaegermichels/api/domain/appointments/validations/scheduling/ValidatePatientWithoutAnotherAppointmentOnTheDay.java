package com.github.daianaegermichels.api.domain.appointments.validations.scheduling;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsRepository;
import com.github.daianaegermichels.api.domain.appointments.validations.scheduling.ValidateAppointmentsScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientWithoutAnotherAppointmentOnTheDay implements ValidateAppointmentsScheduling {
    @Autowired
    private AppointmentsRepository repository;

    public void validate(AppointmentsData data) {
        var firstTime = data.data().withHour(7);
        var lastTime = data.data().withHour(18);

        var patientHasAnotherAppointmentAtTheSameDay = repository.existsByPatientIdAndDataBetween(data.idPatient(), firstTime, lastTime);
        if(patientHasAnotherAppointmentAtTheSameDay) {
            throw new ValidationException("Patient already has an appointment scheduled on this day!");
        }
    }
}
