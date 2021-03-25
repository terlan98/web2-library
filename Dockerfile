FROM openjdk:11
EXPOSE 8080
COPY target/library-0.0.1-SNAPSHOT.jar library-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/library.jar"]