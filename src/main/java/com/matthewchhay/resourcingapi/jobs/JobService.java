package com.matthewchhay.resourcingapi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        // Job job = this.findOne(jobId).get();
        Optional<Job> existingJob = repository.findById(jobId);

        if (existingJob.isPresent()) {
            Job job = existingJob.get();

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
        return null;

    }

    // public Job update(Long jobId, JobUpdateDTO data) {
    // Optional<Job> existingJob = repository.findById(jobId);

    // if (existingJob.isPresent()) {
    // data.forEach((key, value) -> {
    // Field field = ReflectionUtils.findField(Job.class, key);
    // field.setAccessible(true);
    // ReflectionUtils.setField(field, existingJob.get(), value);
    // });
    // return repository.save(existingJob.get());
    // }
    // return null;
    // }

}
