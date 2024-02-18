FROM amazoncorretto:17.0.7-alpine

COPY target/institutions-0.0.1-SNAPSHOT.jar institutions-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "institutions-0.0.1-SNAPSHOT.jar" ]
