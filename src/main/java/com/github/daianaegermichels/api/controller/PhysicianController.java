package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.physician.Physician;
import com.github.daianaegermichels.api.physician.PhysicianData;
import com.github.daianaegermichels.api.physician.PhysicianRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
