# Resourcing API <!-- omit in toc -->

> Matthew Chhay's Resourcing API using the Spring Boot framework in Java and MySQL.

## Preview <!-- omit in toc -->

![Preview of Matthew Chhay's Resourcing API](https://i.imgur.com/3Fl9JNU.png)

## Table of Contents <!-- omit in toc -->

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Tools Used](#tools-used)
- [Project Status](#project-status)
- [MVP](#mvp)
	- [Jobs](#jobs)
	- [Temps](#temps)
	- [Payloads](#payloads)
	- [Assumptions](#assumptions)
- [Going beyond the MVP](#going-beyond-the-mvp)
- [Setup](#setup)
- [Requirements](#requirements)
- [For launching locally and further development](#for-launching-locally-and-further-development)
- [Issues discovered during development](#issues-discovered-during-development)
	- [Problem 1 - PATCH is not supported](#problem-1---patch-is-not-supported)
	- [Problem 2 - PATCH is not updating](#problem-2---patch-is-not-updating)
	- [Problem 3 - Infinite recursion between job and temp relationship](#problem-3---infinite-recursion-between-job-and-temp-relationship)
- [Room for Improvement](#room-for-improvement)

## Introduction

An API where consumers can create, update or fetch jobs and temporary workers (temps) and temps can be assigned to jobs using Spring Java and MySQL.

## Features

-   Fetch all jobs or temps
-   Create a job or a temp
-   Fetch all jobs assigned to a temp or not
-   A relationship with jobs and temps

## Technologies Used

-   Java 17 and Maven Project
-   Spring Boot 3.0.1
    -   Spring Web - build web, including RESTful, applications using Spring MVC
    -   Spring Boot DevTools - provides fast application restarts, LiveReload, and configurations for enhanced development experience
    -   Spring Data JPA - Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate
    -   MySQL Driver - mySQL JDBC and R2DBC driver
    -   Validation - bean validation with hibernate validator
-   MySQL Workbench
-   Postman

## Tools Used

-   Spring Initializr - create a Spring app
-   Postman - test endpoints and methods
-   Spring Boot Dashboard VS Code Extension

## Project Status

Project is ongoing

## MVP

### Jobs

| Method | URL                           | Action                                                    | Created & works on Postman? |
| ------ | ----------------------------- | --------------------------------------------------------- | --------------------------- |
| GET    | jobs                          | Fetch all jobs                                            | ✓                           |
| GET    | jobs/`{id}`                   | Fetch job by `{id}`                                       | ✓                           |
| GET    | jobs?assigned=`{true\|false}` | Filter jobs by whether a job is assigned to a temp or not | ✓                           |
| POST   | jobs                          | Create new job                                            | ✓                           |
| PATCH  | jobs/ `{id}`                  | Update job by `{id}`, e.g. assigning a temp to a job      | ✓                           |

### Temps

| Method | URL                   | Action                                                                | Created & works on Postman? |
| ------ | --------------------- | --------------------------------------------------------------------- | --------------------------- |
| GET    | temps                 | Fetch all temps                                                       | ✓                           |
| GET    | temps/`{id}`          | Fetch temp by `{id}`                                                  | ✓                           |
| GET    | temps/tree            | Fetch whole tree of temps                                             | ✗                           |
| GET    | temps?jobId=`{jobId}` | Fetch temps that are available for a job based on the jobs date range | ✗                           |
| POST   | temps                 | Create new temp                                                       | ✓                           |
| PATCH  | temps                 | Update temp by `{id}`, e.g. new name                                  | ✓                           |

### Payloads

```json
// GET /jobs/{id}
{
	id: ...,
	name: ...,
	startDate: ...,
	endDate: ...,
	temp: {
		id: ...,
		firstName: ...,
		lastName: ...,
	} // temp can also be null if a temp hasn't been assigned to the job
}

// GET /temps/{id}
{
	id: ...,
	firstName: ...,
	lastName: ...,
	jobs: [{
		id: ...,
		name: ...,
		startDate: ...,
		endDate: ...,
	}, ...] // can be empty if temp hasn't been assigned to jobs
}
```

### Assumptions

-   [ ] Temps can only have one job at a time (can’t be doing 2 jobs on the same date)
-   [x] Temps can have many jobs, and job can have 1 temp assigned
-   [x] Should be able to assign existing temps to jobs via `POST /jobs` & `PATCH /jobs/{id}`
-   [x] Must use a relational database

## Going beyond the MVP

-   [ ] Temps should be able to manage other temps (will require an additional field)
-   [ ] When you request a temp record it should display the reports of that temp
-   [ ] Should be represented in the database as a nested set
-   [ ] `GET /temps/tree` - should display the whole tree of temps

## Setup

## Requirements

-   Java 17
-   MySQL Server

## For launching locally and further development

1. Git clone this repo `git@github.com:chhaymatt/resourcing-api.git`
2. Modify `src/main/resources/application.properties` file with your device's credentials (e.g. different username or password)
3. Create resourcing database in mysql workbench `create database resourcing;` on `localhost 3306`
4. Run `./mvnw spring-boot:run` or from file `src/main/java/com/matthewchhay/resourcingapi/ResourcingApiApplication.java`
5. Confirm API returns "Hello World from Job' by using Postman and GET `localhost:9876/jobs/test`

## Issues discovered during development

### Problem 1 - PATCH is not supported

Unable to patch a job and console logs/Postman state the method is not supported.

**How did I solve it?**

1. Confirmed the job exists by `GET /jobs/1`
2. Searched online and discovered how to enable debug in application.properties `logging.level.root=DEBUG`
3. Saw a post online and someone mentioned about them not passing in the {id}
4. Checked the JobController and realised I doubled up on the URL, it was going to `/jobs/jobs/1` instead of `/jobs/1`
5. Removed additional `/jobs` in the `@PatchMapping` and error no longer appears

### Problem 2 - PATCH is not updating

-   Patching a job returns the original job instead of the patched payload

**How did I solve it?**

1. Enabled debug mode, noticed JobUpdateDTO and compared file with JobCreateDTO
2. Realised missing JobUpdateDTO constructor
3. Added JobUpdateDTO constructor and it is working, considering combining both DTOs into one file

### Problem 3 - Infinite recursion between job and temp relationship

API stops working when using a relational database

**How did I solve it?**

1.  Attempt 1: Adding `@JsonManagedReference` and `@JsonBackReference` to the entities with the relational field stops infinite recursion because jobs references temp and temp also references jobs, however the entity with @JsonBackReference will not appear in the payload
2.  Attempt 2: Adding `@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")` to both entities allows both fields to appear but not ideal because getting all jobs will show all temps and jobs which is not neat
3.  Attempt 3: Replaced `@JoinColumn(name = "temp")` with `@JsonIgnoreProperties(value = { "jobs" })` on the job entity and added `@JsonIgnoreProperties(value = { "temp" })` to the temp entity. Issue resolved adopting this annotation/decorator.

## Room for Improvement

-   [ ] Create Factory Seeder for jobs and temps
-   [ ] Create E2E tests
-   [ ] Create Github Action to run tests on every commit
