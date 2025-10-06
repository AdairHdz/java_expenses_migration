# Build stage
FROM maven:3.8.6-eclipse-temurin-8 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM tomcat:9.0-jdk8
WORKDIR /usr/local/tomcat

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built war file from build stage
COPY --from=build /app/target/expenses.war /usr/local/tomcat/webapps/ROOT.war

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
