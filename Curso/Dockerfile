# Etapa 1: Compilación inyectando el driver de PostgreSQL automáticamente
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Este comando inyecta la dependencia de Postgres al vuelo sin romper tu pom.xml local
RUN mvn dependency:get -Dartifact=org.postgresql:postgresql:42.7.2 && \
    mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución estable
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]