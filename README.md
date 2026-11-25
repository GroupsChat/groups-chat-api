# Groups Chat API

Groups Chat API is the self-hosted application server for Groups Chat. The backend is a
Spring Boot modular monolith that uses PostgreSQL as its source of truth and can use Redis
for transient coordination as the product evolves.

The current repository contains the application foundation and its development and CI
contracts. Product modules are described in
[`docs/groups-chat-arquitetura-base-v1.md`](docs/groups-chat-arquitetura-base-v1.md).

## Prerequisites

- Java 25 with `JAVA_HOME` pointing to the Java 25 installation
- Docker with Compose support
- Node.js 24 only when using the optional local Commitlint hook

The project uses the Maven Wrapper, so a separate Maven installation is not required.

## Development

Run the application with its local PostgreSQL and Redis services:

```bash
./mvnw spring-boot:run
```

Spring Boot's Docker Compose integration starts the services declared in `compose.yaml`.
To manage them explicitly, use `docker compose up -d` and `docker compose down`.

Useful commands:

```bash
./mvnw spotless:apply              # Apply Java formatting
./mvnw validate                    # Check Java/Maven, formatting, and static analysis
./mvnw test                        # Run unit tests (*Test)
./mvnw verify                      # Run all checks, integration tests, coverage, and build
./mvnw verify -DskipUnitTests=true # Run integration tests without unit tests
```

Integration tests use Testcontainers and require access to a Docker daemon. Build outputs
and reports are generated under `target/`.

## Contributing

See [`CONTRIBUTING.md`](CONTRIBUTING.md) for the branch, commit, pull request, and local
hook conventions. CI behavior and troubleshooting are documented in
[`docs/development/ci.md`](docs/development/ci.md).

## License

This project is licensed under the terms in [`LICENSE`](LICENSE).
