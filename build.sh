#!/bin/bash
set -e

# Build and install the libraries
# abstracting away from using the
# RabbitMq message queue
pushd messaging-utilities-3.2
./build.sh
popd

pushd BankService
./build.sh
popd

# Build the services
pushd account-service-dtu
./build.sh
popd

pushd payment-service-dtu
./build.sh
popd

pushd report-service-dtu
./build.sh
popd

pushd token-service-dtu
./build.sh
popd 
