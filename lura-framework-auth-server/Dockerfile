# Stage: 1
FROM gradle:jdk8 AS builder

WORKDIR /app

# 将源代码放入后编译、打包
ADD . /app
RUN echo "ossrhUsername=user\nossrhPassword=passwd" >>gradle.properties
# use BuildKit
RUN --mount=type=cache,target=/root/.gradle,id=my_gradle_module,sharing=locked gradle :lura-framework-auth-server:build

# Stage: 2
FROM openjdk:8-jre
COPY --from=builder /app/lura-framework-auth-server/build/libs/lura-framework-auth-server.jar /app.jar

ENV profile=dev

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${profile}", "/app.jar"]
