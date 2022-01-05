#!/usr/bin/env bash

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd $workdir/devops

cd mysql

docker-compose up -d

cd $workdir/redis

docker-compose up -d

