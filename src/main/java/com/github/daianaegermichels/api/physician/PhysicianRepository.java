package com.github.daianaegermichels.api.physician;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
}
