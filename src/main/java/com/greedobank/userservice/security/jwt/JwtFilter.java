package com.greedobank.userservice.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedobank.userservice.exception.ExceptionMessage;
import com.greedobank.userservice.security.details.PersonDetails;
import com.greedobank.userservice.security.details.PersonDetailsService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;
  private final JwtProvider jwtProvider;
  private final PersonDetailsService personDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    String token = jwtProvider.getTokenFromHeader(request);
    if (token == null) {
      filterChain.doFilter(request, response);
    } else if (jwtProvider.validateToken(token, response)) {
      String email = jwtProvider.getEmailFromToken(token);
      PersonDetails personDetails = personDetailsService.loadUserByUsername(email);
      if (personDetails == null) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter()
            .write(objectMapper.writeValueAsString(new ExceptionMessage("Unauthorized")));
        return;
      }
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          personDetails, null, personDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
      filterChain.doFilter(request, response);
    }
  }

}
