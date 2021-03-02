package com.glassdoor.test.intern.first;

import com.glassdoor.test.intern.first.VO.IncomingRequest;
import com.glassdoor.test.intern.first.entity.ProcessedPayments;
import com.glassdoor.test.intern.first.repository.ProcessedPaymentsRepository;
import com.glassdoor.test.intern.first.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PaymentControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;



    @Test
    protected void testPayment() {
        IncomingRequest incomingRequest = new IncomingRequest();
        incomingRequest.setUserId(1);
        incomingRequest.setUserName("ABC");
        incomingRequest.setAmount(1);
        incomingRequest.setCardnumber("1234 1234 1234 9999");
        incomingRequest.setBillingAddress("123 Some Street, City Name, ST");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity<>(incomingRequest, httpHeaders);

        ResponseEntity<Boolean> responseEntity = testRestTemplate.postForEntity("/payment/pay"
        , httpEntity, Boolean.class);

        /**
         * Test with correct user info
         */
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.ACCEPTED));
        assertTrue(responseEntity.getBody());
        List<ProcessedPayments> processedPayments = userRepository.findById(1l).get().getProcessedPaymentsList();
        ProcessedPayments lastPayment = processedPayments.get(processedPayments.size() -1);
        assertTrue(lastPayment.getCardUsedForPayment().equals(incomingRequest.getCardnumber()));
        assertTrue(lastPayment.getAmount() == incomingRequest.getAmount());


        /**
         * Test with incorrect user info
         */
        incomingRequest.setBillingAddress("111 Some Street, City Name, ST");

        ResponseEntity<Boolean> responseEntity1 = testRestTemplate.postForEntity("/payment/pay"
                , httpEntity, Boolean.class);

        assertTrue(responseEntity1.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        assertFalse(responseEntity1.getBody());

    }
}
