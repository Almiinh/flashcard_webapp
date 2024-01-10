FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
COPY target/flashcard-spring-boot-0.0.1.jar flashcardwebapp.jar

# Make port configurable
ARG PORT=8080
ENV PORT ${PORT}
EXPOSE ${PORT}

# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar -Dserver.port=${PORT} flashcardwebapp.jar
