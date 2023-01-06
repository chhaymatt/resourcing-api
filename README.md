# Setup draft

1. Git clone
2. Set up src/main/resources/application.properties
3. Create resourcing database in mysql workbench `create database resourcing;`
4. Run src/main/java/com/matthewchhay/resourcingapi/ResourcingApiApplication.java
5. In Postman, GET localhost:9876/jobs/test and see if it returns hello world

# To do:

-   Fix job domain
-   Fix temp domain
-   Create relationship between temps and jobs

# Endpoints

## Jobs

| Method | URL                           | Action                                                    | Created & works on Postman? | E2E Test? | Payload Example |
| ------ | ----------------------------- | --------------------------------------------------------- | --------------------------- | --------- | --------------- |
| GET    | jobs                          | Fetch all jobs                                            | ✅                          |           |                 |
| GET    | jobs/`{id}`                   | Fetch job by `{id}`                                       | ✅                          |           |                 |
| GET    | jobs?assigned=`{true\|false}` | Filter jobs by whether a job is assigned to a temp or not |                             |           |                 |
| POST   | jobs                          | Create new job                                            | ✅                          |           |                 |
| PATCH  | jobs/ `{id}`                  | Update job by `{id}`, e.g. assigning a temp to a job      |                             |           |                 |
| DELETE | jobs/`{id}`                   | Delete job by `{id}`                                      |                             |           |                 |
| DELETE | jobs                          | Delete all jobs                                           |                             |           |                 |

### Payload for creating a new job - POST /jobs

```
{
    "name": "Job name goes here",
    "startDate": "2023-01-01",
    "endDate": "2023-01-04",
    "temp": 1
}
```

```
{
    "name": "Job name goes here",
    "startDate": "2023-01-01",
    "endDate": "2023-01-04",
}
```

# Roadblocks in my development

## Problem 1 - PATCH is not supported

Unable to patch a job and console logs/Postman says the method is not supported.

### Symptom

```
    "message": "Method 'PATCH' is not supported.",
    "status": 405,
    "error": "Method Not Allowed"
```

### How did I solve it?

1. Confirmed the job exists by GET /jobs/1
2. Searched online and found out how to enable debug in application.properties `logging.level.org.springframework.web=DEBUG`
3. Saw a post online and someone mentioned about them not passing in the {id}
4. Rechecked the JobController and realised I doubled up on the URL, it was going to `/jobs/jobs/1` instead of `/jobs/1`
5. Removed additional /jobs in the @PatchMapping
