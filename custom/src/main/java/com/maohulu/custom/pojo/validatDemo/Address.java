package com.maohulu.custom.pojo.validatDemo;

import com.maohulu.custom.annatation.ValidAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author huliu
 * @date 16:49 2022/5/31
 */
@Data
@ValidAddress
public class Address {

    @NotNull
    @Size(max = 50)
    private String street1;

    @Size(max = 50)
    private String street2;

    @NotNull
    @Size(max = 10)
    private String zipCode;

    @NotNull
    @Size(max = 20)
    private String city;

    @Valid
    @NotNull
    private Country country;
}