package com.matthewchhay.resourcingapi.temps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempService service;

    @GetMapping
    public ResponseEntity<List<Temp>> getAll() {
        List<Temp> allTemps = this.service.all();
        return new ResponseEntity<>(allTemps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> findOneTemp(@PathVariable Long id) {
        Temp foundTemp = this.service.findOne(id).get();
        return new ResponseEntity<>(foundTemp, HttpStatus.OK);
    }

    @GetMapping("/tree")
    public ResponseEntity<List<Temp>> getTree() {
        List<Temp> allTemps = this.service.all();
        return new ResponseEntity<>(allTemps, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Temp> createTemp(@Valid @RequestBody TempCreateDTO data) {
        Temp createdTemp = this.service.create(data);
        return new ResponseEntity<>(createdTemp, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Temp> updateTemp(@PathVariable Long id,
            @Valid @RequestBody TempUpdateDTO data) {
        Temp updatedTemp = this.service.update(id, data);
        return ResponseEntity.ok(updatedTemp);
    }

}
