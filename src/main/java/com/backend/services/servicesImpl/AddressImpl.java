package com.backend.services.servicesImpl;

import com.backend.model.Account;
import com.backend.model.Address;
import com.backend.repository.AddressRepository;
import com.backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> getAddressWithId(String id) {
        return addressRepository.findAllById(id);
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void removeAddressById(String id) {
        addressRepository.deleteById(id);
    }
}
