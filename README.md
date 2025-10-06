# Expenses Management Application

A Java web application for managing expenses, budgets, and monthly records built with Spring MVC, JPA/Hibernate, and MySQL.

## Technologies

- **Backend:** Spring Framework 5.3.34 (MVC, JPA, Transaction Management)
- **ORM:** Hibernate 5.6.15
- **Database:** MySQL 8.0
- **Build Tool:** Maven
- **Server:** Apache Tomcat 9.0
- **Java Version:** 1.8

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- MySQL 8.0 (for local development)
- Docker & Docker Compose (for containerized deployment)

## Running the Application

There are two ways to run this application:

### Option 1: Local Development (without Docker)

#### 1. Set up MySQL Database

Make sure MySQL is running locally on port 3307 (or update the port in `application.properties`).

Create the database:
```sql
CREATE DATABASE expenses_java_migration;
CREATE USER 'your_username'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON expenses_java_migration.* TO 'your_username'@'localhost';
FLUSH PRIVILEGES;
```

#### 2. Configure Database Connection

Create your local configuration file from the example:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Then edit `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/expenses_java_migration?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Note:** The `application.properties` file is ignored by git to protect your credentials. Use `application.properties.example` as a reference.

#### 3. Run the Application

```bash
mvn clean install
mvn tomcat7:run
```

The application will be available at: `http://localhost:8080`

---

### Option 2: Docker Deployment (Recommended)

#### 1. Configure Environment Variables

Copy the example environment file and update with your credentials:

```bash
cp .env.example .env
```

Edit `.env` file with your database credentials:

```env
# MySQL Database Configuration
MYSQL_ROOT_PASSWORD=your_secure_root_password
MYSQL_DATABASE=expenses_java_migration
MYSQL_USER=your_database_user
MYSQL_PASSWORD=your_secure_password

# Application Database Connection
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/expenses_java_migration?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME=your_database_user
SPRING_DATASOURCE_PASSWORD=your_secure_password
```

**Important:** The `.env` file contains sensitive credentials and should **never** be committed to version control.

#### 2. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f

# View logs for specific service
docker-compose logs -f app
docker-compose logs -f db

# Stop all services
docker-compose down

# Stop and remove volumes (deletes database data)
docker-compose down -v
```

The application will be available at: `http://localhost:8080`

MySQL will be available at: `localhost:3307` (mapped from container port 3306)

#### 3. Docker Services

The docker-compose setup includes:

- **db**: MySQL 8.0 database server
  - Port: 3307 (host) → 3306 (container)
  - Data persisted in Docker volume `mysql-data`
  - Healthcheck ensures database is ready before app starts

- **app**: Spring MVC application
  - Port: 8080
  - Automatically waits for database to be healthy
  - Built using multi-stage Docker build for optimized image size

#### 4. Rebuilding After Code Changes

```bash
# Rebuild the application image
docker-compose build app

# Restart the app service
docker-compose up -d app

# Or rebuild and restart in one command
docker-compose up -d --build app
```

## Project Structure

```
expenses/
├── src/
│   ├── main/
│   │   ├── java/com/expenses/
│   │   │   ├── config/          # Spring configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── repository/      # Data repositories
│   │   │   └── service/         # Business logic
│   │   ├── resources/
│   │   │   └── application.properties
│   │   └── webapp/              # Web resources
│   └── test/                    # Unit tests
├── Dockerfile                   # Application container definition
├── docker-compose.yml           # Multi-container orchestration
├── .env                         # Environment variables (DO NOT COMMIT)
├── .env.example                 # Environment template
├── .dockerignore               # Files to exclude from Docker build
└── pom.xml                     # Maven configuration
```

## Configuration Details

### How Configuration Works

The application uses a layered configuration approach:

1. **application.properties** - Default configuration for local development
2. **Environment Variables** - Override properties when running in Docker
3. **@PropertySource** - Spring loads properties from `application.properties`
4. **@Value annotations** - Inject properties into configuration beans

### Local vs Docker Configuration

**Local Development:**
- Reads from `application.properties`
- Connects to `localhost:3307`
- `.env` file is ignored

**Docker Deployment:**
- Reads from `application.properties` as baseline
- Environment variables from `.env` override specific properties
- Connects to `db:3306` (internal Docker network)

## API Endpoints

### Budget Endpoints
- `GET /api/budgets` - Get all budgets
- `GET /api/budgets/{id}` - Get budget by ID
- `POST /api/budgets` - Create new budget
- `PUT /api/budgets/{id}` - Update budget
- `DELETE /api/budgets/{id}` - Delete budget

### Expense Endpoints
- `GET /api/expenses` - Get all expenses
- `GET /api/expenses/{id}` - Get expense by ID
- `POST /api/expenses` - Create new expense
- `PUT /api/expenses/{id}` - Update expense
- `DELETE /api/expenses/{id}` - Delete expense

### Monthly Record Endpoints
- `GET /api/monthly-records` - Get all monthly records
- `GET /api/monthly-records/{id}` - Get monthly record by ID
- `POST /api/monthly-records` - Create new monthly record
- `PUT /api/monthly-records/{id}` - Update monthly record
- `DELETE /api/monthly-records/{id}` - Delete monthly record

## Database Schema

The application uses Hibernate's `ddl-auto=update` mode, which automatically creates and updates tables based on JPA entities:

- **budgets** - Budget tracking
- **expenses** - Individual expense records
- **monthly_records** - Monthly summaries

## Security Considerations

1. **Never commit credentials** - Add `.env` and `application.properties` to `.gitignore`
2. **Use environment-specific configs** - Different credentials for dev/staging/production
3. **MySQL Public Key Retrieval** - The `allowPublicKeyRetrieval=true` parameter is needed for MySQL 8.0's caching_sha2_password authentication
4. **SSL disabled** - `useSSL=false` is for development only; enable SSL in production

## Troubleshooting

### Application fails to start with "Public Key Retrieval is not allowed"

Add `allowPublicKeyRetrieval=true` to your JDBC URL:
```
jdbc:mysql://host:port/database?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

### Cannot connect to database

**Local Development:**
- Verify MySQL is running: `mysql -u your_username -p`
- Check the port matches in `application.properties`
- Verify database exists: `SHOW DATABASES;`

**Docker:**
- Check database container is healthy: `docker-compose ps`
- View database logs: `docker-compose logs db`
- Verify network connectivity: `docker-compose exec app ping db`

### Port already in use

If port 8080 or 3307 is already in use, update the ports in:
- `docker-compose.yml` (for Docker deployment)
- `pom.xml` tomcat7-maven-plugin configuration (for local development)

### Maven build fails

```bash
# Clean and rebuild
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

## Development

### Running Tests

```bash
mvn test
```

### Building WAR file

```bash
mvn clean package
```

The WAR file will be generated at: `target/expenses.war`

## License

This project is for educational purposes.
