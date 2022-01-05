#!/usr/bin/env bash

./start-net.sh

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd $workdir/lura-nacos-server

docker-compose up -d





