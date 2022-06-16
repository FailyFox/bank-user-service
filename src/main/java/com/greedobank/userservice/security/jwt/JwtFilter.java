package com.greedobank.userservice.security.jwt;

import com.greedobank.userservice.security.details.PersonDetails;
import com.greedobank.userservice.security.details.PersonDetailsService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  private final JwtProvider jwtProvider;
  private final PersonDetailsService personDetailsService;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    String token = jwtProvider.resolveToken((HttpServletRequest) servletRequest);
    if (token != null && jwtProvider.validateToken(token)) {
      String email = jwtProvider.getEmailFromToken(token);
      PersonDetails personDetails = personDetailsService.loadUserByUsername(email);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          personDetails, null, personDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}