package com.matthewchhay.resourcingapi.jobs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matthewchhay.resourcingapi.temps.Temp;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "jobs" })
    private Temp temp;

    public Job() {
    }

    public Job(String name, LocalDate startDate, LocalDate endDate, Temp temp) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate2) {
        this.startDate = startDate2;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate2) {
        this.endDate = endDate2;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

}
