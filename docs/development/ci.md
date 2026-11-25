# Continuous Integration

## Validation matrix

| Validation | Branch push | Pull request to `main` | Push to `main` |
| --- | --- | --- | --- |
| Conventional Commits | Yes | Yes | No |
| Pull request title | No | Yes | No |
| Java environment, formatting, Checkstyle | Yes | Yes | Yes |
| Unit and integration tests | No | Yes | Yes |
| Coverage and application package | No | Yes | Yes |

The workflows grant only `contents: read`. Pull requests run through the `pull_request`
event and never through `pull_request_target`, so untrusted fork code does not receive
elevated credentials.

## Workflows

- **Branch CI** runs commit validation and Java quality checks in parallel for every push
  outside `main`.
- **Pull Request CI** validates commits and quality before running `./mvnw -B -ntp verify`.
  Its final `CI Gate` job fails when any required job fails, is skipped, or is cancelled.
- **Main CI** reruns quality, tests, coverage, and packaging for the exact squash commit
  incorporated into `main`.

Older branch and pull request executions are cancelled when a newer commit arrives. Main
executions are not cancelled. Test, coverage, and application artifacts are retained for
14 days.

## Maven contracts

| Command | Contract |
| --- | --- |
| `./mvnw validate` | Enforce Java/Maven versions, Spotless, and Checkstyle |
| `./mvnw test` | Run `*Test` unit tests with Surefire |
| `./mvnw verify` | Run quality, unit tests, `*IT` integration tests, JaCoCo, and package |
| `./mvnw verify -DskipUnitTests=true` | Run integration tests without unit tests |
| `./mvnw spotless:apply` | Apply the configured Java formatter |

JaCoCo combines unit and integration coverage in `target/site/jacoco`. Coverage is
reported from the first day but has no mandatory threshold until the first product modules
provide a representative baseline.

## Commit ranges

Commitlint uses the complete Git history:

- Existing branch push: GitHub's `before..after` range.
- New branch push: `merge-base(origin/main, HEAD)..HEAD`.
- Pull request: `base.sha..head.sha`.

This prevents an invalid intermediate commit from being hidden by a later valid commit and
excludes history already present in `main`.

## Required repository settings

The `main` ruleset requires a pull request, the `CI Gate` status from GitHub Actions, an
up-to-date branch, resolved conversations, and linear history. Force pushes and deletion
are blocked, the bypass list is empty, and zero external approvals are required initially.

Repository merge settings allow only squash merge, use the pull request title for the
squash commit title, enable auto-merge, and delete merged branches automatically.

## Troubleshooting

### `release version 25 not supported`

Confirm both commands use Java 25 and that `JAVA_HOME` is not left pointing to an older
JDK:

```bash
java -version
./mvnw --version
```

### Integration tests cannot find Docker

`./mvnw verify` requires permission to access the Docker daemon. Confirm `docker version`
shows both client and server information. A client-only result means the daemon is not
accessible to the current user.

### Commit hook cannot find Commitlint

Install the isolated dependencies:

```bash
npm ci --prefix .github/tools/commitlint
```

The Node dependency tree is not used by the Java application.
