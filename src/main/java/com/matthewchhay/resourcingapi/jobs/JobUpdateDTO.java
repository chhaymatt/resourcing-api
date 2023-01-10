package com.matthewchhay.resourcingapi.jobs;

import java.sql.Date;

public class JobUpdateDTO {

    String name;
    Date startDate;
    Date endDate;
    Long temp;

    public JobUpdateDTO(String name, Date startDate, Date endDate, Long temp) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.temp = temp;
    }
}
