FROM amazoncorretto:17.0.7-alpine

ENV JAVA_TOOL_OPTIONS "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n"
EXPOSE 5005

RUN apk add --no-cache postgresql-client

COPY target/institutions-0.0.1-SNAPSHOT.jar institutions-0.0.1-SNAPSHOT.jar

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]