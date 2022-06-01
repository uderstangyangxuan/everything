package com.maohulu.custom.annatation;

import com.maohulu.custom.pojo.validatDemo.Address;
import com.maohulu.custom.pojo.validatDemo.Country;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiCountryAddressValidator implements ConstraintValidator<ValidAddress, Address> {

    public void initialize(ValidAddress constraintAnnotation) {

    }

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {

        Country country = address.getCountry();
        if (country == null || country.getIso2() == null || address.getZipCode() == null) {
            return true;
        }

        switch (country.getIso2()) {
            case "FR":
//                return // Check if address.getZipCode() is valid for France
            case "GR":
//                return // Check if address.getZipCode() is valid for Greece
            default:
                return true;
        }
    }
}