# SB Bank USSD Demo

A Spring Boot application that simulates USSD banking services with real API integration to SB Bank.

## Features

### Authentication
- Real API integration with SB Bank login endpoint
- 6-digit PIN authentication with device identification
- Session-based token management
- Secure access token storage

### Banking Services
- **My Account**: View balance and mini statements
- **Own Account Transfer**: Internal transfers between accounts
- Real-time transaction processing with reference tracking

### USSD Interface
- Browser-based phone emulator for testing
- Realistic phone UI with numeric keypad
- PIN masking for security
- Step-by-step menu navigation

## API Integration

### Login Endpoint
```
POST https://api.shabelle.shega.heranitech.com/api/User/login
```
- Request: PIN, device UUID, platform
- Response: User data and access token

### Transfer Endpoint
```
POST https://api.shabelle.shega.heranitech.com/api/Transfer/internal
```
- Request: From account, To account, Amount, Remark
- Authentication: Bearer token from login
- Response: Reference ID and transaction status

## How to Run

1. **Start the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   (Application runs on port 8081 by default)

2. **Access USSD Interface**
   - Open http://localhost:8081 in your browser
   - Use the on-screen keypad or type directly
   - Press **DIAL / SEND** to submit

3. **Test the Flow**
   - Enter PIN: `123456`
   - Navigate menus using numbered options
   - Test transfers with account numbers like `000000009`

## USSD Menu Flow

```
Welcome to SB -> Enter PIN (123456)
    |
Main Menu
| 1: My Accounts
| 2: Send To SB Account
| 3: Send To Other Bank
| 4: Send To Wallet
| 5: Airtime Top-up
|
| #: Next
| 0: Exit
```

### Implemented Features
- **Send To SB Account**: Full transfer flow with account validation and confirmation
- **Exit**: Clean session termination
- **Authentication**: Real API integration with token management

### Coming Soon
- My Accounts (option 1)
- Send To Other Bank (option 3)
- Send To Wallet (option 4)
- Airtime Top-up (option 5)
Welcome → Enter PIN (123456)
    ↓
Main Menu
├── 1. My Account
│   ├── 1. View Balance
│   ├── 2. Mini Statement
│   └── 3. Back to Main Menu
├── 2. Own Account Transfer
│   ├── Enter destination account (9 digits)
│   ├── Enter amount
│   └── Confirm transfer
└── 3. Exit
```

## Configuration

The application uses these configuration files:
- `application.properties`: Base configuration
- `application-local.properties`: Local environment settings (API endpoints)

## Dependencies

- Spring Boot 3.5.13
- Spring Web (REST APIs)
- Lombok (Reduced boilerplate)
- Maven build system

## Testing

The application has been tested with:
- ✅ Login authentication flow
- ✅ Session management
- ✅ Account transfer functionality
- ✅ Error handling and validation
- ✅ USSD menu navigation

---