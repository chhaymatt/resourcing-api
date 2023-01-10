package com.matthewchhay.resourcingapi.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService service;

    @GetMapping("/test")
    public String test() {
        return this.service.hello();
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAll() {
        List<Job> allJobs = this.service.all();
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findOneJob(@PathVariable Long id) {
        Job foundJob = this.service.findOne(id).get();
        return new ResponseEntity<>(foundJob, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobCreateDTO data) {
        Job createdJob = this.service.create(data);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,
            @Valid @RequestBody JobUpdateDTO data) {
        Job updatedJob = this.service.update(id, data);
        return ResponseEntity.ok(updatedJob);
    }

}
