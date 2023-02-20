package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.physician.PhysicianData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("physicians")
public class PhysicianController {
    @PostMapping
    public void register(@RequestBody PhysicianData data) {
        System.out.println(data);
    }
}
