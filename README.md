# BILLBUDDY /PaisaTrack — Full-stack version

This package keeps the supplied HTML/CSS/JavaScript interface and adds a Java Spring Boot REST backend with MySQL persistence. Transactions, split bills, and settlement history are stored in MySQL. Browser localStorage remains a local cache/offline fallback.

## Requirements
- Java 17 or newer
- Maven 3.9+
- MySQL Server 8.x (Workbench is a database GUI; it does not replace the MySQL Server)

## 1. Create the database
In MySQL Workbench, connect to your running local MySQL Server and execute:

```sql
CREATE DATABASE IF NOT EXISTS paisatrack;
```

The application creates/updates its tables automatically when it starts.

## 2. Configure the database password
Open a terminal in the `backend` folder. Set the password used by your MySQL `root` account.

Windows PowerShell:
```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_MYSQL_PASSWORD"
```

If your local root account has no password, set `$env:DB_PASSWORD=""` or omit it.

Optional environment variables:
- `DB_URL` (default: `jdbc:mysql://localhost:3306/paisatrack?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata`)
- `DB_USERNAME` (default: `root`)
- `DB_PASSWORD` (default: empty)

## 3. Start the application
From the `backend` directory, run:

```bash
mvn spring-boot:run
```

Open `http://localhost:8080` in your browser. Do not open `index.html` directly when you want database sync; use the Spring Boot URL so the frontend and API share the same origin.

## 4. API endpoints
- `GET /api/health`
- `GET /api/transactions`
- `PUT /api/transactions/sync`
- `GET /api/split-bills`
- `PUT /api/split-bills/sync`
- `GET /api/settlements`
- `PUT /api/settlements/sync`

The sync endpoints replace the stored collection with the submitted collection. This is suitable for this single-user college/demo project; it is not designed for simultaneous multi-user accounts.

## Notes
- On startup, the frontend loads records from the backend. If the database collection is empty and browser cache has records, it uploads that cache to MySQL once.
- If the backend is not available, the frontend continues using browser localStorage and displays a warning.
- There is no authentication in this version. Do not expose it publicly with personal financial data.
- No real payments are made; settlement entries are manual records, as in the supplied frontend.
