package com.waheedtechblog.springsecurity.jpa.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return ("<h1>Hi " + currentUserName + "</h1>");
        } else {
            return ("<h1>Hi! Anonymous user</h1>");
        }
    }

    @GetMapping("/user")
    public String user(Principal principal) {
        //You can fetch loggedIn user from Principal object
        return ("<h1>Hi " + principal.getName() + "</h1>");
    }

    @GetMapping("/admin")
    public String admin(Authentication authentication) {
        //You can fetch loggedIn user from Authentication object
        return ("<h1>Hi " + authentication.getName() + "</h1>");
    }
}
