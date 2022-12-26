package com.matthewchhay.resourcingapi.jobs;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public class JobCreateDTO {
    @NotBlank
    String name;

    @NotBlank
    Date startDate;

    @NotBlank
    Date endDate;

    Long temp;
}
