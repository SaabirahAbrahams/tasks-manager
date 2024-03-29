# Tasks-Manager 

## Request
For Users: 
* create User "/api/users/id"
* get User by id "/api/users/id"
* get all Users "/api/users"
* update User "/api/users/id"

For Tasks:
* create Tasks for User "/api/users/id/tasks"
## Docker 
To create docker image:
```
$ docker build -t tasksmanager-docker:latest .
```
To run docker image:
```
$ docker run -p 8081:8080 taskmanager-docker
```

