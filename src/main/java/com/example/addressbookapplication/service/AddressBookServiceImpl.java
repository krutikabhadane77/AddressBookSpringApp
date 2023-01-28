package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.exception.AddressBookException;
import com.example.addressbookapplication.model.AddressBookData;
import com.example.addressbookapplication.repository.AddressBookRepository;
import com.example.addressbookapplication.util.EmailSenderService;
import com.example.addressbookapplication.util.TokenUtil;
import jakarta.validation.Valid;
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
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;
    //Getting all address book list
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

    //Creating an address book data
    @Override
    public AddressBookData createAddressBookData(AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        addressBookData = new AddressBookData(addressBookDTO);
        emailSenderService.sendEmail(addressBookData.getEmail(),"Test Mail", "Hii...."+addressBookData.getFirstName()+
                " your details are added!\n" +
                "\n First Name:  "+addressBookData.getFirstName() +
                "\n Last Name:  "+addressBookData.getLastName() +
                "\n Phone number:  "+addressBookData.getContactNo()+
                "\n Email:  "+addressBookData.getEmail()+
                "\n Address:  "+addressBookData.getAddress()+
                "\n City:  "+addressBookData.getCity()+
                "\n State:  "+addressBookData.getState()+
                "\n ZipCode:  "+addressBookData.getPinCode());
        return addressBookRepository.save(addressBookData);
    }

    // Updating address book data by using id
    @Override
    public AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        addressBookData.updateAddressAddressBookData(addressBookDTO);
        emailSenderService.sendEmail(addressBookData.getEmail(),"Test Mail", "Hii...."+addressBookData.getFirstName()+
                " your details are updated!\n" +
                "\n First Name:  "+addressBookData.getFirstName() +
                "\n Last Name:  "+addressBookData.getLastName() +
                "\n Phone number:  "+addressBookData.getContactNo()+
                "\n Email:  "+addressBookData.getEmail()+
                "\n Address:  "+addressBookData.getAddress()+
                "\n City:  "+addressBookData.getCity()+
                "\n State:  "+addressBookData.getState()+
                "\n ZipCode:  "+addressBookData.getPinCode());
        return addressBookRepository.save(addressBookData);
    }
    //deleting address details by id
    @Override
    public void deleteAddressBookData(int personId) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        emailSenderService.sendEmail(addressBookData.getEmail(), "Test Mail","Hii....! Your details are deleted!");
        addressBookRepository.delete(addressBookData);
    }

    // getting address book data by city
    @Override
    public List<AddressBookData> getAddressBookDataByCity(String city) {
        return addressBookRepository.findAddressByCity(city);
    }

    @Override
    public List<AddressBookData> getAddressBookDataByState(String state) {
        return addressBookRepository.findAddressByState(state);
    }

    //Creating an address book data by token
    @Override
    public AddressBookData createAddressBookDataByToken(AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        addressBookData = new AddressBookData(addressBookDTO);
        addressBookRepository.save(addressBookData);
        String token = tokenUtil.createToken(addressBookData.getId());
        emailSenderService.sendEmail(addressBookData.getEmail(),"Test Mail", "Hii...."+addressBookData.getFirstName()+
                " your details are updated!\n" +
                "\n First Name:  "+addressBookData.getFirstName() +
                "\n Last Name:  "+addressBookData.getLastName() +
                "\n Phone number:  "+addressBookData.getContactNo()+
                "\n Email:  "+addressBookData.getEmail()+
                "\n Address:  "+addressBookData.getAddress()+
                "\n City:  "+addressBookData.getCity()+
                "\n State:  "+addressBookData.getState()+
                "\n ZipCode:  "+addressBookData.getPinCode()+
                "\n Token:  "+token);
        return addressBookData;
    }

    //Getting an address book data by token
    @Override
    public AddressBookData getAddressBookDataByToken(String token) {
        long id = tokenUtil.decodeToken(token);
        AddressBookData addressBookData = addressBookRepository.findById(id).get();
        return addressBookData ;
    }

    // Updating address book data by using token
    @Override
    public AddressBookData updateAddressBookDataByToken(String token, @Valid AddressBookDTO addressBookDTO) {
        long id = tokenUtil.decodeToken(token);
        AddressBookData addressBookData = addressBookRepository.findById(id).get();
        addressBookData.setFirstName(addressBookDTO.getFirstName());
        addressBookData.setLastName(addressBookDTO.getLastName());
        addressBookData.setContactNo(addressBookDTO.getContactNo());
        addressBookData.setEmail(addressBookDTO.getEmail());
        addressBookData.setAddress(addressBookDTO.getAddress());
        addressBookData.setCity(addressBookDTO.getCity());
        addressBookData.setState(addressBookDTO.getState());
        addressBookData.setPinCode(addressBookDTO.getPinCode());
        return addressBookRepository.save(addressBookData);
    }

    // Deleting address book data by using token
    @Override
    public AddressBookData deleteAddressBookDataByToken(String token) {
        long id = tokenUtil.decodeToken(token);
        if (addressBookRepository.findById(id).isPresent()) {
            AddressBookData addressBookData = addressBookRepository.findById(id).get();
            addressBookRepository.deleteById(id);
            return addressBookData;
        } else {
            throw new AddressBookException("Invalid token ");
        }
    }
}