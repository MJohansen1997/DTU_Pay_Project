FROM adoptopenjdk:11-jre-hotspot
COPY target/lib /usr/src/lib
COPY target/ReportService-1.0-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src/
CMD java -Xmx32m -jar ReportService-1.0-SNAPSHOT.jar
