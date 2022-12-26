# Setup draft

1. Git clone
2. Set up src/main/resources/application.properties
3. Create resourcing database in mysql
4. Run src/main/java/com/matthewchhay/resourcingapi/ResourcingApiApplication.java


# Issues during development
Replace @SpringBootApplication with
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })