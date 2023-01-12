package com.matthewchhay.resourcingapi.temps;

import jakarta.validation.constraints.NotBlank;

public class TempCreateDTO {
    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    public TempCreateDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
