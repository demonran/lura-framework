#!/usr/bin/env bash

# 启动网络
./start-net.sh

workdir=$(dirname "$PWD")

cd $workdir/lura-zipkin-server

docker-compose up -d





