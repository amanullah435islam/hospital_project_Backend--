# 🏥 Hospital Management System – Backend

A professional **Hospital Management System Backend** built with **Java and Spring Boot**, designed to manage core hospital operations including users, patients, doctors, appointments, prescriptions, authentication, authorization, and secure user access.

The project follows a layered backend architecture and implements modern authentication and security mechanisms such as **JWT authentication, role-based authorization, email verification, password reset, Google authentication, and WebAuthn/fingerprint authentication**.

---

## 📌 Project Overview

The **Hospital Management System Backend** provides REST APIs for managing hospital-related operations.

The system is designed around different hospital roles and modules, allowing authorized users to access appropriate resources while keeping authentication and authorization centralized through Spring Security.

### Core Modules

* 👤 User Management
* 🧑‍⚕️ Doctor Management
* 🧑‍🦽 Patient Management
* 📅 Appointment Management
* 💊 Prescription Management
* 🔐 Authentication & Authorization
* 📧 Email Verification
* 🔑 Forgot Password
* 🔄 Reset Password
* 🔵 Google Authentication
* 👆 Fingerprint / WebAuthn Authentication
* 👮 Role-Based Authorization

---

# ✨ Key Features

## 👤 User Management

The system provides user management functionality for hospital users.

Features include:

* User registration
* User profile management
* User authentication
* Role assignment
* Secure password storage
* User authorization

---

## 🔐 Authentication System

The backend implements a complete authentication workflow using Spring Security.

### Authentication Features

* User Registration
* User Login
* JWT Authentication
* Access Token
* Refresh Token
* Logout
* Token validation
* Protected APIs
* Role-based authorization

---

## 📧 Email Verification

After registration, users can verify their email address through the verification process.

```text
User Registration
       ↓
Validate User Information
       ↓
Create User
       ↓
Generate Verification Token
       ↓
Send Verification Email
       ↓
User Opens Verification Link
       ↓
Verify Token
       ↓
Account Verification Complete
```

---

# 🔑 Forgot Password & Reset Password

The system provides a secure password recovery workflow.

```text
Forgot Password
       ↓
Enter Registered Email
       ↓
Generate Reset Token
       ↓
Send Reset Link
       ↓
User Opens Reset Link
       ↓
Validate Reset Token
       ↓
Enter New Password
       ↓
Encrypt Password
       ↓
Update Password
```

---

# 🔵 Google Authentication

The project supports authentication through **Google OAuth2**.

```text
User
 ↓
Google Login
 ↓
Google Authentication
 ↓
Google User Information
 ↓
Backend Authentication
 ↓
Application Login
 ↓
Authorized Access
```

---

# 👆 Fingerprint / WebAuthn Authentication

The project also includes a **WebAuthn-based authentication flow** for modern passwordless authentication.

The WebAuthn mechanism can use platform authenticators such as fingerprint or other supported device authentication methods.

```text
User
 ↓
WebAuthn / Fingerprint
 ↓
Browser / Device Authenticator
 ↓
Credential Verification
 ↓
Backend Verification
 ↓
Authentication Success
 ↓
Authorized Access
```

---

# 👮 Role-Based Authorization

Different users can be assigned different roles.

Example:

```text
USER
DOCTOR
PATIENT
ADMIN
```

Role-based authorization controls access to protected resources.

```text
Authenticated User
        ↓
       Role
        ↓
Authorization Check
        ↓
 ┌──────┼─────────┐
 ↓      ↓         ↓
ADMIN  DOCTOR   PATIENT
 ↓      ↓         ↓
Allowed Resources
```

> The exact roles and permissions should match the role definitions currently implemented in the repository.

---

# 🧑‍⚕️ Doctor Management

The backend includes doctor-related functionality.

Possible operations include:

* Doctor registration/profile management
* Doctor information
* Doctor availability
* Doctor-related appointments
* Doctor prescriptions

The exact completed operations should be verified against the current controller/service implementation.

---

# 🧑‍🦽 Patient Management

The system provides patient management functionality.

Core operations include:

* Create Patient
* Get Patient
* Get All Patients
* Update Patient
* Delete Patient

Typical REST flow:

```text
Request DTO
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

# 📅 Appointment Management

The backend supports hospital appointment management between patients and doctors.

Typical appointment flow:

```text
Patient
   ↓
Select Doctor
   ↓
Select Appointment
   ↓
Appointment Request
   ↓
Validation
   ↓
Appointment Created
   ↓
Doctor / Patient Access
```

Appointment functionality can be extended with:

* Appointment scheduling
* Appointment status
* Doctor availability
* Appointment history
* Cancellation
* Rescheduling

---

# 💊 Prescription Management

The backend includes prescription-related functionality connecting doctors and patients.

Typical flow:

```text
Doctor
  ↓
Patient
  ↓
Create Prescription
  ↓
Prescription Details
  ↓
Patient Access
```

A prescription can be extended to include:

* Medicine
* Dosage
* Frequency
* Duration
* Doctor information
* Patient information
* Prescription date

---

# 🏗️ Backend Architecture

The project follows a layered Spring Boot architecture.

```text
src/main/java
│
├── controller
│
├── service
│
├── repository
│
├── entity / model
│
├── dto
│
├── response
│
├── security
│
├── exception
│
├── validation
│
└── configuration
```

---

# 📊 Layer Responsibilities

| Layer          | Responsibility                          |
| -------------- | --------------------------------------- |
| Controller     | Handles HTTP requests and API responses |
| Service        | Contains business logic                 |
| Repository     | Handles database operations             |
| Entity / Model | Represents persistent/domain data       |
| DTO            | Transfers request and response data     |
| Security       | Authentication and authorization        |
| Validation     | Validates incoming data/business rules  |
| Exception      | Centralized exception handling          |
| Configuration  | Application and security configuration  |

---

# 🔄 General API Flow

```text
Client
  │
  ▼
Request
  │
  ▼
Controller
  │
  ▼
Request DTO
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
Database
  │
  ▼
Entity
  │
  ▼
Service
  │
  ▼
Response DTO
  │
  ▼
Controller
  │
  ▼
HTTP Response
```

---

# 🔐 Security Architecture

The security layer protects the hospital APIs.

```text
Client
  │
  │ Authorization: Bearer <JWT>
  ▼
Spring Security Filter
  │
  ▼
JWT Validation
  │
  ▼
Authentication
  │
  ▼
Role / Permission Check
  │
  ▼
Protected Controller
  │
  ▼
Service
```

---

# 🎫 Token-Based Authentication

The authentication system can use different tokens for different security workflows.

| Token                | Purpose                     |
| -------------------- | --------------------------- |
| Access Token         | Access protected APIs       |
| Refresh Token        | Generate a new access token |
| Verification Token   | Verify user email           |
| Password Reset Token | Reset forgotten password    |

---

# 🔐 Password Security

User passwords should never be stored as plain text.

The authentication system uses password hashing before storing credentials.

```text
Plain Password
      ↓
Password Encoder
      ↓
Encrypted / Hashed Password
      ↓
Database
```

---

# 📦 DTO-Based Design

The project follows a DTO-based API design to keep API models separated from database entities.

### Request

```text
Client
  ↓
RequestDTO
  ↓
Controller
  ↓
Service
```

### Response

```text
Database
  ↓
Entity
  ↓
Service
  ↓
ResponseDTO
  ↓
Controller
  ↓
Client
```

For standard CRUD operations:

| Operation | RequestDTO | ResponseDTO |
| --------- | ---------: | ----------: |
| Save      |          ✅ |           ✅ |
| Get All   |          ❌ |           ✅ |
| Get By ID |          ❌ |           ✅ |
| Update    |          ✅ |           ✅ |
| Delete    |          ❌ |    Optional |

---

# 🛡️ Validation & Exception Handling

The backend is designed with centralized validation and exception handling.

The system can handle:

* Invalid request data
* Missing required fields
* Invalid authentication
* Unauthorized access
* Resource not found
* Business rule violations
* Database-related exceptions

A centralized exception handler provides consistent API error responses.

---

# 🧩 CRUD Pattern

The backend follows a standard CRUD architecture.

```text
POST
  ↓
Save

GET
  ↓
Get All

GET /{id}
  ↓
Get By ID

PUT /{id}
  ↓
Update

DELETE /{id}
  ↓
Delete
```

Example service structure:

```java
public PatientResponse save(PatientRequest request);

public List<PatientResponse> getAll();

public PatientResponse getById(Long id);

public PatientResponse update(Long id, PatientRequest request);

public void delete(Long id);
```

---

# 🛠️ Technologies Used

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* REST API
* JWT
* BCrypt / Password Encoding
* OAuth2 / Google Authentication
* WebAuthn
* Maven
* Relational Database
* DTO Pattern
* Global Exception Handling
* Bean Validation

---

# 📂 Project Modules

```text
Hospital Management System
│
├── Authentication
│   ├── Registration
│   ├── Login
│   ├── Email Verification
│   ├── JWT
│   ├── Refresh Token
│   ├── Logout
│   ├── Forgot Password
│   ├── Reset Password
│   ├── Google Authentication
│   └── WebAuthn / Fingerprint
│
├── User
│
├── Doctor
│
├── Patient
│
├── Appointment
│
└── Prescription
```

---

# ▶️ How to Run

## 1. Clone Repository

```bash
git clone https://github.com/amanullah435islam/hospital_project_Backend--.git
```

## 2. Open the Project

Open the project using:

* IntelliJ IDEA
* Eclipse
* VS Code

---

## 3. Configure Database

Configure your database connection in:

```text
src/main/resources/application.properties
```

or:

```text
application.yml
```

Use your local database credentials.

---

## 4. Configure Email

Configure the required SMTP settings for:

* Email verification
* Forgot password
* Password reset

Do not commit real email passwords, API keys, or other secrets to GitHub.

---

## 5. Configure Google OAuth2

Add your Google OAuth2 client configuration through environment variables or secure application configuration.

Never expose production OAuth credentials in the repository.

---

## 6. Run the Application

### Maven Wrapper – Linux/macOS

```bash
./mvnw spring-boot:run
```

### Maven Wrapper – Windows

```bash
mvnw.cmd spring-boot:run
```

Or run the main Spring Boot application class directly from your IDE.

---

# 🧪 API Testing

The backend APIs can be tested using tools such as:

* Postman
* Swagger/OpenAPI, if configured
* Browser for supported GET endpoints
* Frontend applications

Recommended testing sequence:

```text
1. Register User
       ↓
2. Verify Email
       ↓
3. Login
       ↓
4. Receive JWT
       ↓
5. Access Protected API
       ↓
6. Test Role-Based Access
       ↓
7. Create Patient / Doctor Data
       ↓
8. Create Appointment
       ↓
9. Create Prescription
       ↓
10. Test Password Recovery
```

---

# 🔮 Planned / Future Modules

You mentioned that some hospital functionality is still remaining. Those should be added gradually rather than marking them as already completed.

Potential future modules include:

* 🏥 Hospital / Branch Management
* 🏢 Department Management
* 🛏️ Bed & Ward Management
* 🔬 Diagnostic / Laboratory Management
* 💊 Pharmacy Management
* 📦 Medicine Inventory
* 💰 Billing & Invoice
* 💳 Payment Management
* 📊 Hospital Dashboard
* 📈 Reports & Analytics
* 🩺 Doctor Schedule Management
* 📋 Medical History
* 🔔 Notification System
* 📧 Automated Email Notifications
* 📝 Audit Logging

---

# 🚀 Future Technical Improvements

The backend can be further improved with:

* Swagger / OpenAPI documentation
* Unit Testing
* Integration Testing
* Testcontainers
* Docker
* Docker Compose
* CI/CD Pipeline
* Database Migration with Flyway/Liquibase
* Redis Caching
* Centralized Logging
* Monitoring
* API Rate Limiting
* Production Deployment
* Cloud Deployment

---

# 🎯 Project Status

**Status: 🚧 In Development**

The current backend already includes major hospital and security functionality, including:

* User Management
* Patient Management
* Doctor Management
* Appointment Management
* Prescription Management
* Authentication
* JWT Security
* Role-Based Authorization
* Email Verification
* Forgot Password
* Reset Password
* Google Authentication
* WebAuthn / Fingerprint Authentication

Additional hospital modules and production-level improvements will be implemented progressively.

---

# 📚 Learning Outcomes

This project demonstrates practical experience with:

* Spring Boot Backend Development
* REST API Development
* Spring Security
* JWT Authentication
* Role-Based Authorization
* OAuth2 Authentication
* WebAuthn Authentication
* Email Verification
* Password Recovery
* DTO Architecture
* Service Layer Architecture
* Repository Pattern
* JPA / Hibernate
* CRUD Operations
* Exception Handling
* Validation
* Database Integration
* Secure Backend Development

---

# 👨‍💻 Developed By

**Md. Amanullah Islam**

Software Developer

**Primary Technologies:**

`Java` · `Spring Boot` · `Spring Security` · `JPA/Hibernate` · `REST API` · `JWT`

---

# 🔗 Repository

**GitHub Repository**

https://github.com/amanullah435islam/hospital_project_Backend--

---

# 📄 License

This project is developed for educational, portfolio, and professional development purposes.
