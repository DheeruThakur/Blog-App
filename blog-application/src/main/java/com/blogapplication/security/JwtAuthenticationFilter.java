package com.blogapplication.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// get token from request
		String jwtToken = request.getHeader("Authorization");
		
		String token = null;
		String username = null;
		
		if(jwtToken != null && jwtToken.startsWith("Bearer")) {
			token = jwtToken.substring(7);
			try {
				
				username = jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("jwt token is expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid jwt token");
			} catch (Exception e) {
                e.printStackTrace();
            }
			
		}
		else {
			System.out.println("token doesnot starts with Bearer");
		}
		
		// validate token
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtTokenHelper.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				System.out.println("Invalid jwt token");
			}
			
		}
		else {
			System.out.println("username is null or security context is not null");
		}
		
		filterChain.doFilter(request, response);
	}

}
