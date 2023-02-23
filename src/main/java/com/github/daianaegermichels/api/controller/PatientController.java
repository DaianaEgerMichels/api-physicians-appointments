package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.domain.patient.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientData data, UriComponentsBuilder uriComponentsBuilder) {
        var patient = new Patient(data);
        repository.save(patient);
        var uri = uriComponentsBuilder.path("/physicians/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetails(patient));
    }
    @GetMapping
    public ResponseEntity<Page<PatientDataList>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(PatientDataList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientDataUpdate data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInformation(data);
        return ResponseEntity.ok(new PatientDetails(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity patientById(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetails(patient));
    }
}
