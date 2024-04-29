package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.dto.ServiceProDTO;
import com.backend.model.Service;
import com.backend.services.ServiceService;
import com.backend.util.CloudinaryUtil;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class ServiceController {

	@Autowired 
	private ServiceService service;
	
	@GetMapping("/getAllService")
	public List<Service> getAllService(){
		return service.getAllService();
	}
	@GetMapping("/getProService/{id}")
	public List<ServiceProDTO> getProService(@PathVariable String id){
		return service.getProService(id);
	}

	 @GetMapping("/getAllServiceV1")
	    public Object getAllService(@RequestParam("page") int page, @RequestParam("size") int size) {
	        return service.findAllService(page, size);
	    }
	    
	    @PostMapping("updateService")
	    @Transactional
	    public Object updateService(
	            @RequestParam("id") String id,
	            @RequestParam("description") String description,
	            @RequestParam("idCate") String idCategory,
	            @RequestParam("name") String name,
	            @RequestParam("price") double price,
	            @RequestParam(value = "image", required = false) MultipartFile image
	    ) {
	        try {
	            var url = "";
	            if (image != null) {
	                url = CloudinaryUtil.uploadImage(image.getBytes());
	            }
	            return service.updateService(id, description, idCategory, name, price, url);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "FAIL";
	        }
	    }

	    @DeleteMapping("/deleteService/{id}")
	    public Object deleteService(@PathVariable String id) {
	        return service.deleteService(id);
	    }

	    @GetMapping("searchService/{textSearch}")
	    public Object searchService(@PathVariable("textSearch") String textSearch) {
	        return service.searchService(textSearch);
	    }
}
