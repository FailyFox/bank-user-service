package com.greedobank.userservice.security.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest req, HttpServletResponse res,
      AuthenticationException authException) throws IOException {
    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Your token is null or not valid");
  }
}
