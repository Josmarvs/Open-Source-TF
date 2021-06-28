package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Role;
import com.appheathycook2.Entity.RoleName;
import com.appheathycook2.Entity.User;
import com.appheathycook2.Message.Request.LoginForm;
import com.appheathycook2.Message.Request.SignUpForm;
import com.appheathycook2.Message.Response.JwtResponse;
import com.appheathycook2.Message.Response.ResponseMessage;
import com.appheathycook2.Repository.IRoleRepository;
import com.appheathycook2.Repository.IUserRepository;
import com.appheathycook2.Security.Jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt =jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest){
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Falló: El Username ya existe!! "),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Falló: El Email ya existe!! "),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(),signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        String role = signUpRequest.getRole();
        Set roles = new HashSet<>();

        switch(role){
            case "admin":
                Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleAdmin); break;
            case "receptionist":
                Role roleReceptionist = roleRepository.findByName(RoleName.ROLE_RECEPTIONIST)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleReceptionist); break;
            default:
                Role roleUser = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleUser); break;
        }

        user.setRoles(roles);

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Usuario registrado correctamente !!"), HttpStatus.OK);
    }
}
