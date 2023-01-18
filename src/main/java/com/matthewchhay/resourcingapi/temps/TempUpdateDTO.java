package com.matthewchhay.resourcingapi.temps;

import java.util.List;

public class TempUpdateDTO {
    String firstName;
    String lastName;
    List<Long> temps;

    public TempUpdateDTO(String firstName, String lastName, List<Long> temps) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.temps = temps;
    }
}
