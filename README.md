Using this Skeleton:
===============================

1. Create a new public project in gitlab with the project name (i.e. `service-something-here`).
    1. Put the following text in the description to include with deployinator: `This app is a beacon_deploy_app.`
1. Clone this new project into local workspace
    1. `cd ~/workspace`
    1. `git clone git@gitlab.global.dish.com:service-layer/service-something-here.git`

## GENERAL SETUP

Running the Startup Script
--------------------------
The config_project.sh script is run from within the beacon-service-skeleton directory.  It will create a directory (named whatever you enter for the git-project-name) up one level.  In other words, it will be at the same level as the beacon-service-skeleton directory.

Once all the configuration is completed in the new project directory, it will perform all the necessary git commands to initialize, add, checkin, and push.  Since there is no good way to create a respository in the gitlab environment, you must create the gitlab repo (in the pivotal-ofm group) before running the script.


1. Create a repo for the new project.  This name is what you will enter as the git-project name below
1. cd into the beacon-service-skeleton directory
1. Run ``` ./config_project.sh```
1. Follow the prompts


IN THIS FILE:
-------------
1.  Replace all __git-friendly project name__ with your git project name (i.e. `service-something-here`)
1.  Replace all __user-friendly project name__ with your title-case project name (i.e. `Service Something Here`)
1.  Replace all __Pascal-cased application name__ with your project name in Pascal-case (i.e. `ServiceSomethingHere`)
1.  Replace all __package name__ with your Java package name (i.e. `somethinghere`)
1.  Replace all __underscore name__ with your ruby-friendly project name (i.e. `service_something_here`)
1.  Replace all __upper underscore name__ with a system env friendly case of your service name (i.e. `SERVICE_SOMETHING_HERE`)
1.  Go to the command line and run the following commands:


ON THE COMMAND LINE:
--------------------
```bash
$ cd src/main/java/com/dish/ofm/service/butch/config
$ mv ButchTestProjectConfig.java __Pascal-cased application name__Config.java
$ cd ../controller
$ mv ButchTestProjectController.java __Pascal-cased application name__Controller.java
$ cd ../..
$ mv butch __package name__
$ mv ButchTestProjectApplication.java __Pascal-cased application name__Application.java

$ cd ../../../../../../test/java/com/dish/ofm/service/butch/controller
$ mv ButchTestProjectControllerTest.java __Pascal-cased application name__ControllerTest.java
$ cd ../..
$ mv butch __package name__
$ cd ../../../../../../../acceptance/spec/helpers
$ mv butch_test_project_server.rb  __underscore name___server.rb
```

IN ENTIRE PROJECT:
------------------

1. Replace all butch-test with __git-friendly project name__
  1.  Replace all Butch Test Project with __user-friendly project name__
1.  Replace all 9099 with your simulator port number
1.  Replace all 8099 with your port number
1.  Replace all butchEndpoint with your camel-case REST endpoint (i.e. lookupCommitment)
1.  Replace all Woot! with your REST endpoint success description (i.e. Lookup of customer commitments Successful)
1.  Replace all butchSuffix with your REST endpoint suffix (i.e. lookup-commitment)
1.  Replace all ButchTestProject with the __Pascal-cased application name__ (i.e. `CommitmentService`)
1.  Replace all butch with __package name__
1.  Replace all BUTCH_TEST with __upper underscore name__
1.  Replace all butch_test_project with __underscore name__
1.  If Zipkin needs to be enabled as part of acceptance criteria, make sleuth enabled flags to true (i.e `enabled: true`) in application.yml file
1.  Run `gradle clean build`
1. Update Project settings in IntelliJ
    1. Close IntelliJ
    1. run cmd ```rm -r -f .idea```
    1. run cmd ```idea .```
1. Run `gradle clean build` to make sure everything is still intact
1. Re-import project in IntelliJ (using Existing sources) and delete 'GIT_PROJECT_NAME.iml' file if found

PORT DOCUMENTATION:
-------------------

Add the port number for the new service to the Dish IT Wiki and Documentation Story:
https://it-wiki.global.dish.com/index.php/Beacon_Services_Ports

SAMPLE README below:
===============================

# Butch Test Project API
The Butch Test Project API is a RESTful service for interacting with <SERVICE INFO HERE>.

## Project Requirements
- JDK 1.8.0-31 or higher
- Gradle 2.1 or higher

## Getting Started
1. Follow the instructions on the [wiki](https://it-wiki.global.dish.com/index.php/Dish_Root_Cert) to add the artifactory
cert to your local machine.
  1. Navigate to the your `[JAVA INSTALLATION DIR]/jre/lib/security`. On OSX, this can be achieved by running `cd "$(/usr/libexec/java_home)/jre/lib/security"`.
  1. Create file called `dish.cer` with the contents of the certificate on the [wiki](https://it-wiki.global.dish.com/index.php/Dish_Root_Cert).
  1. Run `sudo ../../bin/keytool --importcert -v -trustcacerts -file dish.cer -keystore cacerts`
  1. When prompted for a password, enter "changeit"
1. clone `https://gitlab.global.dish.com/service-layer/butch-test.git`
1. if git alias is setup on your terminal then run `gupdate`, otherwise run `git pull -r & git submodule update --init --recursive --remote`
1. run `gradle clean build`

## Starting the App
`gradle bootRun`

## Running Unit Tests
`gradle clean test`

## Running Integration Tests
[TODO]

## To add an external project as sub-module for the first time
1. Open terminal and go to ~/workspace/<project-dir>
1. execute `git submodule add https://gitlab.global.dish.com/service-layer/common-utils.git`
    1. This command will add git project as sub-module. This command generates .gitmodules file which further needs to be committed into git repository as well.
1. Add following common-utils as a compile time dependency to build.gradle
    project(':common-utils')
1. Add following to settings.gradle to include the new sub-module as part of main project
    include 'common-utils'

## Start the App in Debug mode

To run the app in debug mode :

1. `cd ~/workspace/butch-test`
1. `gradle clean assemble && SERVER_PORT=8099 java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar build/libs/butch-test.jar`

The app listens on port 5005
Create a new Remote Configuration in IntelliJ:

1. Run -> Edit Configurations.
1. Hit the '+' button and select 'Remote'.
1. Set the port as 5005.
1. Run -> Debug
1. Select the remote configuration

## Api Docs
Once the application is up and running you can see the [Swagger API Docs](http://localhost:8099/swagger-ui.html) in your browser.

#### 1. Butch Test Project
##### Sample Request

```
GET http://host:8099/butchSuffix

i.e GET http://localhost:8099/butchSuffix

```

##### Sample Response

```
Http Status code
```

# Eureka
Eureka configuration has been added to this skeleton. By default Eureka is disabled. Inorder to enable Eureka in CF environments add the following to the properties files:
```
eureka:
  client:
      enabled: true
```
