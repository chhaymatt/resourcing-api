package com.matthewchhay.resourcingapi.temps;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matthewchhay.resourcingapi.jobs.Job;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {
    @Autowired
    private TempRepository repository;

    public List<Temp> all() {
        return this.repository.findAll();
    }

    public Optional<Temp> findOne(Long TempId) {
        Optional<Temp> maybeTemp = this.repository.findById(TempId);
        return maybeTemp;
    }

    public Temp create(TempCreateDTO data, Set<Temp> temps) {
        String cleanedFirstName = data.firstName.trim();
        String cleanedLastName = data.lastName.trim();
        Temp newTemp = new Temp(cleanedFirstName, cleanedLastName, temps);
        return this.repository.save(newTemp);
    }

    public Temp update(Long TempId, TempUpdateDTO data, Temp temp, Set<Temp> temps) {
        if (data.firstName != null) {
            String cleanedFirstName = data.firstName.trim();
            temp.setFirstName(cleanedFirstName);
        }

        if (data.lastName != null) {
            String cleanedLastName = data.lastName.trim();
            temp.setLastName(cleanedLastName);
        }

        if (temps != null) {
            temp.setTemps(temps);
        }

        return this.repository.save(temp);
    }

    public boolean isTempJobConflict(Temp temp, LocalDate startDate, LocalDate endDate) {
        List<Job> tempJobs = temp.getJobs();

        for (Job tempJob : tempJobs) {
            if (startDate.isBefore(tempJob.getEndDate()) && endDate.isAfter(tempJob.getStartDate())) {
                return true;
            }
            if (startDate.isEqual(tempJob.getStartDate()) || endDate.isEqual(tempJob.getEndDate())) {
                return true;
            }
            if (startDate.isEqual(tempJob.getEndDate()) || endDate.isEqual(tempJob.getStartDate())) {
                return true;
            }
            if (startDate.isBefore(tempJob.getStartDate()) && endDate.isAfter(tempJob.getEndDate())) {
                return true;
            }
        }
        return false;
    }

}
