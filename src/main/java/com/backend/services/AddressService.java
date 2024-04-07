package com.backend.services;

import com.backend.model.Account;
import com.backend.model.Address;

import java.util.List;

public interface AddressService {
    public List<Address> getAddressWithId(String id);

    public Address addAddress(Address address);

    public void removeAddressById(String id);
}
