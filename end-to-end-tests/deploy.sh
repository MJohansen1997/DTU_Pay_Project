#!/bin/bash
set -e
docker image prune -f
docker pull rabbitMq:latest
docker-compose up -d rabbitMq
sleep 20
docker-compose up -d account-service-dtu payment-service-dtu report-service-dtu token-service-dtu dtu-facade
sudo docker images
