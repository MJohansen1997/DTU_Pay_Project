FROM adoptopenjdk:11-jre-hotspot
COPY target/lib /usr/src/lib
COPY target/TokenService-1.0-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src/
CMD java -Xmx32m -jar TokenService-1.0-SNAPSHOT.jar
