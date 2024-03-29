package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.domain.appointments.AppointmentScheduling;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsCancelData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsData;
import com.github.daianaegermichels.api.domain.appointments.AppointmentsDetails;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name="bearer-key")
public class AppointmentsController {

    @Autowired
    private AppointmentScheduling appointmentScheduling;
    @PostMapping
    @Transactional
    public ResponseEntity toSchedule (@RequestBody @Valid AppointmentsData data) {
        var appointmentDetail = appointmentScheduling.toSchedule(data);
        return ResponseEntity.ok(appointmentDetail);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentsCancelData data) {
        appointmentScheduling.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
