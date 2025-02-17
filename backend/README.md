# City Finder API

## Getting Started

### Dependencies

Install the project's dependencies with the following command:
```bash
./mvnw clean package -T1C -U -Dmaven.test.skip=true
```

If the command results in an error, try the following:

- Check your terminal for the permissions associated with the `mvnw` command.
- Enter `chmod +x mvnw` if the command lacks the executable permission.

### Run

Start the application with:
```bash
./mvnw spring-boot:run 
```

You can then access the swagger UI at `http://localhost:8080/swagger-ui.html`.
You can also access the H2 console at `http://localhost:8080/h2-console`.

### Tests

Run the tests with:
```bash
./mvnw test -Dcheckstyle.skip=true
```

### Checkstyle

For the linter, we use the [Maven Checkstyle Plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/), which relies on the [Checkstyle linting rules](https://checkstyle.org/). Those rules are defined in the `/checkstyle/checkstyle.xml` file and are configurable. You can run the linter with the following command:

```bash
./mvnw checkstyle:check
```