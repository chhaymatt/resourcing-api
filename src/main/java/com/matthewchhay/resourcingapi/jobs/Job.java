package com.matthewchhay.resourcingapi.jobs;

import java.sql.Date;
import java.util.List;

import com.matthewchhay.resourcingapi.temps.Temp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    @Column
    Date startDate;

    @Column
    Date endDate;

    // @OneToOne(mappedBy = "temp")
    // List<Temp> temps;
}
