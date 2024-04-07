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

import com.backend.dto.ColorDTO;
import com.backend.payload.ColorPayload;
import com.backend.services.ColorService;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin(value="http://localhost:3000/")
public class ColorController {

	@Autowired
	private ColorService colorService;
	
	@GetMapping("/getAllColor")
	public Object getAllColor(@RequestParam("page") int page, @RequestParam("size") int size){
		return colorService.getAllColor(page, size);
	}

	@GetMapping("/getAllColorV1")
	public Object getAllColor(){
		return colorService.getAllColor();
	}

	@PostMapping("updateColor")
	public Object updateCategory(@RequestBody ColorPayload color) {
		return colorService.updateColor(color.getIdColor(), color.getColor());
	}

	@PostMapping("searchColor")
	public Object searchCategory(@RequestBody ColorPayload color) {
		return colorService.searchColor(color.getColor());
	}
	@GetMapping("/getColorPro/{id}")
	public List<ColorDTO> getColorById(@PathVariable String id){
		return colorService.getColorById(id);
	}
}
