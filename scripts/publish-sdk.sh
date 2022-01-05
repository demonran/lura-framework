#!/usr/bin/env bash

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd ${workdir}

./gradlew clean :lura-framework-core:publishToMavenLocal
./gradlew clean :lura-framework-spring-boot-starter:publishToMavenLocal
./gradlew clean :lura-framework-commons:publishToMavenLocal
./gradlew clean :lura-framework-security-core:publishToMavenLocal
