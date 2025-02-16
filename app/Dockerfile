FROM ubuntu:latest

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

ARG JAR_FILE=target/app.jar

COPY ./target/app.jar  app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


CMD ["/bin/bash"]
