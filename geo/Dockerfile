# FROM bellsoft/liberica-openjdk-alpine:18
# RUN apk add --no-cache maven
# WORKDIR /usr/src/geowebapp
# COPY . .
# RUN mvn clean install
# CMD ["java", "-jar", "target/geo-0.0.1-SNAPSHOT.jar"]

FROM bellsoft/liberica-openjdk-alpine:18 AS builder
RUN apk add --no-cache maven
COPY pom.xml /usr/src/geowebapp/
WORKDIR /usr/src/geowebapp
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean install
FROM bellsoft/liberica-openjdk-alpine:18
COPY --from=builder /usr/src/geowebapp/target/geo-0.0.1-SNAPSHOT.jar /usr/src/geowebapp/target/geo-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/geowebapp
CMD ["java", "-jar", "target/geo-0.0.1-SNAPSHOT.jar"]
