# Project Version Control Guidelines

1. [Branches](#branches)
2. [Development Strategy](#development-strategy)
3. [Contributing to Repository Project](#contributing-to-repository-project)
4. [Push Policy](#push-policy)
5. [Workflow Scripts](#workflow-scripts)
   - [new_release.yml](#new_releaseyml)
      - [How to Use](#how-to-use)
      - [Trigger](#trigger)
      - [Inputs](#inputs)
   - [feature_progress.yml](#feature_progressyml)
      - [How to Use](#how-to-use-1)
      - [Triggers](#triggers)
   - [commit_hotfix.yml](#commit_hotfixyml)
      - [How to Use](#how-to-use-2)
      - [Triggers](#triggers-1)
6. [Maintenance](#maintentance)
   - [Jobs](#Jobs)
     - Build
       - steps
         - <name>sdf
     - Test
       - steps
         - <name>sdf
     - Lint
         - steps
             - <name>sdf
     - Sync-Braches
         - steps
             - <name>sdf


## Branches

- `main`: Main branch for linear commit history and releases.
- `dev`: Production branch with latest working code.
- `feature/*`: Sandbox for ongoing development.
- `hotfix/*`: One time commit branch for short call solutions.

## Development Strategy

- `feature/*` branch is allowed to fail.
- `dev` branch should not fail.
- `hotfix/*` branch must not fail.
- `main` branch must not fail.

## Contributing to Repository Project

- Create `hotfix/<NAME>` from `main` 
  - `hotfix/<NAME>` will be deleted from `origin` after pull-request
  - `dev` will be updated if tests succeed
  - `main` gets a merge request to support double-checking
- Create `feature/<NAME>` from `dev`
  - `feature/<NAME>` will be updated regardless of any tests
  - `dev` will be updated if tests succeed
  - `main` gets a merge request to support double-checking

## Push Policy

Pushing code without tests is generally not allowed in this system

1. Commit code with short and direct description \
   ![Image1](imageResources/image1.png)
2. Change to Pull Request Tab \
   ![Image2](imageResources/image2.png)
3. Create Pull Request with the same description of the commit \
   ![Image3](imageResources/image3.png)
4. Once the Checks are done the Changes are taken into effect in the dev branch.

### Workflow Scripts

#### new_release.yml

##### How to Use

1. Manually trigger `new_release.yml`, the `version number` is mandatory and to put in manually to avoid misunderstandings.


##### Trigger

`new_release.yml` is triggered manually by using the "workflow_dispatch" event.

##### Inputs

- **Version**: Version number for the release (required).

#### feature_progress.yml

##### How to Use

The developer works on a branch names `feature/*`
All delevopers writing new features for the project need to use `feature progress.yml`.

##### Triggers

- **Pushes**: Triggered on every push to branches starting with 'feature/'.
- **Pull Requests**: Triggered when a pull request is opened or updated on branches starting with 'feature/'.

#### commit_hotfix.yml

##### How to Use

The developer works on a branch names `hotfix/*`
If the deployed version of this project needs a hotfix, the developers need to use `commit_hotfix.yml`.

##### Triggers

- **Pushes**: Triggered on every push to branches starting with 'hotfix/'.
- **Pull Requests**: Triggered when a pull request is opened or updated on branches starting with 'hotfix/'.
