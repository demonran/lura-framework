#!/usr/bin/env bash

workDir=$(cd $(dirname "$0"); pwd)

# 启动nacos server
.$workDir/start-nacos-server.sh
#启动zipkin server
.$workDir/start-zipkin-server.sh
#启动gateway
.$workDir/start-gateway-server.sh
#启动devops
.$workDir/start-devops.sh
#启动plumelog server
.$workDir/start-plumelog-server.sh
