package com.example.demo.security;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
//the token is will get from frontend react which has the key Authorization the type of token is bearer 
    	// we only wanna get token there will be the bearer space thats why we use substring 
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
// here we check the validation of token that is is valid or not 
            // if its valid we get the exact with the method return in  jwtutil
            if (jwtUtil.isValid(token)) {

                String email = jwtUtil.extractEmail(token);
                // if we give the email with findby we get the user from the database 
                UserModel user = userRepository.findByEmail(email).orElse(null);

               if(user!=null) {
            	   UsernamePasswordAuthenticationToken authentication =
                           new UsernamePasswordAuthenticationToken(
                                   user, null,null );
            	// the class securitycontextholder  has 
                SecurityContextHolder.getContext().setAuthentication(authentication);  

                    

                  
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
