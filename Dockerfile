FROM openjdk:17
EXPOSE 8080
ADD target/hateos-0.0.1-SNAPSHOT.jar hateos-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/hateos-0.0.1-SNAPSHOT.jar"]