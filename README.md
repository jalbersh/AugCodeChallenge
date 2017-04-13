# Beacon Session API

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
1. clone `https://gitlab.global.dish.com/service-layer/beacon-session.git`
1. if git alias is setup on your terminal then run `gupdate`, otherwise run `git pull -r & git submodule update --init --recursive --remote`
1. run `gradle clean build`

## Starting the App
`gradle bootRun`

## Running Unit Tests
`gradle clean test`

## Running Integration Tests
```bash
$ cd acceptance
$ rspec
```

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

1. `cd ~/workspace/beacon-session`
1. `gradle clean assemble && SERVER_PORT=9060 java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar build/libs/beacon-session.jar`

The app listens on port 5005
Create a new Remote Configuration in IntelliJ:

1. Run -> Edit Configurations.
1. Hit the '+' button and select 'Remote'.
1. Set the port as 5005.
1. Run -> Debug
1. Select the remote configuration

## Api Docs
Once the application is up and running you can see the [Swagger API Docs](http://localhost:9060/swagger-ui.html) in your browser.

#### 1. Beacon Session
##### Sample Request

```
GET http://host:9060/get-session

i.e GET http://localhost:9060/get-session

```

##### Sample Response

```
Http Status code
```
