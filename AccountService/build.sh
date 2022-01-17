#!/bin/bash
set -e
mvn clean package
docker-compose build account-service-dtu  dtu-facade
