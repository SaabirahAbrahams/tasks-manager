FROM openjdk:21
LABEL maintainers="tasksmanager.net"
ADD target/tasks-manager-0.0.1-SNAPSHOT.jar tasksmanager-docker.jar
ENTRYPOINT ["java", "-jar", "tasksmanager-docker.jar"]