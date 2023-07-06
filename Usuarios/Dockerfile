FROM openjdk:18
EXPOSE  8070
RUN apt install maven -y
RUN mvn clean install

ENTRYPOINT ["java","-jar","/target/api.jar"]
