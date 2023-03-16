package com.github.daianaegermichels.api.domain.physician;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Page<Physician> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT p FROM Physician p 
            WHERE 
            p.active = 1 
            AND 
            p.specialty = :specialty 
            AND 
            p.id NOT IN ( 
            SELECT a.physician.id FROM Appointments a 
            WHERE a.data = :data 
            AND 
            a.reason IS NULL
            ) 
            ORDER BY RAND() 
            LIMIT 1
            """)
    Physician chooseFreeRandomPhysicianOnDate(Specialty specialty, LocalDateTime data);

    @Query("""
            SELECT p.active FROM Physician p WHERE p.id = :idPhysician
            """)
    Boolean findActiveById(Long idPhysician);
}
