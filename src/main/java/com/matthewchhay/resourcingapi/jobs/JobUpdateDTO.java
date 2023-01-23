package com.matthewchhay.resourcingapi.jobs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;

public class JobUpdateDTO {

    String name;
    LocalDate startDate;
    LocalDate endDate;

    @Min(1)
    Long temp;

    public JobUpdateDTO(String name, LocalDate startDate, LocalDate endDate, Long temp) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.temp = temp;
    }
}
