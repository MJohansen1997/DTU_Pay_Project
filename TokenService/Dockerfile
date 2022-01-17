FROM adoptopenjdk:11-jre-hotspot
COPY target/lib /usr/src/lib
COPY target/token-service-dtu-1.0.0.jar /usr/src/
WORKDIR /usr/src/
CMD java -Xmx32m -jar token-service-dtu-1.0.0.jar
