package com.nayan.booking.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nayan.booking.Service.jwtUserDetailService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private jwtUserDetailService userDetailsService;
	
	
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

			String authorizationHeader = request.getHeader("Authorization");
			System.out.println(authorizationHeader);

			String username = null;
			String jwtToken= null;
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwtToken = authorizationHeader.substring(7);
				username= jwtUtils.extractUsername(jwtToken);
				System.out.println(username);
			}
			if( username!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails currentuserDetails = userDetailsService.loadUserByUsername(username);
				System.out.println("cuu "+ currentuserDetails);
				Boolean tokenValidated=jwtUtils.validateToken(jwtToken,currentuserDetails);
				if(tokenValidated) {
					System.out.println("Hii");
					/*
					 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
					 * UsernamePasswordAuthenticationToken(currentuserDetails, null);
					 * usernamePasswordAuthenticationToken.setDetails(new
					 * WebAuthenticationDetailsSource().buildDetails(request));
					 * SecurityContextHolder.getContext().setAuthentication(
					 * usernamePasswordAuthenticationToken);
					 */
					
					 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							 currentuserDetails, null, currentuserDetails.getAuthorities());
		                usernamePasswordAuthenticationToken
		                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);

}
}