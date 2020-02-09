package com.waheedtechblog.springsecurityjwt.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping({ "/username" })
    public String loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return ("<h1>Hi " + currentUserName + "</h1>");
        } else {
            return ("<h1>Hi! Anonymous user</h1>");
        }
    }

}
