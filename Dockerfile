FROM openjdk:11.0.11-jdk-slim

MAINTAINER mintonzhang@163.com

ENV TZ=Asia/Shanghai

#将这个名字改为具体的项目名
ENV SERVER_NAME=chatgpt-proxy-server

RUN echo -e "${TZ}" > /etc/timezone && ln -sf /usr/share/zoneinfo/${TZ} /etc/localtime

RUN mkdir -p /server/config/null

WORKDIR /server

VOLUME /server


COPY ./target/${SERVER_NAME}.jar ./

CMD sleep 5;java -Dfile.encoding=utf-8 \
 -server \
 -Xmx${HEAP_SIZE:-256}m \
 -Xms${HEAP_SIZE:-256}m \
 -jar ${SERVER_NAME}.jar \
  ${SPRING_OPT}\
