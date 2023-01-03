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

| Method | URL                            | Action                                                    | Created & works on Postman? | E2E Test? |
| ------ | ------------------------------ | --------------------------------------------------------- | --------------------------- | --------- |
| GET    | jobs                           | Fetch all jobs                                            | Not working in Postman 404  |           |
| GET    | jobs/`{id}`                    | Fetch job by `{id}`                                       | ✅                          |           |
| GET    | /jobs?assigned=`{true\|false}` | Filter jobs by whether a job is assigned to a temp or not |                             |           |
| POST   | jobs                           | Create new job                                            | Not working in Postman 404  |           |
| PATCH  | jobs/ `{id}`                   | Update job by `{id}`, e.g. assigning a temp to a job      |                             |           |
| DELETE | jobs/`{id}`                    | Delete job by `{id}`                                      |                             |           |
| DELETE | jobs                           | Delete all jobs                                           |                             |           |
