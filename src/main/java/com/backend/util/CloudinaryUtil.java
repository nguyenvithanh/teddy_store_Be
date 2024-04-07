package com.backend.util;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ObjectUtils;

import com.cloudinary.Cloudinary;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CloudinaryUtil {
    public String uploadImage(Object file) throws IOException {
        var config = new HashMap<>();
        config.put("cloud_name", "dchefcs07");
        config.put("api_key", "366994417113435");
        config.put("api_secret", "pFv06ognVfXk6BM-NgTkjHkZZ8o");
        Cloudinary cloudinary = new Cloudinary(config);
        var uploadResult = cloudinary
                .uploader()
                .upload(file, com.cloudinary.utils.ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}
