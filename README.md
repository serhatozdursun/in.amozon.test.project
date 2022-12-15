# Test Automation Framework for amazon.in

![badge](https://github.com/serhatozdursun/in.amozon.test.project/actions/workflows/maven.yml/badge.svg)

failing because of [chrome issue](https://bugs.chromium.org/p/chromedriver/issues/detail?id=2473)
,in local execution no issue.

- Example Test Automation Framework
    * [Overview](#overview)
        + [Requirements](#requirements)
        + [Project architecture](#architecture)
        + [Features](#features)
        + [Selenium Grid](#grid)
        + [How to run](#run)
        + [Dockerizing](#docker)


<a name="overview"></a>

## Overview

The project has been developed with java.

<a name="requirements"></a>

### Requirements

- Java JDK 18 or higher
- Maven 3.8.5 or higher

<a name="architecture"></a>
## Project architecture

The project is written with POM models. All Helper classes are under the helper package. I've created only the required ones to complete the assignment.
But I've considered the next level of this project. So I created different helper classes for the different features of Selenium.

<a name="features"></a>
## Project features

Chrome is the default browser for project if you want you can change default browser from [config.properties](https://github.com/serhatozdursun/in.amozon.test.project/blob/master/src/main/resources/config.properties) like below
```
default.browser=safari
```

<a name="grid"></a>
## Selenium Grid
if you pass the selenium the grid URL in to the [config.properties](https://github.com/serhatozdursun/in.amozon.test.project/blob/master/src/main/resources/config.properties) file, as a parameter like below the test will run on the selenium grid

```
grid.url=http://192.168.1.53:4444/
```
<a name="run"></a>
## How to run

You can run the project with following command
```
mvn test
```

<a name="docker"></a>
## Docker
You can use [DockerFile](https://github.com/serhatozdursun/in.amozon.test.project/blob/master/DockerFile) from repo to dockerize the framework
