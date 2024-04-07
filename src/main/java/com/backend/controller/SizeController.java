package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.SizeDTO;
import com.backend.payload.SizePayload;
import com.backend.services.SizeService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class SizeController {

	@Autowired
	private SizeService sizeService;
	
	
	@GetMapping("/getAllSize")
    public Object getAllSize(@RequestParam("page") int page, @RequestParam("size") int size) {
        return sizeService.getAllSize(page, size);
    }
	
	@GetMapping("/getSizeBy/{productId}")
    public List<SizeDTO> getSizeById(@PathVariable String productId){
		return sizeService.getSizeWhereId(productId);
	}
	@GetMapping("/getAllSizeV1")
    public Object getAllSize(){
        return sizeService.getAllSize();
    }
    @PostMapping("updateSize")
    public Object updateSize(@RequestBody SizePayload size) {
        return sizeService.updateSize(size.getIdSize(), size.getSizeNo());
    }

    @PostMapping("searchSize")
    public Object searchCategory(@RequestBody SizePayload size) {
        return sizeService.searchSize(size.getSizeNo());
    }
}
