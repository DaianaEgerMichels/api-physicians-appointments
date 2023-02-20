package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.physician.Physician;
import com.github.daianaegermichels.api.physician.PhysicianData;
import com.github.daianaegermichels.api.physician.PhysicianDataList;
import com.github.daianaegermichels.api.physician.PhysicianRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("physicians")
public class PhysicianController {
    @Autowired
    private PhysicianRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PhysicianData data) {

        repository.save(new Physician(data));
    }

    @GetMapping
    public Page<PhysicianDataList> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(PhysicianDataList::new);
    }
}
