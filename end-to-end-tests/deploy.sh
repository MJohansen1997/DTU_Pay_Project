#!/bin/bash
set -e
docker image prune -f
docker-compose up -d rabbitmq
sleep 5
docker-compose up -d account-service-dtu payment-service-dtu report-service-dtu token-service-dtu dtu-facade-17
sudo docker images
