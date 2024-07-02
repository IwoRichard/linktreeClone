# Linktree Clone API with Spring Boot

This project is a Linktree clone API developed using Spring Boot. It allows users to manage their profiles and social links, add custom links, and perform various user management tasks securely. The API is equipped with security features including user registration, login, password change, and email verification tokens for account activation.

## Features

- **User Management**: Register, login, update profiles, and change passwords.
- **Profile Customization**: Update social links and add custom links.
- **Security**: Email verification for account activation, password change functionality, and secure authentication.
- **RESTful Endpoints**: Well-defined endpoints for various operations.

## Endpoints

- Register new users. `/register`
- Retrieve user details by user ID. `/{userId}`
- Update user profile information. `/updateProfile/{userId}`
- Update user social link. `/updateSocial/{userId}`
- Add a custom link to the user's profile. `/addCustomLink/{userId}`
- Change user password. `/changePassword`

## Getting Started

### Prerequisites

- Java 11+
- Maven
- Spring Boot

### Installation

Clone the project

```bash
  git clone https://github.com/yourusername/linktree-clone-api.git
```

Build the project

```bash
  mvn clean install
```

Run the application

```bash
  mvn spring-boot:run
```







