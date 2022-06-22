package com.greedobank.userservice.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedobank.userservice.exception.ExceptionMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

  private final ObjectMapper objectMapper;
  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Value("${SECRET_WORD}")
  private String jwtSecret;

  public boolean validateToken(String token, HttpServletResponse response) throws IOException {
    try {
      Jwts.parser().setSigningKey(getSecretKeyFrom(jwtSecret)).parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      log.info("Token is invalid because of {}", e.getMessage());
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter()
          .write(objectMapper.writeValueAsString(new ExceptionMessage(e.getMessage())));
      return false;
    }
  }

  public String getTokenFromHeader(HttpServletRequest request) {
    return request.getHeader(AUTHORIZATION_HEADER);
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(getSecretKeyFrom(jwtSecret)).parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  public SecretKey getSecretKeyFrom(String secretWord) {
    byte[] decodedKey = Base64.getDecoder().decode(secretWord);
    return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
  }
}