FROM openjdk:11-jre

#Change timezone

RUN ln -sf /usr/share/zoneinfo/America/Brazil /etc/localtime
RUN echo "America/Sao_Paulo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ARG app_version

COPY target/craft-beer-${app_version}.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]