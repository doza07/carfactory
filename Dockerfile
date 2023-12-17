FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY ./target/carfactory.jar app.jar
CMD ["java", "-jar", "app.jar"]