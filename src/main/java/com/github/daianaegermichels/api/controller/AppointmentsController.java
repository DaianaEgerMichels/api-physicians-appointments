package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.domain.appointments.AppointmentScheduling;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentScheduling appointmentScheduling;
    @PostMapping
    @Transactional
    public ResponseEntity toSchedule (@RequestBody @Valid AppointmentsData data) {
        System.out.println(data);
        appointmentScheduling.toSchedule(data);
        return ResponseEntity.ok(new AppointmentsDetails(null, null, null, null));

    }
}
