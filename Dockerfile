FROM azul/zulu-openjdk-alpine:17.0.6
COPY target/crudpessoa-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]