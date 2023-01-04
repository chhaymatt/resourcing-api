package com.matthewchhay.resourcingapi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository repository;

    public String hello() {
        return "Hello World from Job";
    }

    public List<Job> all() {
        return this.repository.findAll();
    }

    public Optional<Job> findOne(Long jobId) {
        Optional<Job> maybeJob = this.repository.findById(jobId);
        if (maybeJob.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no job");
        }
        return maybeJob;
    }

    public Job create(JobCreateDTO data) {
        String cleanedName = data.name.trim();

        Job newJob = new Job(cleanedName, data.startDate, data.endDate, data.temp);

        this.repository.save(newJob);

        return newJob;
    }

    public Job update(Long jobId, JobUpdateDTO data) {
        // Finds existing job based on findOne method above
        Job job = this.findOne(jobId).get();

        if (data.name != null) {
            String cleanedName = data.name.trim();
            job.setName(cleanedName);
        }

        if (data.startDate != null) {
            job.setStartDate(data.startDate);
        }

        if (data.endDate != null) {
            job.setEndDate(data.endDate);
        }

        if (data.temp != null) {
            job.setTemp(data.temp);
        }

        return this.repository.save(job);
    }
}
