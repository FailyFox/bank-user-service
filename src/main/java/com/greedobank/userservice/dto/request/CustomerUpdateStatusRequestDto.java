package com.greedobank.userservice.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateStatusRequestDto {

  @NotNull(message = "Status is missing")
  @Pattern(regexp = "(^APPROVED$)|(^DECLINED$)",
      message = "Status must be 'APPROVED' or 'DECLINED'")
  private String status;
}