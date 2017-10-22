# CS504-running-information-analysis
running-information-analysis is a RESTful service in spring boot, using Maven as build tool.

Feature List:

- Upload runningInfo
- Delete all runningInfo
- Delete runningInfo by runningId
- Find runningInfo by runningId
- Find runningInfo by userId
- Get all runningInfo with sort and pagination. Page number, page size, sort direction, and sort property can be customized

## Requirements

- Java Platform (JDK) 8
- Apache Maven

## Installation and Quick Start
### 1. Download project file
```
git clone git@github.com:YIZHUSTC/CS504-running-information-analysis.git
cd CS504-running-information-analysis
```
### 2. Login MySQL database
```
mysql -u root -p
```
Create table RUNNING_ANALYSIS in database runningInfoAnalysis_db if not exist
```
mysql> SHOW DATABASES;
mysql> CREATE DATABASE runningInfoAnalysis_db;
mysql> USE runningInfoAnalysis_db;
mysql> SHOW TABLES;
mysql> CREATE TABLE RUNNING_ANALYSIS (userId BIGINT(20) AUTO_INCREMENT, runningId VARCHAR(50), latitude DOUBLE, longitude DOUBLE, runningDistance DOUBLE, totalRunningTime DOUBLE, timestamp TIMESTAMP, healthWarningLevel INT, heartRate VARCHAR(10), username VARCHAR(30), address VARCHAR(50), PRIMARY KEY(userId));
mysql> DESC RUNNING_ANALYSIS;
mysql> EXIT;
```
### 3. Build and run Spring Boot application
Change directory to /your_path/CS504-running-information-analysis before running the following commands.
```
mvn clean install
java -jar ./target/running-information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar
```
## API Overview
> Check API References for detailed information

| Method | URL | Description | 
|--------|--------|-------------|
| POST | /running | upload a list of runningInfo | 
| DELETE | running/purge | delete all runningInfo | 
| DELETE | running/delete/{runningId} | delete one runningInfo by runningId |
| GET | /running | get all runningInfo with pagination and sort by healthWarningLevel | 
| GET | /running/?page=0&size=2 | get all runningInfo with two data each page and sort by healthWarningLevel | 
| GET | /running/{userId} | get one runningInfo by userId | 
| GET | /running/runningId/{runningId} | get one runningInfo by runningId | 
