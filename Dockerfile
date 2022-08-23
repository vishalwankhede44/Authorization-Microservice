FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9000
ADD target/*jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]