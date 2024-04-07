package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.DetailsOrder;
import com.backend.services.DetailsOrderService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value = "http://localhost:3000/")
public class DetailsOrderController {

    @Autowired
    private DetailsOrderService detailsOrderService;

    @GetMapping("/getAllDetailsOrder")
    public List<DetailsOrder> getAllDetailsOrder() {
        return detailsOrderService.getAllDetailsOrder();
    }

    @PostMapping("/addNewOrderDetail")
    public DetailsOrder addNewOrderDetail(@RequestBody DetailsOrder detailsOrder) {
        return detailsOrderService.addNewDetailOrder(detailsOrder);
    }
}
