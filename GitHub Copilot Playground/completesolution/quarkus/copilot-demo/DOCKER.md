# Build a Quarkus native image

## Build the native executable

```bash
mvn clean package -Pnative
```

## Build the docker image using the Dockerfile.native-micro

```bash
docker build -f Dockerfile.native-micro -t quarkus/quarkus-native-micro .
```

## Run the docker image

```bash
docker run -i --rm -p 8080:8080 quarkus/quarkus-native-micro
```

## Test the application

```bash
curl -v http://localhost:8080/hello
```

