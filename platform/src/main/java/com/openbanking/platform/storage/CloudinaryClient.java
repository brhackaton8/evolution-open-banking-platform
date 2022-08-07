package com.openbanking.platform.storage;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryClient {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dxddbvvkg",
            "api_key", "244426132456294",
            "api_secret", "1r8zMRLwF-gOVcsgWnNbLBaf5Yc"));

    public String uploadVideo(String path) throws IOException {

        Map upload = cloudinary.uploader().upload(new File(path), ObjectUtils.asMap("resource_type", "video"));
        return (String) upload.get("secure_url");

    }
}
