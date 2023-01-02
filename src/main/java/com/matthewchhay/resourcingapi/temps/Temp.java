package com.matthewchhay.resourcingapi.temps;

//import java.util.List;

//import com.matthewchhay.resourcingapi.jobs.Job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;

@Entity
public class Temp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    // @OneToMany(mappedBy = "job")
    // List<Job> jobs;

    @Column
    private Long job;
}
