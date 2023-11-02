FROM openjdk:11

WORKDIR /opt/www/dth/server/digitaltwin-healthcare-server

COPY ./target/*.jar app.jar

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]
