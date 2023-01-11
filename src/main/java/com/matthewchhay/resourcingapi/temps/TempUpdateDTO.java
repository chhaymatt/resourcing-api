package com.matthewchhay.resourcingapi.temps;

import java.util.List;

public class TempUpdateDTO {
    String firstName;
    String lastName;
    List<Long> jobs;

    // @OneToMany(mappedBy = "job")
    // List<Job> jobs;

    public TempUpdateDTO(String firstName, String lastName, List<Long> jobs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobs = jobs;
    }
}
