package com.github.daianaegermichels.api.domain.physician;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Page<Physician> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT p FROM Physicians p 
            WHERE p.active = 1
            AND p.specialty = :specialty
            AND p.id NOT IN ( SELECT a.physician.id Appointments a WHERE a.data = :data )
            ORDER BY rand()
            LIMIT 1
            """)
    Physician chooseFreeRandomPhysicianOnDate(Specialty specialty, LocalDateTime data);
}
