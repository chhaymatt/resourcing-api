package com.matthewchhay.resourcingapi.jobs;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.matthewchhay.resourcingapi.temps.Temp;
import com.matthewchhay.resourcingapi.temps.TempService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService service;

    @Autowired
    private TempService tempService;

    @GetMapping("/test")
    public String test() {
        return this.service.hello();
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAll(@RequestParam(required = false) Boolean assigned) {
        List<Job> allJobs = assigned == null ? this.service.all() : this.service.getJobsAssigned(assigned);
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findOneJob(@PathVariable Long id) {
        Optional<Job> maybeJob = this.service.findOne(id);

        // Find job based on id
        if (maybeJob.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No job found with id: " + id);
        }

        return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobCreateDTO data) {
        Temp temp = null;

        // Check assigning temp exists
        if (data.temp != null) {
            Optional<Temp> maybeTemp = tempService.findOne(data.temp);
            if (maybeTemp.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temp found with id: " + data.temp);
            }
            temp = maybeTemp.get();
        }

        // Check payload dates
        checkDates(data.startDate, data.endDate);

        Job createdJob = this.service.create(data, temp);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,
            @Valid @RequestBody JobUpdateDTO data) {

        // Find job based on id
        Optional<Job> maybeJob = this.service.findOne(id);
        if (maybeJob.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No job found with id: " + id);
        }

        // Get job
        Job job = maybeJob.get();

        // Find assigned temp from job otherwise it will be null from POST /jobs
        Temp temp = job.getTemp();

        // Check payload dates
        // Case 1. Replace existing startDate and endDate
        checkDates(data.startDate, data.endDate);

        // Case 2. Replace startDate and keep existing endDate
        checkDates(data.startDate, job.getEndDate());

        // Case 3. Replace endDate and keep existing startDate
        checkDates(job.getStartDate(), data.endDate);

        // Case 4. No changes - do nothing

        // Check assigning temp exists
        if (data.temp != null) {
            Optional<Temp> maybeTemp = tempService.findOne(data.temp);
            if (maybeTemp.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temp found with id: " + data.temp);
            }
            temp = maybeTemp.get();
        }

        // TO DO: Check temps existing jobs date ranges does not conflict with this job

        Job updatedJob = this.service.update(id, data, job, temp);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    public void checkDates(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be before end date");
        }
    }

}
