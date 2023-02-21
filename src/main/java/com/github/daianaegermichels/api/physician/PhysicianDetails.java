package com.github.daianaegermichels.api.physician;

import com.github.daianaegermichels.api.address.Address;

public record PhysicianDetails(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {
    public PhysicianDetails(Physician physician){
            this(physician.getId(), physician.getName(), physician.getEmail(), physician.getCrm(), physician.getPhone(), physician.getSpecialty(), physician.getAddress());
    }
}
