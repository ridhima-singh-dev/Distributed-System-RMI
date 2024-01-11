FROM openjdk:18
COPY target/client-0.0.1-allinone.jar /client-0.0.1-allinone.jar
CMD sleep 5 && /usr/bin/java -cp /client-0.0.1-allinone.jar client.Main ${SERVER_HOST}