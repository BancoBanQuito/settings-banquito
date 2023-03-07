FROM eclipse-temurin:17-jdk-focal

COPY settings/target/settings-0.0.1-SNAPSHOT.jar settings-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/settings-0.0.1-SNAPSHOT.jar"]
EXPOSE 80
