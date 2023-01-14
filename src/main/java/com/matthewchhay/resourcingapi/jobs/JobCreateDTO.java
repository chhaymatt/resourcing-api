package com.matthewchhay.resourcingapi.jobs;

import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

public class JobCreateDTO {
    @NotBlank
    String name;

    Date startDate;

    Date endDate;

    Long temp;

    public JobCreateDTO(String name, Date startDate, Date endDate, Long temp) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.temp = temp;
    }

}
