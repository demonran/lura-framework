#!/usr/bin/env bash

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd $workdir/lura-zipkin-server || exit

docker-compose down





