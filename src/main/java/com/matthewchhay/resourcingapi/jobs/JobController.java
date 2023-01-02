package com.matthewchhay.resourcingapi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Job> maybeJob = this.service.findOne(id);

        if (maybeJob.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no job");
        }

        return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Job> create(@Valid @RequestBody JobCreateDTO data) {
        Job createdJob = this.service.create(data);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

}
