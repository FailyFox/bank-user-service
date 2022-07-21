package com.greedobank.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.service.impl.CustomerServiceImpl;
import java.time.format.DateTimeFormatter;
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
public class CustomerControllerTest extends BaseTest {

  @Mock
  private CustomerServiceImpl customerService;

  @Autowired
  private MockMvc mockMvc;

  private CustomerRequestDto validFieldsRequest;
  private CustomerRequestDto invalidFieldsRequest;
  private CustomerResponseDto responseDto;

  @BeforeEach
  public void setupDto() {
    responseDto = createCustomerResponseDto();
    validFieldsRequest = createValidCustomerRequestDto();
    invalidFieldsRequest = createInvalidCustomerRequestDto();
  }

  @Test
  @WithMockUser(username = "rzherebetskyi@gmail.com", roles = "MANAGER")
  public void getCustomerAccountById_thenReturnResponseDto() throws Exception {
    when(customerService.getCustomer(anyInt())).thenReturn(responseDto);
    mockMvc.perform(get("/customer/{id}", ID_DEFAULT))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(responseDto.getId()))
        .andExpect(jsonPath("$.fname").value(responseDto.getFname()))
        .andExpect(jsonPath("$.lname").value(responseDto.getLname()))
        .andExpect(jsonPath("$.email").value(responseDto.getEmail()))
        .andExpect(jsonPath("$.address").value(responseDto.getAddress()))
        .andExpect(jsonPath("$.phone").value(responseDto.getPhone()))
        .andExpect(jsonPath("$.idCode").value(responseDto.getIdCode()))
        .andExpect(jsonPath("$.passportData").value(responseDto.getPassportData()))
        .andExpect(jsonPath("$.birthday").value(responseDto.getBirthday()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
  }

  @Test
  @WithMockUser(username = "rzherebetskyi@gmail.com", roles = "MANAGER")
  public void getCustomerAccountById_thenReturnNoSuchEntity() throws Exception {
    when(customerService.getCustomer(anyInt())).thenReturn(responseDto);
    mockMvc.perform(get("/customer/{id}", ID_ZERO))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "tchornyi@gmail.com", roles = "CUSTOMER")
  public void getCustomerAccountById_thenReturnForbidden() throws Exception {
    when(customerService.getCustomer(anyInt())).thenReturn(responseDto);
    mockMvc.perform(get("/customer/{id}", ID_DEFAULT))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "tchornyi@gmail.com", roles = "CUSTOMER")
  public void createCustomerAccount_thenReturnInvalidFields() throws Exception {
    when(customerService.addCustomer(any(CustomerRequestDto.class)))
        .thenReturn(responseDto);
    mockMvc.perform(post("/customer")
            .content(new ObjectMapper().writeValueAsString(invalidFieldsRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(username = "rzherebetskyi@gmail.com", roles = "MANAGER")
  public void getAllCustomerAccounts_thenReturnResponseDto() throws Exception {
    when(customerService.getCustomer(anyInt())).thenReturn(responseDto);
    mockMvc.perform(get("/customer", ID_DEFAULT))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(responseDto.getId()))
        .andExpect(jsonPath("$[0].fname").value(responseDto.getFname()))
        .andExpect(jsonPath("$[0].lname").value(responseDto.getLname()))
        .andExpect(jsonPath("$[0].email").value(responseDto.getEmail()))
        .andExpect(jsonPath("$[0].address").value(responseDto.getAddress()))
        .andExpect(jsonPath("$[0].phone").value(responseDto.getPhone()))
        .andExpect(jsonPath("$[0].idCode").value(responseDto.getIdCode()))
        .andExpect(jsonPath("$[0].passportData").value(responseDto.getPassportData()))
        .andExpect(jsonPath("$[0].birthday").value(responseDto.getBirthday()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
  }

  @Test
  @WithMockUser(username = "tchornyi@gmail.com", roles = "CUSTOMER")
  public void createCustomerAccount_thenReturnResponseDto() throws Exception {
    when(customerService.addCustomer(any(CustomerRequestDto.class)))
        .thenReturn(responseDto);
    mockMvc.perform(post("/customer")
            .content(new ObjectMapper().writeValueAsString(validFieldsRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.fname").value(responseDto.getFname()))
        .andExpect(jsonPath("$.lname").value(responseDto.getLname()))
        .andExpect(jsonPath("$.email").value(responseDto.getEmail()))
        .andExpect(jsonPath("$.address").value(responseDto.getAddress()))
        .andExpect(jsonPath("$.phone").value(responseDto.getPhone()))
        .andExpect(jsonPath("$.idCode").value(responseDto.getIdCode()))
        .andExpect(jsonPath("$.passportData").value(responseDto.getPassportData()));
  }
}