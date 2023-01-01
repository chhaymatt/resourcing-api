package com.matthewchhay.resourcingapi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    // @Autowired // shows an error when uncommented
    private JobRepository repository;

    public String hello() {
        return "Hello World from Job";
    }

    public List<Job> all() {
        return this.repository.findAll();
    }

    public Optional<Job> findOne(Long jobId) {
        return this.repository.findById(jobId);
    }

    public Job create(JobCreateDTO data) {
        String cleanedName = data.name.trim();

        Job newJob = new Job(cleanedName, data.startDate, data.endDate, data.temp);

        this.repository.save(newJob);

        return newJob;
    }
}
