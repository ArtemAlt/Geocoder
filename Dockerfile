FROM bellsoft/liberica-openjdk-alpine:11.0.12-7 as build
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN ./mvnw clean package


FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
MAINTAINER Artem Altunin <artem.altunin.vit@gmail.com>
RUN mkdir /app
RUN mkdir /app/log
COPY --from=build /app/target/Geocoder-0.0.1-SNAPSHOT.jar  /app/Geocoder.jar
WORKDIR /app
ENTRYPOINT ["sh", "-c", "java --add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.management/sun.management=ALL-UNNAMED --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED ${JAVA_TOOL_OPTIONS} -jar Geocoder.jar"]


