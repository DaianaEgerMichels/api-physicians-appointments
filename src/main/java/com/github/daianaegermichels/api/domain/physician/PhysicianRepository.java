package com.github.daianaegermichels.api.domain.physician;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Page<Physician> findAllByActiveTrue(Pageable pageable);
}
