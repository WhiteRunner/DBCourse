FROM java:8
VOLUME /tmp
ADD sp-server-0.0.1.jar dbcourse.jar
ENV LANG C.UTF-8
ENV LANGUAGE zh_CN.UTF-8
ENV LC_ALL C.UTF-8
ENV TZ Asia/Shanghai
EXPOSE 8099
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dbcourse.jar"]
