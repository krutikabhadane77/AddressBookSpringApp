package com.example.addressbookapplication.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

@Data
public class AddressBookDTO {
    @Nullable
    private int id;

    @NonNull
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "first name invalid")
    private String firstName;

    @NonNull
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "last name invalid")
    private String lastName;

    @Pattern(regexp="^[6-9][0-9]{9}$",message = "contact number should be 10 digits")
    private String contactNo;

    @NotBlank(message = "email should not be empty")
    @Pattern(regexp= "^[a-zA-Z0-9]{3,}([\\\\.\\\\+\\\\-]?[a-zA-Z0-9]{3,})?[@][A-Za-z0-9]{1,}[.][A-Za-z]{2,4}[,]?([.][A-Za-z]{2,4}[.]?)?$",message="email should not be empty")
    private String email;

    @NotBlank(message = "address should not be empty")
    private String address;

    @NotBlank(message = "city should not be empty")
    private String city;

    @NotBlank(message = "state should not be empty")
    private String state;

    @Pattern(regexp="^[1-9]{1}[0-9]{5}$" ,message = "zip should be 6 digits")
    private String pinCode;

}
