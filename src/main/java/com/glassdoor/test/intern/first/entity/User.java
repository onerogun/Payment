package com.glassdoor.test.intern.first.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class User {
    @Id
    private Long userId;
    private String userName;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<ProcessedPayments> processedPaymentsList;
}
