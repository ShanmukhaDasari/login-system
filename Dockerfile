FROM openjdk:17

WORKDIR /app

# Copy from root (not target) now
COPY loginsystem-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8008

CMD ["java", "-jar", "app.jar"]
