package com.backend.services;

import com.backend.model.Banner;

import java.util.List;

public interface BannerService {
    Banner addBanner(String url);
    Boolean removeBanner(String id);
    List<Banner> getAllBanner();
}
