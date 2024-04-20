package com.backend.services.servicesImpl;

import com.backend.model.Banner;
import com.backend.repository.BannerRepository;
import com.backend.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BannerImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner addBanner(String url) {
        UUID uuid = UUID.randomUUID();
        Banner banner = new Banner();
        banner.setId(uuid.toString());
        banner.setUrl(url);
        return  bannerRepository.save(banner);
    }

    @Override
    public Boolean removeBanner(String id) {
        Banner banner = bannerRepository.findById(id).orElseGet(null);
        if (banner != null) {
            bannerRepository.delete(banner);
            return true;
        }
        return false;
    }

    @Override
    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }
}
