package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBookData;
import jakarta.validation.Valid;

import java.util.List;

public interface IAddressBookService {
    List<AddressBookData> getAddressBookData();

    AddressBookData getAddressBookDataById(int personId);

    AddressBookData createAddressBookData(AddressBookDTO addressBookDTO);

    AddressBookData updateAddressBookData(int personId,AddressBookDTO addressBookDTO);

    void deleteAddressBookData(int personId);

    List<AddressBookData> getAddressBookDataByCity(String city);

    List<AddressBookData> getAddressBookDataByState(String state);


    AddressBookData createAddressBookDataByToken(@Valid AddressBookDTO addressBookDTO);

    AddressBookData getAddressBookDataByToken(String token);

    AddressBookData updateAddressBookDataByToken(String token, @Valid AddressBookDTO addressBookDTO);

    AddressBookData deleteAddressBookDataByToken(String token);
}
