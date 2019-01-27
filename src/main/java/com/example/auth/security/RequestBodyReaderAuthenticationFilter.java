package com.example.auth.security;

import com.example.auth.domain.CustomUser;
import com.example.auth.domain.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestBodyReaderAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public RequestBodyReaderAuthenticationFilter() {
        super("/login");
    }


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String requestBody;
        CustomUser authRequest;
        try {
            requestBody = IOUtils.toString(request.getReader());
            authRequest = objectMapper.readValue(requestBody, CustomUser.class);

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            throw new InternalAuthenticationServiceException("Something went wrong while parsing /login request body", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(new ContentCachingRequestWrapper(request), response);
    }

}
