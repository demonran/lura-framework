#!/usr/bin/env bash

# 启动网络
./start-net.sh

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd $workdir/lura-sentinel-dashboard

docker-compose up -d





