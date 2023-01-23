package com.matthewchhay.resourcingapi.temps;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matthewchhay.resourcingapi.jobs.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Temp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    @OneToMany(mappedBy = "temp")
    @JsonIgnoreProperties(value = { "temp" })
    private List<Job> jobs;

    @ManyToMany
    @JsonIgnoreProperties(value = { "temps", "jobs" })
    private Set<Temp> temps;

    public Temp() {
    }

    public Temp(String firstName, String lastName, Set<Temp> temps) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.temps = temps;
    }

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

    public List<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<Temp> getTemps() {
        return this.temps;
    }

    public void setTemps(Set<Temp> temps) {
        this.temps = temps;
    }

}
