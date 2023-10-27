
FROM openjdk:17

RUN mkdir /app

COPY target/Dental_Surgeries_AppSystem_WebApi-0.0.1-SNAPSHOT.jar /app

WORKDIR /app

CMD ["java", "-jar", "./Dental_Surgeries_AppSystem_WebApi-0.0.1-SNAPSHOT.jar"]
