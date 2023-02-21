package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.physician.*;
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
@RequestMapping("physicians")
public class PhysicianController {
    @Autowired
    private PhysicianRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PhysicianData data, UriComponentsBuilder uriComponentsBuilder) {
        var physician = new Physician(data);
        repository.save(physician);
        var uri = uriComponentsBuilder.path("/physicians/{id}").buildAndExpand(physician.getId()).toUri();
        return ResponseEntity.created(uri).body(new PhysicianDetails(physician));
    }

    @GetMapping
    public ResponseEntity<Page<PhysicianDataList>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(PhysicianDataList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PhysicianDataUpdate data) {
        var physician = repository.getReferenceById(data.id());
        physician.updateInformation(data);
        return ResponseEntity.ok(new PhysicianDetails(physician));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var physician = repository.getReferenceById(id);
        physician.delete();
        return ResponseEntity.noContent().build();
    }
}
