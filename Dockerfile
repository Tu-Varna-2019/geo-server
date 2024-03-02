# Use OpenJDK 18 as base image
FROM bellsoft/liberica-openjdk-alpine:18

# Set the working directory inside the container
WORKDIR /usr/src/myapp

# Copy the compiled JAR file from the target directory to the container
COPY /geo/target/geo-0.0.1-SNAPSHOT.jar.original /usr/src/myapp/geo.jar

# Command to run the application when the container starts
CMD ["java", "-cp", "geo-0.0.1-SNAPSHOT.jar.original", "com.tuvarna.geo.Main"]
