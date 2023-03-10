package com.github.daianaegermichels.api.domain.appointments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    boolean existsByPhysicianIdAndData(Long idPhysician, LocalDateTime data);

    boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
