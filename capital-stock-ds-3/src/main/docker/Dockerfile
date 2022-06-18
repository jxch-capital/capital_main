FROM openjdk:17-jdk-slim
VOLUME /capital
ADD capital-stock-ds-3.jar app.jar
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

ENTRYPOINT ["java", "-jar", "/app.jar", "--env=bate"]