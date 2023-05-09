package com.exam.config;

import com.exam.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requwstTokenHeader=request.getHeader("Authorization");
        System.out.println(requwstTokenHeader);
        String username=null;
        String jwtToken = null;
        System.out.println(requwstTokenHeader);
        if(requwstTokenHeader !=null && requwstTokenHeader.startsWith("Bearer "))
        {

            jwtToken =requwstTokenHeader.substring(7);
            try {
                System.out.println(jwtToken);
                username=this.jwtUtil.extractUsername(jwtToken);
                System.out.println(username);
            }
            catch (ExpiredJwtException e)
            {
                e.printStackTrace();
                System.out.println("Token expired");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Error occured");
            }
        }
        else
        {
            System.out.println("Invalid token don't start with bearer");
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            final UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);

            if(this.jwtUtil.validateToken(jwtToken,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }   else
        {
            System.out.println("Token not valid");
        }
        filterChain.doFilter(request,response);

    }
}