#!/usr/bin/env bash

workdir=$(dirname $(cd $(dirname "$0"); pwd))
echo "current dir is: $workdir"

$workdir/gradlew clean :lura-framework-core:publish
$workdir/gradlew clean :lura-framework-spring-boot-starter:publish
$workdir/gradlew clean :lura-framework-commons:publish
$workdir/gradlew clean :lura-framework-security-core:publish