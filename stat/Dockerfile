FROM eclipse-temurin:21-jre-jammy
VOLUME /tmp
ARG JAR_FILE=stat-server/target/*.jar
COPY ${JAR_FILE} stat_svc.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /stat_svc.jar"]