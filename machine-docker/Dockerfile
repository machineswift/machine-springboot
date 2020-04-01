FROM openjdk:8-jre
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/app.jar"]
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/app.jar