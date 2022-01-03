#!/usr/bin/env bash

projectDir=$(dirname "$PWD")
cd $projectDir


./gradlew clean :lura-framework-core:publishToMavenLocal
./gradlew clean :lura-framework-spring-boot-starter:publishToMavenLocal
./gradlew clean :lura-framework-commons:publishToMavenLocal
./gradlew clean :lura-framework-security-core:publishToMavenLocal
