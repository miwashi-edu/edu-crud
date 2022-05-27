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