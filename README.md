# java-selenium-example

## Requirements
- [Docker compose v2.0.0](https://docs.docker.com/compose/install/) - you can install the Docker Desktop app to make things easier.
- Docker engine must be running (e.g. have the Docker Desktop app running).

## Running Tests
```sh
    docker compose up
```
This will execute the [docker-compose.yml](./docker-compose.yml) file, which will spin up [Selenium Grid](https://www.selenium.dev/documentation/grid/) with containers for Chrome, Firefox and Edge test executions. A `tests` container will also be spun up containing Maven, Java 11, and all needed dependencies to run the tests. After tests are executed, an HTML report [target/cucumber-reports.html](target/cucumber-reports.html) will be generated at the root of the project.

By default, tests will run against the Chrome node, but this can be changed with the `BROWSER` environment variable (accepts `chrome`, `firefox`, and `edge`).

## Details

Page objects can be found in [src/main/java/com/selenium/example/pageObjects](src/main/java/com/selenium/example/pageObjects).

Cucumber feature files can be found in [src/test/java/com/selenium/example/features](src/test/java/com/selenium/example/features).

Cucumber step definitions can be found in [src/test/java/com/selenium/example/steps](src/test/java/com/selenium/example/steps).
