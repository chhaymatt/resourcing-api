package com.matthewchhay.resourcingapi.temps;

import java.util.List;

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

    public Temp() {
    }

    public Temp(String firstName, String lastName, List<Long> jobs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobs = jobs;
    }

    @Column
    private List<Long> jobs;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Long> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Long> jobs) {
        this.jobs = jobs;
    }

}
