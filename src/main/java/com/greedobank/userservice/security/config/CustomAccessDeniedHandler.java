package com.greedobank.userservice.security.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest req, HttpServletResponse res,
      AccessDeniedException accException) throws IOException {
    res.sendError(HttpServletResponse.SC_FORBIDDEN, "You have not permissions for this request");
  }
}
