package com.appheathycook2.Message.Response;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {

    private String token;
    private String type ="Bearer";
    private String username;
    private Collection authorities;

    public JwtResponse(String token, String username, Collection authorities) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }
}
