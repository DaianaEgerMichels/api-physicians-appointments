package com.github.daianaegermichels.api.domain.appointments.validations.scheduling;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.validations.scheduling.ValidateAppointmentsScheduling;
import com.github.daianaegermichels.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientActive implements ValidateAppointmentsScheduling {

    @Autowired
    private PatientRepository repository;
    public void validate(AppointmentsData data) {
        var patientIsActive = repository.findActiveById(data.idPatient());
        if (!patientIsActive) {
            throw new ValidationException("Appointment cannot be scheduled with excluded patient!");
        }
    }

}
