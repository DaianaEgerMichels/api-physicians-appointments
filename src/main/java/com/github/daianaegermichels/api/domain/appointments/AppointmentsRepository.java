package com.github.daianaegermichels.api.domain.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    boolean existsByPhysicianIdAndDataAndCancelReasonIsNull(Long idPhysician, LocalDateTime data);

    boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
