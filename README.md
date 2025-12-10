# Transport Management System - SpringBoot + PostgreSQL

### A robust backend system designed for logistics operations, enabling seamless management of Loads, Transporters, Bids, and Bookings. The workflow enforces real-world business rules such as truck capacity validation, controlled load status transitions, multi-truck allocation, and bid evaluation based on scoring logic.
### Built with Spring Boot and PostgreSQL, this backend provides a reliable foundation for Transport Management Systems (TMS).

---

## Tech Stack
- Java 21
- Spring Boot
- PostgreSQL
- Swagger OpenAPI
- Postman

---

## API Documentation

### Swagger UI
👉 http://localhost:8081/swagger-ui/index.html#/

### Postman Collection
👉 https://www.postman.co/workspace/My-Workspace~11a8e165-837f-4d77-894b-4db1390c2ce5/collection/41731578-570a4dd7-b5f0-4bda-ac9e-106bacd01f46?action=share&creator=41731578

---

## API Summary 

### Load APIs -
- POST /load → Create a new load 
- GET /load?shipperId=&status=&page=0&size=10 → List with pagination
- GET /load/{loadId} → Load details with active bids
- PATCH /load/{loadId}/cancel → Cancel a load 
- GET /load/{loadId}/best-bid → Get sorted(best) bid suggestions
  
### Transporter APIs -
- POST /transporter → Register a new transporter
- GET /transporter/{transporterId} → Fetch transporter details
- PUT /transporter/{transporterId}/trucks → Update available trucks

### Bid APIs -
- POST /bid → Submit a bid
- GET /bid?loadId=&transporterId=&status= → Filter bids
- GET /bid/{bidId} → Get bid details
-PATCH /bid/{bidId}/reject → Reject a bid

### Booking APIs -
- POST /booking → Accept a bid & create booking
- GET /booking/{bookingId} → Get booking details
- PATCH /booking/{bookingId}/cancel → Cancel booking

---

## Database Schema Diagram

<img width="600" height="600" alt="TMS_ERD" src="https://github.com/user-attachments/assets/e435e4e6-7747-4a60-9d49-98c19a140f81" />

## Test Coverage Images

<img width="700" height="700" alt="image" src="https://github.com/user-attachments/assets/3aeeaef2-f4b5-4bda-a5c7-f5ba29b1f9a9" />
<img width="700" height="700" alt="image" src="https://github.com/user-attachments/assets/3d1348cc-f9fd-4411-9dcc-905751c1fe42" />
<img width="700" height="700" alt="image" src="https://github.com/user-attachments/assets/4b591b15-a62f-47a5-81e7-7dc4427a2bdd" />
<img width="700" height="700" alt="image" src="https://github.com/user-attachments/assets/234d74aa-f40d-4bb2-8878-1fe6f133882c" />

## Developed by - Tanishk Sarraf
