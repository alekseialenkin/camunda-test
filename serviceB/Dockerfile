ARG JRE_IMAGE=amazoncorretto:17-alpine
ARG GRADLE_IMAGE=gradle:8.5-jdk17-alpine

FROM ${GRADLE_IMAGE} AS build

WORKDIR /serviceB

COPY build.gradle settings.gradle ./

RUN gradle dependencies --no-daemon

COPY . .

RUN gradle clean build -x test --no-daemon

FROM ${JRE_IMAGE}

COPY --from=build /serviceB/build/libs/serviceB-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8020

RUN rm -rf /etc/logrotate.d/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker","app.jar"]
