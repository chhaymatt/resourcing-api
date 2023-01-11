package com.matthewchhay.resourcingapi.temps;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class TempCreateDTO {
    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    List<Long> jobs;

    // @OneToMany(mappedBy = "job")
    // List<Job> jobs;

    public TempCreateDTO(String firstName, String lastName, List<Long> jobs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobs = jobs;
    }

}
