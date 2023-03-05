FROM openjdk:17

ADD target/testcase-0.0.1-SNAPSHOT.jar testcase-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "testcase-0.0.1-SNAPSHOT.jar"]