package com.maohulu.custom.pojo.validatDemo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Country {
    @NotNull
    @Size(min = 2, max = 2)
    private String iso2;
}