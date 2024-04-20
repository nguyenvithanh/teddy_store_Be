package com.backend.controller;

import com.backend.dto.CustomerDto;
import com.backend.dto.PaginateDTO;
import com.backend.payload.ActiveCustomerPayload;
import com.backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin("http://localhost:3000/")
public class CustomerController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/customers")
    public PaginateDTO<List<CustomerDto>> getCustomerSearchAndPagination(
            @RequestParam(value = "query", defaultValue = "")  String query,
            @RequestParam(value = "active",defaultValue = "1") Integer active,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "9", required = false) Integer pageSize ) {
        return accountService.getCustomerSearchAndPagination(query,active == 1, pageNumber,pageSize);
    }

    @PostMapping("/customers/active")
    public Boolean updateActiveCustomer(
            @RequestBody ActiveCustomerPayload activeCustomerPayload) {
        return accountService.updateActiveCustomer(activeCustomerPayload.getId(),activeCustomerPayload.getActive());
    }
}
