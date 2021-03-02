package com.glassdoor.test.intern.first.service;

import com.glassdoor.test.intern.first.VO.IncomingRequest;
import com.glassdoor.test.intern.first.entity.ProcessedPayments;
import com.glassdoor.test.intern.first.entity.User;
import com.glassdoor.test.intern.first.repository.ProcessedPaymentsRepository;
import com.glassdoor.test.intern.first.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessedPaymentsRepository paymentsRepository;

    public ResponseEntity<Boolean> processPayment(IncomingRequest incomingRequest) {
        log.info("Inside of processPayment method of PaymentService class");
        log.info("Incoming Request: " + incomingRequest.toString());
        User user = userRepository.findById((long) incomingRequest.getUserId()).orElse(null);
        if (user != null) {
            log.info("user exists, checking username and address");
            if(user.getUserName().equals(incomingRequest.getUserName())
                    && user.getAddress().equals(incomingRequest.getBillingAddress()) ) {
                log.info("payment accepted!");
                submitPayment(incomingRequest.getCardnumber(), incomingRequest.getAmount());

                ProcessedPayments processedPayment = new ProcessedPayments();
                processedPayment.setCardUsedForPayment(incomingRequest.getCardnumber());
                processedPayment.setAmount(incomingRequest.getAmount());
                processedPayment.setUserId((long) incomingRequest.getUserId());
                processedPayment.setPaymentProcessTime(LocalDateTime.now());
                ProcessedPayments savedPayment = paymentsRepository.save(processedPayment);
                log.info("payment saved to database: " + savedPayment.toString());

                return  new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            } else  {
                log.info("payment declined, username or address does not match");
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } else {
            log.info("payment declined, user does not exist!");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public void submitPayment(String card, int amount) {
        //Don't implement this.
    }

    public ResponseEntity<List<ProcessedPayments>> getPayments(Long userId) {
        log.info("Inside of getPayments method of PaymentService class");
        log.info("user Id submitted: " + userId);
        if(userRepository.existsById(userId)) {
            log.info("user exists, returning processed payments");
           List<ProcessedPayments> payments= userRepository.findById(userId).get().getProcessedPaymentsList();
           return new ResponseEntity<>(payments, HttpStatus.OK);
        } else {
            log.info("user does not exist!");
            return ResponseEntity.badRequest().build();
        }
    }
}
