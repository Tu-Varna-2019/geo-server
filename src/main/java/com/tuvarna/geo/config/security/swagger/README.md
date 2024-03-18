# Steps for generating and yml file & generate Kotlin data classes based on the yml file

1. Access <http://localhost:8080/v3/api-docs>
Or alternatevly you can use this command to save an openapi.yml file by using:

```bash
curl http://localhost:8080/v3/api-docs.yaml > openapi.yaml

```

2. Install the openapi-generator cli to convert the yaml file to kotlin compliant code:

```homebrew

brew install openapi-generator
```

More info: <https://openapi-generator.tech/docs/installation/>


2. Copy the openapi.yaml file to the Kotlin Android app, and in the root directory type:

```bash
cd app/src/main/java/com/tuvarna/geo
openapi-generator generate -i config/openapi.yaml -g kotlin --config config/openapi-config.json --additional-properties=packageName=com.tuvarna.geo

```

Then try to move the `src/com/tuvarna/geo` sub folders to `com/tuvarna/geo` directory, the package should be still valid and you don't need to manually refactor it.

```bash
mv ./src/com/tuvarna/geo/* .
```

## Access the web ui

- <http://localhost:8080/swagger-ui/index.html>