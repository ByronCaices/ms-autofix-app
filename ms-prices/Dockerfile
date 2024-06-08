FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-prices.jar
ENTRYPOINT ["java","-jar","/ms-prices.jar"]