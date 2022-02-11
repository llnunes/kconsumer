FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/consumer-0.0.1-SNAPSHOT.jar consumer.jar
ENTRYPOINT ["java","-jar","/consumer.jar"]