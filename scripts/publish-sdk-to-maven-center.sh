#!/usr/bin/env bash

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

cd ${workdir}

./gradlew clean :lura-framework-core:publish
./gradlew clean :lura-framework-spring-boot-starter:publish
./gradlew clean :lura-framework-commons:publish
./gradlew clean :lura-framework-security-core:publish
