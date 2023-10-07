FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV DATABASE_URL jdbc:postgresql://dpg-ckdd74msmu8c7389dmsg-a.oregon-postgres.render.com:5432/messenger_t316
ENV DATABASE_USER bestuser
ENV DATABASE_PASSWORD aS7Zd1rLVG6B1CoSP6dMx8l5vfM8a0ND
EXPOSE 8080
