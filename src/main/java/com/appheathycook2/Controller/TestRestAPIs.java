package com.appheathycook2.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestAPIs {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess(){
        return "Contenidos de Usuario!!";
    }

    @GetMapping("/receptionist")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public String receptionistAccess(){
        return "Contenidos de Recepcionista!!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "Contenidos de Administrador!!";
    }
}
