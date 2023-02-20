package com.github.daianaegermichels.api.physician;

public record PhysicianDataList(String name, String email, String crm, Specialty specialty) {
    public PhysicianDataList(Physician physician) {
        this(physician.getName(), physician.getEmail(), physician.getCrm(), physician.getSpecialty());
    }
}
