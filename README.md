# Microservices Project with Spring Boot, Spring Security, Netflix Eureka, and FeignClient

## Project Overview
This project demonstrates a microservices architecture using Spring Boot, Spring Security, Netflix Eureka for service discovery, and FeignClient for inter-service communication.

## Main Services
- **User Service**: Handles user registration, login, and JWT-based authentication
- **Product Service**: Manages products with role-based access
- **Order Service**: Handles order processing

## Additional Services
- **API Gateway**: Routes requests to appropriate microservices
- **Service Discovery**: Uses Netflix Eureka for service registration

## Technologies Used
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Netflix Eureka
- FeignClient
- Spring Data JPA
- Postgresql Database

## Project Structure
```
microservices-parent/
├── user-service/
├── product-service/
├── order-service/
├── api-gateway/
├── service-discovery/
└── pom.xml
```

## Prerequisites
- Java 17+
- Maven 3.x
- Postman (for testing)

## Setup and Installation

### Clone Repository
```bash
git clone https://github.com/your-repo/microservices-parent.git
cd microservices-parent
```

### Build Project
```bash
mvn clean install
```

### Run Services (in order)
1. Service Discovery
```bash
cd service-discovery
mvn spring-boot:run
```

2. API Gateway
```bash
cd api-gateway
mvn spring-boot:run
```

3. User Service
```bash
cd user-service
mvn spring-boot:run
```

4. Product Service
```bash
cd product-service
mvn spring-boot:run
```

5. Order Service
```bash
cd order-service
mvn spring-boot:run
```

## Service Endpoints

### User Service
- `POST /api/users/register`: Register a new user
- `POST /api/users/login`: Authenticate and generate JWT token
- `GET /api/users/test`: Test endpoint

### Product Service
- `GET /api/products`: Get all products
- `POST /api/products`: Add a new product (MERCHANT only)
- `DELETE /api/products/{id}`: Delete a product (MERCHANT only)

### Order Service
- `POST /api/orders`: Place an order (CUSTOMER only)
- `GET /api/orders`: Get all orders (MERCHANT only)

## Testing with Postman
1. Register a user
2. Login to get JWT token
3. Use token to access protected endpoints

## Future Enhancements
- Centralized logging with ELK Stack
- Distributed tracing (Spring Cloud Sleuth, Zipkin)
- Replace H2 with production database
- Add Swagger API documentation
- Containerization with Docker and Kubernetes