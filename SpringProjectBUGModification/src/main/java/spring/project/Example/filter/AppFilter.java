package spring.project.Example.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.project.Example.Service.JwtService;
//import spring.project.Example.Service.JwtService;
import spring.project.Example.Service.UserService;

@Component
public class AppFilter extends OncePerRequestFilter {

	 @Autowired
	    private JwtService jwtService;

	 @Autowired
	    private UserService userDetailsServiceImpl;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        if(authHeader != null && authHeader.startsWith("Bearer ")){
	            token = authHeader.substring(7);
	            username = jwtService.extractUsername(token);
	        }

	        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
	            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
	            if(jwtService.validateToken(token, userDetails)){
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
}