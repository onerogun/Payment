package com.glassdoor.test.intern.first.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProcessedPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    private int amount;
    private String cardUsedForPayment;
    private LocalDateTime paymentProcessTime;

    @Column(name = "user_id")
    private Long userId;
}
