FROM maven:3.9.9-eclipse-temurin-11 AS builder
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src
RUN mvn -B -DskipTests package

FROM tomcat:9.0-jdk11-temurin
WORKDIR /usr/local/tomcat

COPY --from=builder /workspace/target/pr07-project.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
