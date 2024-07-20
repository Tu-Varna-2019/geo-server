# Note

Since the app requires a valid AWS access key, secret key & valid PostgreSQL credentials, please make sure to replace the placeholder values from `src/main/application.propeties` wit valid ones!

# Docker build instruction

1. Build Dockerfile into an image:

```bash
cd /geo-server
docker build -t iliyankostov/geo-server:0.0.1-RELEASE .
```

2. Run the container

```bash
 docker run -d -p 8080:8080 --name geo-server --env AWS_REGION=eu-west-1 iliyankostov/geo-server:0.0.1-RELEASE
```
Be sure  to replace the environment variable AWS_REGION with the region you are using.

# Docker compose

1. Run the geo-server microservice:

```bash
docker compose up
```
