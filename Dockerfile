FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY . .

RUN chmod +x mvnw && ./mvnw clean package spring-boot:repackage -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/Library-Management-System-0.0.1-SNAPSHOT.jar"]
