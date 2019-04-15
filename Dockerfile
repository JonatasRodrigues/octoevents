FROM java:8
EXPOSE 8080

ADD /target/octo-events-1.0-SNAPSHOT.jar octo-events-1.0-SNAPSHOT.jar
CMD ["java","-jar","octo-events-1.0-SNAPSHOT.jar"]