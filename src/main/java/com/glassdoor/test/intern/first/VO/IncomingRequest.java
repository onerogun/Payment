package com.glassdoor.test.intern.first.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class IncomingRequest {
  private int userId;
  private String userName;
  private String billingAddress;
  private int amount;
  private String cardnumber;
}