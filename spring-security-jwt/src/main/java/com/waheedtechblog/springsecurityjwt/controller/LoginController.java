package com.waheedtechblog.springsecurityjwt.controller;

import com.waheedtechblog.springsecurityjwt.dto.LoginRequest;
import com.waheedtechblog.springsecurityjwt.dto.LoginResponse;
import com.waheedtechblog.springsecurityjwt.services.MyUserDetailService;
import com.waheedtechblog.springsecurityjwt.utils.JwtTokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokeService jwtTokeService;

    @Autowired
    private MyUserDetailService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String accessToken = jwtTokeService.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(accessToken));
    }
}
