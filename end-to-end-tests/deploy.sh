#!/bin/bash
set -e
docker image prune -f
docker-compose up -d rabbitmq
sleep 5
docker-compose up -d report-service-dtu token-service-dtu dtu-facade-17 #account-service-dtu payment-service-dtu
