package com.matthewchhay.resourcingapi.jobs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobCreateDTO {
    @NotBlank
    String name;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @Min(1)
    Long temp;

    public JobCreateDTO(String name, LocalDate startDate, LocalDate endDate, Long temp) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.temp = temp;
    }

}
