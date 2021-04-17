FROM java:8-jre
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD target/userservice-1.0.jar /usr/src/app/
ENTRYPOINT ["java", "-DMYSQL_URL=jdbc:mysql://mysql:3306/cplayerdb", "-DMYSQL_USER=cp_user", "-DMYSQL_PASSWORD=cp_password","-jar","userservice-1.0.jar"]
