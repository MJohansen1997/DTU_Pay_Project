FROM adoptopenjdk:11-jre-hotspot
COPY target/lib /usr/src/lib
COPY target/PaymentService-1.0-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src/
CMD java -Xmx32m -jar PaymentService-1.0-SNAPSHOT.jar
