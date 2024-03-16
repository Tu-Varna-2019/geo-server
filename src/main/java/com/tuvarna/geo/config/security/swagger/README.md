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
openapi-generator generate -i openapi.yaml -g kotlin -o .

```


## Access the web ui

- <http://localhost:8080/swagger-ui/index.html>