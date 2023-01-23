package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBookData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAddressBookService {
    List<AddressBookData> getAddressBookData();

    AddressBookData getAddressBookDataById(int personId);

    AddressBookData createAddressBookData(AddressBookDTO addressBookDTO);

    AddressBookData updateAddressBookData(int personId,AddressBookDTO addressBookDTO);

    void deleteAddressBookData(int personId);

    List<AddressBookData> getAddressBookDataByCity(String city);

    List<AddressBookData> getAddressBookDataByState(String state);
}
