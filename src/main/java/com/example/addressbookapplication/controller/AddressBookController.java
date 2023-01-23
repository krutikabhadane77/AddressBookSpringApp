package com.example.addressbookapplication.controller;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.dto.ResponseDTO;
import com.example.addressbookapplication.model.AddressBookData;
import com.example.addressbookapplication.service.IAddressBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    // Getting all the address book list
    @GetMapping(value = {"", "/", "/getAll"})
    public ResponseEntity<ResponseDTO> getAddressBookData() {
        List<AddressBookData> addressDataList = null;
        addressDataList = addressBookService.getAddressBookData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Success", addressDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Getting city wise address list using city
    @GetMapping("/getcity/{city}")
    public ResponseEntity<ResponseDTO> getCityById(@PathVariable("city") String city){
        List<AddressBookData> addressDataList = null;
        addressDataList =addressBookService.getAddressBookDataByCity(city);
        ResponseDTO respDTO = new ResponseDTO("Get call success", addressDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Getting city wise address list using city
    @GetMapping("/getstate/{state}")
    public ResponseEntity<ResponseDTO> getStateById(@PathVariable("state") String state){
        List<AddressBookData> addressDataList = null;
        addressDataList =addressBookService.getAddressBookDataByState(state);
        ResponseDTO respDTO = new ResponseDTO("Get call success", addressDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Getting Address details by id from the Database
    @GetMapping("/get/{personId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("personId") int personId) {
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.getAddressBookDataById(personId);
        ResponseDTO respDTO = new ResponseDTO("Get Call for Id Successfull", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Creating an Address Data and save to Database
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.createAddressBookData(addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Updating an Address Details by using id from Database
    @PutMapping(path = "/update/{personId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@Valid @PathVariable("personId") int personId,
                                                                 @RequestBody AddressBookDTO addressBookDTO) {

        AddressBookData addressBookData = null;
        addressBookData = addressBookService.updateAddressBookData(personId, addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee payroll Data for: ", addressBookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Deleting an Address Details using id from the Database
    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("personId") int personId) {
        addressBookService.deleteAddressBookData(personId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + personId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}
