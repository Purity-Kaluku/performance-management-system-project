package com.example.HR.Backend.Requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.mapping.Set;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@CrossOrigin

public class AdminSignupRequest {
    @JsonIgnore
    @Size
    @NotBlank
    @Size(max = 50)
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank
    @Size(min = 6, max = 40, message = "should be 6 characters and above")
    private String password;

    @NotBlank
    @Size(min = 6, max = 40, message = "should be 6 characters and above")
    private String confirmPassword;

}
