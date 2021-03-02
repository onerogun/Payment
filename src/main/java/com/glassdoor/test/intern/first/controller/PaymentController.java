package com.glassdoor.test.intern.first.controller;

import com.glassdoor.test.intern.first.VO.IncomingRequest;
import com.glassdoor.test.intern.first.entity.ProcessedPayments;
import com.glassdoor.test.intern.first.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Boolean> processPayment(@RequestBody IncomingRequest incomingRequest) {
        log.info("Inside of processPayment method of PaymentController class");
        return paymentService.processPayment(incomingRequest);
    }

    @GetMapping("/getPayments/{userId}")
    public ResponseEntity<List<ProcessedPayments>> getPayments(@PathVariable Long userId) {
        log.info("Inside of getPayments method of PaymentController class");
        return paymentService.getPayments(userId);
    }
}
