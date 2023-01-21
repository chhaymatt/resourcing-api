package com.matthewchhay.resourcingapi.jobs;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matthewchhay.resourcingapi.temps.Temp;

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
        return maybeJob;
    }

    public Job create(JobCreateDTO data, Temp temp) {
        Job newJob = new Job(data.name.trim(), data.startDate, data.endDate, temp);
        return this.repository.save(newJob);
    }

    public Job update(Long jobId, JobUpdateDTO data, Job job, Temp temp) {
        if (data.name != null) {
            job.setName(data.name.trim());
        }

        if (data.startDate != null) {
            job.setStartDate(data.startDate);
        }

        if (data.endDate != null) {
            job.setEndDate(data.endDate);
        }

        if (temp != null) {
            job.setTemp(temp);
        }

        return this.repository.save(job);
    }

    public List<Job> getJobsAssigned(Boolean assigned) {
        return assigned ? repository.findAllByTempIsNotNull() : repository.findAllByTempIsNull();
    }
}
