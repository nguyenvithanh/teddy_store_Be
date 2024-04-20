package com.backend.controller;

import com.backend.model.Account;
import com.backend.model.AccountInfo;
import com.backend.model.Address;
import com.backend.model.Banner;
import com.backend.payload.BannerDeletePayload;
import com.backend.payload.BannerPayload;
import com.backend.payload.LoginPayload;
import com.backend.payload.RegisterPayload;
import com.backend.repository.AddressRepository;
import com.backend.services.AccountInfoService;
import com.backend.services.AccountService;
import com.backend.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teddy-store")
@CrossOrigin("http://localhost:3000/")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@PostMapping("/banners")
	public Banner addBanner(@RequestBody  BannerPayload bannerPayload) {
		return bannerService.addBanner(bannerPayload.getUrl());
	}

	@GetMapping("/banners")
	public List<Banner> getAllBanner() {
		return bannerService.getAllBanner();
	}

	@DeleteMapping("/banners/{id}")
	public Boolean deleteBanner(@PathVariable("id") String id) {
		return bannerService.removeBanner(id);
	}

}
