package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.exception.AddressBookException;
import com.example.addressbookapplication.model.AddressBookData;
import com.example.addressbookapplication.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AddressBookServiceImpl implements IAddressBookService{
    private List<AddressBookData> addressBookList = new ArrayList<>();
    @Autowired
    AddressBookRepository addressBookRepository;

    //Getting all employee List
    @Override
    public List<AddressBookData> getAddressBookData() {
        return addressBookRepository.findAll();
    }
    //Getting employee details by id
    @Override
    public AddressBookData getAddressBookDataById(int personId) {

        return addressBookRepository.findById((long) personId)
                .orElseThrow(() -> new AddressBookException("Employee Not Found"));
    }

    //Creating an employee data
    @Override
    public AddressBookData createAddressBookData(AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        addressBookData = new AddressBookData(addressBookDTO);
        return addressBookRepository.save(addressBookData);
    }

    // Updating employee data by using id
    @Override
    public AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        addressBookData.updateAddressAddressBookData(addressBookDTO);
        return addressBookRepository.save(addressBookData);
    }
    //deleting employee by id
    @Override
    public void deleteAddressBookData(int personId) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        addressBookRepository.delete(addressBookData);
    }

    // getting employee list by department
    @Override
    public List<AddressBookData> getAddressBookDataByCity(String city) {
        return addressBookRepository.findAddressByCity(city);
    }

    @Override
    public List<AddressBookData> getAddressBookDataByState(String state) {
        return addressBookRepository.findAddressByState(state);
    }
}