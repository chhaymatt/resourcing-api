# Setup draft

1. Git clone
2. Set up src/main/resources/application.properties
3. Create resourcing database in mysql workbench `create database resourcing;`
4. Run src/main/java/com/matthewchhay/resourcingapi/ResourcingApiApplication.java
5. In Postman, GET localhost:9876/jobs/test and see if it returns hello world


# Issues
- Unable to GET all jobs
- Unable to GET job by id

To do:
- Fix job domain
- Fix temp domain
- Create relationship between temps and jobs
