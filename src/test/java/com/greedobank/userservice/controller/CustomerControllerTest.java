package com.greedobank.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends BaseTest {

  @MockBean
  private CustomerServiceImpl customerService;

  @Autowired
  private MockMvc mockMvc;

  private CustomerUpdateStatusRequestDto validCustomerUpdateStatusRequestDto;
  private CustomerUpdateStatusRequestDto invalidCustomerUpdateStatusRequestDto;
  private CustomerResponseDto customerResponseDto;

  @BeforeEach
  public void setupDto() {
    customerResponseDto = createCustomerResponseDto();
    validCustomerUpdateStatusRequestDto = validUpdateCustomerStatus();
    invalidCustomerUpdateStatusRequestDto = invalidUpdateCustomerStatus();
  }

  @Test
  @WithMockUser(username = "rzherebetskyi@gmail.com", roles = "MANAGER")
  public void updateCustomerStatus_thenReturnResponseDto() throws Exception {
    when(customerService.updateCustomerStatus(any(CustomerUpdateStatusRequestDto.class), anyInt()))
        .thenReturn(customerResponseDto);
    mockMvc.perform(patch("/customer/status/{id}", ID_DEFAULT)
        .content(new ObjectMapper().writeValueAsString(validCustomerUpdateStatusRequestDto))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.status").value(customerResponseDto.getStatus()));
  }
}