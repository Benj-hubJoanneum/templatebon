# Project Version Control Guidelines

## Branches

- `main`: Main branch for linear commit history and releases.
- `dev`: Developer branch for ongoing updates.

## Development Strategy

- `feature` branch is allowed to fail.
- `dev` branch should not fail.
- `main` branch must not fail.

## Contributing to Repository

- Create `hotfix/<NAME>` from `main` to update both `main` and `dev`.
- Create `feature/<NAME>` from `dev` to add new features.

## Push Policy

Pushing code without tests is generally not allowed in this organisation

1. Commit code with short and direct description
   ![Image1](imageResources/image1.png)
2. Change to Pull Request Tab
   ![Image2](imageResources/image2.png)
3. Create Pull Request with the same description of the commit
   ![Image3](imageResources/image3.png)
4. Once the Checks are done the Changes are taken into effect in the dev branch.


