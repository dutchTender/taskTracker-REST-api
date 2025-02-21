FROM alpine:latest

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*
ENV API_KEY=mycats
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

RUN useradd --create-home appUser
USER appUser
ARG JAR_FILE=target/app.jar

COPY ./out/artifacts/taskTracker_REST_api_jar/taskTracker-REST-api.jar  app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app.jar"]

CMD ["/bin/bash"]
