package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.patient.Patient;
import com.github.daianaegermichels.api.patient.PatientData;
import com.github.daianaegermichels.api.patient.PatientDataList;
import com.github.daianaegermichels.api.patient.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public Page<PatientDataList> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(PatientDataList::new);
    }
}
