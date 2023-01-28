FROM maven
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:9
WORKDIR /app
COPY --from=build /app/target/bioMedical.jar /app/
EXPOSE 9090
CMD ["java","-jar","bioMedical.jar"]
