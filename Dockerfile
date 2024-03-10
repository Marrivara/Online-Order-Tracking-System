FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring-boot-docker.jar
ADD ${JAR_FILE} spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]