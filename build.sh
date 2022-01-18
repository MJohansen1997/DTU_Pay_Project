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
pushd AccountService
./build.sh
popd

pushd PaymentService
./build.sh
popd

pushd ReportServices
./build.sh
popd

pushd TokenService
./build.sh
popd 
