FROM maven:3.8-openjdk-18-slim AS stage
WORKDIR /home/app
COPY . /home/app/
ENV AWS_REGION=eu-west-1
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18-jdk-alpine
COPY --from=stage /home/app/target/geo-server-3.2.3.jar geo-server.jar
EXPOSE 8080

LABEL org.opencontainers.image.source=https://github.com/Tu-Varna-2019/geo-server \
	version="0.0.1-RELEASE" \
	description="Geo spatial Java Spring app"

ENTRYPOINT [ "sh","-c","java -jar /geo-server.jar" ]
