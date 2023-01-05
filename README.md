# Setup draft

1. Git clone
2. Set up src/main/resources/application.properties
3. Create resourcing database in mysql workbench `create database resourcing;`
4. Run src/main/java/com/matthewchhay/resourcingapi/ResourcingApiApplication.java
5. In Postman, GET localhost:9876/jobs/test and see if it returns hello world

# Current roadblock:
- PATCH a job, says it is not supported

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
