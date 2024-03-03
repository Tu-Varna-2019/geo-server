# Use OpenJDK 18 as base image
FROM bellsoft/liberica-openjdk-alpine:18

# Install Maven
RUN apk add --no-cache maven

# Set the working directory inside the container
WORKDIR /usr/src/myapp

# Copy the compiled JAR file from the target directory to the container
COPY target/geo-0.0.1-SNAPSHOT.jar /usr/src/myapp/geo.jar

# Command to run the application when the container starts
CMD ["java", "-jar", "geo.jar"]

