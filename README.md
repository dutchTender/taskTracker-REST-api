REST API for a Angular application

- UPCOMING ROADMAPS
  - ADD oAUTH2 SUPPORT VIA AUTHORIZATION AND RESOURCE SERVER SUPPORT

spring actuator URLs

http://localhost:8082/api/console/info

http://localhost:8082/api/console/health

http://localhost:8082/api/console/metrics


main CRUD end points:

localhost:8082/api/users/

localhost:8082/api/tasks/

localhost:8082/api/rewards/

localhost:8082/api/taskRewards/


on application start up. get users end point should produce

[
{
"name": "dutchTender",
"user_type": "admin",
"email": "lzhang422@gmail.com",
"tasks": [
{
"name": "basketball practice after school",
"taskTime": "every other tuesday",
"taskDescription": "drop of achiles at basketball practice",
"taskRewards": [
{
"name": "1 sneakers bar",
"id": 999
}
],
"id": 10002
},
{
"name": "pick up cat food from amazon",
"taskTime": "next week",
"taskDescription": "tikicat + instinct patte",
"taskRewards": [
{
"name": "1 bitcoin",
"id": 1001
}
],
"id": 10001
}
],
"id": 1001
}
