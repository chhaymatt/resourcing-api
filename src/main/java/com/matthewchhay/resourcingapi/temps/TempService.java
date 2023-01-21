package com.matthewchhay.resourcingapi.temps;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Temp create(TempCreateDTO data, List<Temp> temps) {
        String cleanedFirstName = data.firstName.trim();
        String cleanedLastName = data.lastName.trim();
        Temp newTemp = new Temp(cleanedFirstName, cleanedLastName, temps);
        return this.repository.save(newTemp);
    }

    public Temp update(Long TempId, TempUpdateDTO data, Temp temp, List<Temp> temps) {
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
}
