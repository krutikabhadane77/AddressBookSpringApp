package com.example.addressbookapplication.repository;

import com.example.addressbookapplication.model.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookData,Long> {
    List<AddressBookData> findAddressByCity(String city);
    List<AddressBookData> findAddressByState(String state);

}