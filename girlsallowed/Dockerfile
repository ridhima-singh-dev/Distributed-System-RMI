FROM openjdk:18
COPY target/girlsallowed-0.0.1-allinone.jar /girlsallowed-0.0.1-allinone.jar
CMD sleep 2 && /usr/bin/java -cp /girlsallowed-0.0.1-allinone.jar service.girlsallowed.Main $SERVER_HOST