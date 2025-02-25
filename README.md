Java - 17.x

Spring Boot - 2.7.12

REST API for a Angular application

Run in container:

build your main artifact to : /home/out/artifacts/app_jar/taskTracker-REST-api.jar

run your docker build command from /home/

docker build -t task-tracker-api .

docker run -p 8082:8082 task-tracker-api

- features
  - custom exception handling
  - performance monitoring
  - docker support
  - Structured Api Response
  - concurrency support
  - thread pool optimization


- UPCOMING ROADMAPS
  - Netflix Eureka Support
  - DTO projection support
  - Entity Graph support
  - oAUTH2 SUPPORT VIA AUTHORIZATION AND RESOURCE SERVER SUPPORT
  - Open telemetry integration
  - Idempotent implementation


spring actuator URLs

http://localhost:8082/api/console/info

http://localhost:8082/api/console/health

http://localhost:8082/api/console/metrics


main CRUD end points:

localhost:8082/api/users/

localhost:8082/api/tasks/

localhost:8082/api/task-rewards/


on application start up. get users end point should produce

--------------------------------------------------------------

Building the application in a docker container
cd to the app module
run from the app module to deploy REST API to a docker container
  docker build -t task-tracker-api .
  docker run -p 4444:8082 task-tracker-api


