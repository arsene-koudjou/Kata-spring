FROM openjdk:17
EXPOSE 8080
ADD target/Kata-Spring-Docker.jar Kata-Spring-Docker.jar
RUN mkdir /inputs
COPY inputs/input.txt /inputs/input.txt
ENTRYPOINT ["java","-jar","Kata-Spring-Docker.jar"]