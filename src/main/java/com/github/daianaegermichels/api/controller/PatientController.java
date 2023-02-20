package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.patient.Patient;
import com.github.daianaegermichels.api.patient.PatientData;
import com.github.daianaegermichels.api.patient.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientData data) {

        repository.save(new Patient(data));
    }
}
