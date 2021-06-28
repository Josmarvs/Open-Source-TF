package com.appheathycook2.Message.Request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min=3, max=80)
    private String name;
    @NotBlank
    @Size(min=3,max=60)
    private String username;
    @NotBlank
    @Email
    @Size(max=100)
    private String email;
    @NotBlank
    private String role;
    @NotBlank
    @Size(min=5, max=80)
    private String password;
}
