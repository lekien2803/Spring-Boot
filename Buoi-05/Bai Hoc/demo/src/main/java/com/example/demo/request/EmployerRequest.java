package com.example.demo.request;

import org.springframework.web.multipart.MultipartFile;

public record EmployerRequest(
    String name,
    MultipartFile logo_path,
    String website,
    String email
) {
    
}
