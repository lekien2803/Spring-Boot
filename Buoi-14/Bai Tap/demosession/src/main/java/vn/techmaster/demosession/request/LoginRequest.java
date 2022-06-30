package vn.techmaster.demosession.request;

import javax.validation.constraints.*;

public record LoginRequest(
    @NotBlank(message = "Email cannot blank")
    @Email(message = "Invalid email")
    String email,

    @Size(min = 5, max = 20, message = "Password legth must between 5 and 20 characters")
    String password
) {
    
}
