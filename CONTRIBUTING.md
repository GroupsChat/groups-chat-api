# Contributing to Groups Chat API

## Development flow

The repository follows GitHub Flow. `main` is the only permanent branch and changes are
merged through pull requests using squash merge.

Use a short-lived branch with one of these prefixes:

- `feat/`, `fix/`, `refactor/`, `docs/`, `test/`
- `chore/`, `ci/`, `perf/`, `hotfix/`

Branch names, commit messages, and pull request titles are written in English.

## Commit and pull request convention

Commits and pull request titles follow Conventional Commits:

```text
<type>(<optional-scope>): <description>
```

Allowed types are `build`, `chore`, `ci`, `docs`, `feat`, `fix`, `perf`, `refactor`,
`revert`, `style`, and `test`. Use `!` for a breaking change:

```text
feat(auth)!: change session response contract
```

CI validates every commit introduced by a branch or pull request, not only the latest
commit. The pull request title is also validated because it becomes the squash commit
title.

## Local validation

Before opening or updating a pull request, run:

```bash
./mvnw validate
./mvnw test
./mvnw verify
```

`verify` requires Docker because `*IT` tests use disposable PostgreSQL containers. Unit
tests use the `*Test` suffix and must not depend on external services. Integration tests
use `*IT` and are executed by Maven Failsafe.

Apply formatting with:

```bash
./mvnw spotless:apply
```

## Optional commit hook

Install the isolated Commitlint dependencies and activate the repository hooks:

```bash
npm ci --prefix .github/tools/commitlint
git config core.hooksPath .githooks
```

The hook is a convenience. GitHub Actions remains the authoritative validation.

## Pull requests

- Keep changes focused and document validation in the PR template.
- Do not commit secrets, local environment files, or generated `target/` output.
- Resolve review conversations before merge.
- Update the branch with `main` when required by the repository ruleset.
- Merge only when `CI Gate` succeeds.
