package com.github.daianaegermichels.api.domain.appointments.validations.scheduling;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.validations.scheduling.ValidateAppointmentsScheduling;
import com.github.daianaegermichels.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePhysicianActive implements ValidateAppointmentsScheduling {

    @Autowired
    private PhysicianRepository repository;
    public void validate(AppointmentsData data) {
        if (data.idPhysician() == null) {
            return;
        }
        var physicianIsActive = repository.findActiveById(data.idPhysician());
        if (!physicianIsActive) {
            throw new ValidationException("Appointment cannot be scheduled with an inactive physician!");
        }
    }
}
