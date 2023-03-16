package com.github.daianaegermichels.api.domain.appointments.validations.scheduling;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsRepository;
import com.github.daianaegermichels.api.domain.appointments.validations.scheduling.ValidateAppointmentsScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePhysicianWithAnotherAppointmentAtTheSameTime implements ValidateAppointmentsScheduling {

    @Autowired
    private AppointmentsRepository repository;

    public void validate(AppointmentsData data) {
        var physicianHasAnotherAppointmentAtTheSameTime = repository.existsByPhysicianIdAndDataAndReasonIsNull(data.idPhysician(), data.data());
        if(physicianHasAnotherAppointmentAtTheSameTime) {
            throw new ValidationException("Physician already had another consultation at the same time!");
        }
    }
}
