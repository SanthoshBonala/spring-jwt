package com.example.auth.security;

import com.example.auth.Repositories.UserRepository;
import com.example.auth.domain.CustomUser;
import com.example.auth.domain.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails c = (CustomUserDetails)authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        String token = new JwtGenerator().generate(c.getUser());
        new ObjectMapper().writeValue(response.getWriter(), token);
  //      new DefaultRedirectStrategy().sendRedirect(request,response, "/login");
    }
}
