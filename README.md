## Table of contents
* [General info](#general-info)
* [System Requirement](#system-requirement)
* [Technologies](#technologies)
* [Setup and Build](#setup-and-build)
* [Build Executable jar](#build-executable-jar)
* [Run on Browser](#run-on-browser)
* [Unit test](#unit-test)

## General info
This project is simply parse two URL and Merge the resonse asynchronously into one Object.

## System Requirement
* JAVA version: 1.8
* Maven

## Technologies
Project is created with:
* JAVA version: 1.8
* spring-boot-starter-parent: 2.1.1.RELEASE
* junit: 4.12
* gson: 2.8.5

## Setup and Build
To run this project, install it locally using npm:

```
$ cd project-working-directory
$ ./mvnw spring-boot:run
```

## Build Executable jar

```
$ cd project-working-directory
$ mvn package
```
I creates a executable jar file in target\datafetcher-0.0.1-SNAPSHOT.jar

## Run on Browser
After project run successfully hit this URL

http://localhost:8080/ProcessURL

This will show the merged output of provided URLs

## Unit test
As per requiremnt, ``` DataMergerServiceTest.java ``` can be run to see the Application is actually works