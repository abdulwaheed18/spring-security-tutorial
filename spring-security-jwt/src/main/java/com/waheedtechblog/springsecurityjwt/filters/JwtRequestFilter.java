package com.waheedtechblog.springsecurityjwt.filters;

import com.waheedtechblog.springsecurityjwt.services.MyUserDetailService;
import com.waheedtechblog.springsecurityjwt.utils.JwtTokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer ";

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtTokeService jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Check headers and fetch JWT token if exists
        final Optional<String> jwtToken = getJWTToken(httpServletRequest);
        //if token exists
        jwtToken.ifPresent( accessToken -> {
            final String username = jwtUtil.extractUsername(accessToken);
            // not focusing on roles here in this demo.. will take another tutorial for roles and Authorities
            if(username != null && !username.trim().isEmpty()){
               final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                if (jwtUtil.validateToken(accessToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // set current user as authenticaed in securityContext
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        });
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private static Optional<String> getJWTToken(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(BEARER)) {
            return Optional.of(accessToken.substring(7));
        }
        return Optional.empty();
    }

}
