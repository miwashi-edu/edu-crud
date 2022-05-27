# Test first development with database dependency

A database is a runtime dependency and requires a runtime-environment. 
A fairly good best-practice is not to tie up your development
environment to a runtime environment therefore we use the test fork of a java 
project to create an environment.

The code should then be deployable in any environment where 
```groovy
testImplementation 'org.testcontainers:testcontainers'
testImplementation 'org.testcontainers:junit-jupiter' //Allows jupiter acces to junit 4 rules
testImplementation 'org.testcontainers:mysql'
testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
```

@Container
private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

```java
System.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLInnoDBDialect");
System.setProperty("hibernate.connection.driver_class",mySQLContainer.getDriverClassName());
System.setProperty("hibernate.connection.url",mySQLContainer.getJdbcUrl());
System.setProperty("hibernate.connection.username",mySQLContainer.getUsername());
System.setProperty("hibernate.connection.password",mySQLContainer.getPassword());
System.setProperty("hibernate.show_sql","true");
System.setProperty("hibernate.hbm2ddl","update");
```
