# Stage: 1
FROM gradle:jdk8 AS builder

WORKDIR /app

# 将源代码放入后编译、打包
ADD . /app
RUN echo "ossrhUsername=user\nossrhPassword=passwd" >>gradle.properties
# use BuildKit
RUN --mount=type=cache,mode=755,target=/root/.gradle gradle :lura-framework-auth-server:build

# Stage: 2
FROM openjdk:8-jre

COPY --from=builder /app/lura-framework-auth-server/build/libs/lura-framework-auth-server.jar /app.jar

ARG profile

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${profile}", "/app.jar"]
