# Project Version Control Guidelines

## Branches

- `main`: Main branch for linear commit history and releases.
- `dev`: Developer branch for ongoing updates.

## Branching Strategy

- Create `hotfix/<NAME>` from `main` to update both `main` and `dev`.
- Create `feature/<NAME>` from `dev` to add new features.

## Push Policy

- Direct pushes to `main` are blocked.
- Developers contribute by making commits with pull requests.

## Development Strategy

- `feature` branch is allowed to fail.
- `dev` branch should not fail.
- `main` branch must not fail.