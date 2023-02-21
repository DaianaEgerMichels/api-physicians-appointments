package com.github.daianaegermichels.api.patient;

import com.github.daianaegermichels.api.address.Address;

public record PatientDetails(Long id, String name, String email, String cpf, String phone, Address address) {
    public PatientDetails(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
