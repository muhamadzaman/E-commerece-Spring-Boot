# E-Commerece

A simple E-commerece store to sell and purchase products.

## Java version
- 17

## Spring Boot version
- 2.7.7

## Architecture
- microservices

## Microservices
- Product service - All the controller requests and business logic related to Products.
- Comment service - All the controller requests and business logic related to Products.
- User service
- Checkout service

## Build Status
- This project is completed. But not deployed yet. Stripe is storing all the orders record. We can store it in database using end points from stripe api but it is paid.

## Code Style
- I used microservices architecture for this project. 

## Modules
- Users
- Products
- Cart
- Checkout
- Coupon

## Features
- A user can add or buy products
- A user can comment to others products
- A user can edit or delete his comments and products
- A user can make his cart before login and can checkout after login to his account
- A user can add his profile picture
- A User can add many pictures for a product
- Coupon can be used for discount if it is valid

Above are the some main features of my application.

## System Dependencies

I have used the following gems for my system:

- Cloudinary - Store my all the files on cloud.
- Postgres - Used to manage my database
- Sonarlint - to style and manage my indentation of my code
- Stripe - Manage checkouts and coupons
- Bootstrap - Used to style my UI
- Jquery - Used in place of javascript
- spring-cloud-starter-netflix-eureka-client - To register microservices with eureka.
- Spring-starter-ecurity - For the authorization and authentication purpose in my app
- spring-boot-starter-jpa - Starter for using Spring Data JPA with Hibernate
- spring-cloud-starter-openfeign - To use feign client
- spring-cloud-starter-config - For the purpose of 
of  using centralized configurations.
And of course i used intellij as an editor.

## Configuration

I have done some configurations for my application as:

For sending emails:

```sh
stripe.public_key=pk_test_public_key
stripe.secret_key=sk_test_secret_key
```

For cloudinary:

```sh
cloud_name={cloud_name}
cloud_api_key={cloud_api_key}
cloud_api_secret={cloud_api_secret}
```

For Datasource and Jpa:

```sh
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.postgres.console.enabled=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.defer-datasource-initialization=true
```

For registering with Eureka

```sh
eureka.instance.prefer-ip-address=true
eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```
And for some other dependencies i added dependencies and make configurations for them.


## Database
```sh
Postgres
```

## Tests
- Unit tests for the comment controller and comment service implementation is also written in this project.

## Java Docs

- I write java docs for all the controllers and services implementations in microservices. You can best understand each method clearly by reading javadocs.

## How to Use?

- Sign up as a customer and move towards login.
- You can also add your own products to the store.
- You can comment on any product other than yours.
- You can add cart items without login.
- Login is compulsory before checkout and redirecting to checkout.
- View Items and add in cart and place your order after pay the amount and wait for order delivery
