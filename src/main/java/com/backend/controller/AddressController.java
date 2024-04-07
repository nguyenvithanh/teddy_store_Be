package com.backend.controller;

import com.backend.model.Address;
import com.backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value = "http://localhost:3000/")
public class AddressController {

    @Autowired
    private	AddressService addressService;

    @GetMapping("/getDataAddressWithId/{id}")
    public List<Address> getDataAddressWithId(@PathVariable String id) {
        return addressService.getAddressWithId(id);
    }

    @PostMapping("/addNewAddress")
    public Address addNewAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @DeleteMapping("/removeAddress/{id}")
    ResponseEntity<?> deleteAddress(@PathVariable String id) {
        addressService.removeAddressById(id);
        return ResponseEntity.ok("Delete successfully!");
    }
}
