FROM openjdk:20-jdk-slim

WORKDIR /app

# Baixe o wrapper do Gradle
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://services.gradle.org/distributions/gradle-8.8-bin.zip \
    && unzip gradle-8.8-bin.zip -d /opt \
    && rm gradle-8.8-bin.zip \
    && ln -s /opt/gradle-8.8/bin/gradle /usr/bin/gradle

COPY build.gradle.kts settings.gradle.kts gradle.properties ./

COPY src ./src

# Use o comando gradle em vez de ./gradlew
RUN gradle build --no-daemon

EXPOSE 8080

CMD ["gradle", "bootRun"]