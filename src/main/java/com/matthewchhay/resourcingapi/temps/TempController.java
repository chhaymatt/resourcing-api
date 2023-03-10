package com.matthewchhay.resourcingapi.temps;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.matthewchhay.resourcingapi.jobs.Job;
import com.matthewchhay.resourcingapi.jobs.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempService service;

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Temp>> getTemps(@RequestParam(required = false) Long jobId) {
        // Find all temps if no job is specified
        if (jobId == null) {
            List<Temp> allTemps = this.service.all();
            return new ResponseEntity<>(allTemps, HttpStatus.OK);
        }

        // Find job based on id
        Optional<Job> maybeJob = jobService.findOne(jobId);
        if (maybeJob.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No job found with id: " + jobId);
        }
        Job job = maybeJob.get();

        if (job.getTemp() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A temp is already assigned to job" + jobId);
        }

        LocalDate startDate = job.getStartDate();
        LocalDate endDate = job.getEndDate();

        List<Temp> allFreeTemps = this.service.all().stream()
                .filter(temp -> !this.service.isTempJobConflict(temp, startDate, endDate))
                .collect(Collectors.toList());

        return new ResponseEntity<>(allFreeTemps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> findOneTemp(@PathVariable Long id) {
        Optional<Temp> maybeTemp = this.service.findOne(id);
        if (maybeTemp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temp found with id: " + id);
        }
        return new ResponseEntity<>(maybeTemp.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Temp> createTemp(@Valid @RequestBody TempCreateDTO data) {
        Set<Temp> temps = findTempSet(data.temps);
        Temp createdTemp = this.service.create(data, temps);
        return new ResponseEntity<>(createdTemp, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Temp> updateTemp(@PathVariable Long id,
            @Valid @RequestBody TempUpdateDTO data) {

        Optional<Temp> maybeTemp = this.service.findOne(id);
        if (maybeTemp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temp found with id: " + id);
        }

        Set<Temp> temps = findTempSet(data.temps);
        for (Long tempId : data.temps) {
            if (tempId == maybeTemp.get().getId()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Cannot assign temp id to itself");
            }
        }
        Temp updatedTemp = this.service.update(id, data, maybeTemp.get(), temps);
        return new ResponseEntity<>(updatedTemp, HttpStatus.OK);
    }

    // Find each Temp based on data.temps List<Long> = [tempId_1, tempId_2, ...]
    // Also return temps to null if array is empty
    public Set<Temp> findTempSet(List<Long> temps) {
        if (temps != null && temps.size() > 0) {
            return temps.stream().map(tempId -> this.service.findOne(tempId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No temp found with id: " + tempId)))
                    .collect(Collectors.toSet());
        }
        return null;
    }

}
