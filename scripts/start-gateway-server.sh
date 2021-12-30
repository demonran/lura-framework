#!/usr/bin/env bash

./start-net.sh

workdir=$(dirname "$PWD")

cd $workdir

./gradlew :lura-gateway-server:bootRun
