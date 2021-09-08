FROM openjdk:11-jre


#Change timezone

RUN ln -sf /usr/share/zoneinfo/America/Brazil /etc/localtime
RUN echo "America/Sao_Paulo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 80


COPY /target/craft-beer-1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]