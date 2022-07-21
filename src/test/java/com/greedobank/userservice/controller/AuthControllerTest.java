package com.greedobank.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import com.greedobank.userservice.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends BaseTest {

  @Mock
  private PersonServiceImpl personService;

  @Autowired
  private MockMvc mockMvc;

  private AuthRequest validAuthRequest;
  private AuthResponse response;

  @BeforeEach
  public void setupDto() {
    validAuthRequest = createAuthRequest();
  }

  @Test
  @WithMockUser(username = "tchornyi@gmail.com", roles = "CUSTOMER")
  public void authentication_thenReturnToken() throws Exception {
    when(personService.authentication(any(AuthRequest.class)))
        .thenReturn(response);
    mockMvc.perform(post("/auth")
        .content(new ObjectMapper().writeValueAsString(validAuthRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}