package com.matthewchhay.resourcingapi.temps;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (maybeTemp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no temp");
        }
        return maybeTemp;
    }

    public Temp create(TempCreateDTO data) {
        String cleanedFirstName = data.firstName.trim();
        String cleanedLastName = data.lastName.trim();
        Temp newTemp = new Temp(cleanedFirstName, cleanedLastName);
        return this.repository.save(newTemp);
    }

    public Temp update(Long TempId, TempUpdateDTO data) {
        Temp Temp = this.findOne(TempId).get();

        if (data.firstName != null) {
            String cleanedFirstName = data.firstName.trim();
            Temp.setFirstName(cleanedFirstName);
        }

        if (data.lastName != null) {
            String cleanedLastName = data.lastName.trim();
            Temp.setLastName(cleanedLastName);
        }

        return this.repository.save(Temp);
    }
}
