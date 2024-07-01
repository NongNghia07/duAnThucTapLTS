package com.example.duan.Config;

import com.cloudinary.Cloudinary;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "dfgfvusnp";
    private final String API_KEY = "261982714466168";
    private final String API_SECRET = "AxEmlk65bxmeH0bdotMfEizHjLQ";

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> map = new HashMap<>();
        map.put("cloud_name", CLOUD_NAME);
        map.put("api_key", API_KEY);
        map.put("api_secret", API_SECRET);
        return new Cloudinary(map);
    }
}
