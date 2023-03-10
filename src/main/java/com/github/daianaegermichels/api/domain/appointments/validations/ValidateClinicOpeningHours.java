package com.github.daianaegermichels.api.domain.appointments.validations;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateClinicOpeningHours implements ValidateAppointmentsScheduling{
    public void validate(AppointmentsData data) {
        var dateAppointment = data.data();

        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var beforeTheClinicOpens = dateAppointment.getHour() < 7;

        var afterTheClinicCloses = dateAppointment.getHour() > 18;

        if (sunday || beforeTheClinicOpens || afterTheClinicCloses) {
            throw new ValidationException("Appointment outside clinic opening hours!");
        }
    }
}
