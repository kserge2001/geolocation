FROM openjdk:8
ADD target/*.jar biom.jar
EXPOSE 8081
ENTRYPOINT ["-java","-jar","biom.jar"]
