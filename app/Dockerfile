FROM ubuntu:latest

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

RUN useradd --create-home appUser
USER appUser
ARG JAR_FILE=target/app.jar

COPY ./target/app.jar  app.jar
COPY ./lib /lib

EXPOSE 8082

ENV API_KEY=mycats
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"





ENTRYPOINT ["java", "-jar", "/app.jar", "-classpath", "/lib"]


CMD ["/bin/bash"]
