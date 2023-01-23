package com.example.addressbookapplication.model;

import com.example.addressbookapplication.dto.AddressBookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="address_book")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AddressBookData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String contactNo;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String pinCode;

    public AddressBookData( AddressBookDTO addressBookDTO) {
        this.updateAddressAddressBookData(addressBookDTO);
    }

    public void updateAddressAddressBookData(AddressBookDTO addressBookDTO) {
        this.firstName = addressBookDTO.getFirstName();
        this.lastName = addressBookDTO.getLastName();
        this.contactNo = addressBookDTO.getContactNo();
        this.email = addressBookDTO.getEmail();
        this.address = addressBookDTO.getAddress();
        this.city = addressBookDTO.getCity();
        this.state = addressBookDTO.getState();
        this.pinCode = addressBookDTO.getPinCode();
    }
}
