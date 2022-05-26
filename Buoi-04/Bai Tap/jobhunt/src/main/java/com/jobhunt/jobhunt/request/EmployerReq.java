package com.jobhunt.jobhunt.request;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

public record EmployerReq(
        @Valid
        @NotBlank(message = "Name cannot null") String name,

        MultipartFile logo_path,

        @NotBlank(message = "Website cannot null") String website,

        @NotBlank(message = "Email cannot null") @Email(message = "Invalid Email") String email) {

}
