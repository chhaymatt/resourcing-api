package com.matthewchhay.resourcingapi.temps;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class TempCreateDTO {
    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    List<Long> temps;

    public TempCreateDTO(String firstName, String lastName, List<Long> temps) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.temps = temps;
    }

}
