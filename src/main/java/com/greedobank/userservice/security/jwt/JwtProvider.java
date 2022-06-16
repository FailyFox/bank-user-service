package com.greedobank.userservice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtProvider {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Value("${SECRET_WORD}")
  private String jwtSecret;

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      log.info("Token is invalid because of {}", e.getMessage());
    }
    return false;
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(AUTHORIZATION_HEADER);
  }
}