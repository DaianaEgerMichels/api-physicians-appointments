package com.github.daianaegermichels.api.domain.appointments.validations;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePhysicianWithAnotherAppointmentAtTheSameTime implements ValidateAppointmentsScheduling {

    @Autowired
    private AppointmentsRepository repository;

    public void validate(AppointmentsData data) {
        var physicianHasAnotherAppointmentAtTheSameTime = repository.existsByPhysicianIdAndData(data.idPhysician(), data.data());
        if(physicianHasAnotherAppointmentAtTheSameTime) {
            throw new ValidationException("Physician already had another consultation at the same time!");
        }
    }
}
