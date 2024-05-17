package com.example.clinic.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dhnzeljvr",
                "api_key", "225486414393395",
                "api_secret", "pnnZI7gs4WflMgBXyGeX3xleYJk"));
        return cloudinary;
    }
}
