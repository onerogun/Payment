**Introduction**

Ogun Oner

I converted given Java application to 
spring boot application.

- Application saves given user data to H2 in-memory SQL 
database after it starts.
  
- Processed payments are saved to database and can be 
retrieved with user ID. There is a One-to-Many relationship 
  between User database and ProcessedPayments database. User ID
  is saved to each processed payment as a foreign key to set up 
  relation between to databases.
  
- I created 2 REST endpoints to interact with web application. 
- One REST endpoint is to post payment and other is to get payments 
from database as a list.
  
- I used MVC design pattern and implemented OOP standards.

- I tested the application with Integration testing with correct and incorrect user
info.
  
- I used Spring Data JPA to persist data

**Postman post:**

http://localhost:8080/payment/pay

**with body:**

{
"userId": 1,
"userName": "ABC",
"billingAddress": "123 Some Street, City Name, ST",
"amount": 1,
"cardnumber": "5469 9879 5467 2135"
}

**Postman get:**

This will return processed payments from database

http://localhost:8080/payment/getPayments/1
