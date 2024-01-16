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
   - [Actions](#Actions)
     - actions/checkout@v4
     - actions/setup-java@v4
     - actions/upload-artifact@master
     - actions/download-artifact@master
     - Ilshidur/action-discord@master
     
7. [AWS](#aws)

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

##### Environment Variables

- **GIT_USER_EMAIL**: Email for Git actions.
- **GIT_USER_NAME**: Username for Git actions.

##### Workflow Steps

1. **Set Git Identity**: Configures Git with the provided email and username for the workflow.
2. **Checkout Code**: Checks out the repository code with a fetch depth of 0.
3. **Set up JDK 17**: Configures the Java Development Kit (JDK) version 17.
4. **Grant Execute Permission for Gradlew**: Allows the Gradle wrapper script to be executed.
5. **Build with Gradle**: Builds the project using Gradle.
6. **Run Unit Tests**: Executes unit tests for the project.
7. **Run Integration Tests**: Executes integration tests for the project.
8. **Extract Branch Name**: Retrieves the current branch name from the environment.
9. **Merge Hotfix into Dev**: Merges the hotfix branch into the 'dev' branch using fast-forward-only mode.
10. **Delete Hotfix Branch**: Deletes the hotfix branch after a successful merge.

## Maintenance

### Actions

#### actions/checkout@v4

This action checks out the repository, so the workflow can access it. By default only a single commit is fetched. The auth token is persisted in the local git config.
This enables the scripts to run authenticated git commands. 

[Link to the repo of checkout](https://github.com/actions/checkout)

#### actions/setup-java@v4

This action provides the following functionality for GitHub Action runners:

    -   Downloading and setting up a requested version of Java
    -   Extracting and caching custom version of Java from a local file
    -   Configuring runner for publishing using Apache Maven
    -   Configuring runner for publishing using Gradle
    -   Configuring runner for using GPG private key
    -   Registering problem matchers for error output
    -   Caching dependencies managed by Apache Maven
    -   Caching dependencies managed by Gradle
    -   Caching dependencies managed by sbt
    -   Maven Toolchains declaration for specified JDK versions

[Link to the repo of setup-java](https://github.com/actions/setup-java)

#### actions/upload-artifact@master

This action uploads the artifact. The artifact allows you to persist data after a job has completed, and shar that data with another job in the same workflow.

[Link to the repo of upload-artifact@master](https://github.com/actions/upload-artifact)

#### actions/download-artifact@master

This action downloads the artifact so the artifact can be used in different actions of your workflows.

[Link to the repo of download-artifact](https://github.com/actions/download-artifact)

#### Ilshidur/action-discord@master

Sends a discord notification message.

[Link to the repo of Ilshidur/action-discord@master](https://github.com/Ilshidur/action-discord)

## AWS

1. Create an account for AWS
2. [Link to the official documentation](https://docs.aws.amazon.com/efs/latest/ug/gs-step-one-create-ec2-resources.html)
3. When your EC2 instance is up and running connect to it with the command line

When you are connected it should look something like that:

![Picture](imageResources/AWS_1.PNG)

Now to install docker first of all run

````
sudo apt update
````

to update everything that is already on your instance installed. Now run the following commands:

````
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
````

````
curl -fsSl https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
````

````
sudo apt-get install docker-ce docker-ce-cli containerd.io
````

````
apt-cache madison docker-ce
sudo apt-get install docker-ce docker-ce-cli containerd.io
````

````
sudo apt install docker.io
sudo apt install docker-compose
````

I took these commands from the following YouTube video:

[Link to the video](https://www.youtube.com/watch?v=awFLzy0XwXo)
