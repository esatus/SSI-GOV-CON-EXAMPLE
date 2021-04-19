FROM openjdk:11-jre-slim
COPY /app/target/ssi-gov-con-example-0.0.1-SNAPSHOT-exec.jar /app/ssi-gov-con-example.jar
RUN chmod 777 /app/ssi-gov-con-example.jar
WORKDIR /app

EXPOSE 80

ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "/app/ssi-gov-con-example.jar"]
CMD []