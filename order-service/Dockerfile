# Étape 1 : Construire l'application principale
FROM maven:3.8.5-openjdk-17 AS deps

WORKDIR /opt/app
COPY order-service/pom.xml order-service/pom.xml
COPY shared-service/pom.xml shared-service/pom.xml
COPY auth-service/pom.xml auth-service/pom.xml
COPY inventory-service/pom.xml inventory-service/pom.xml
COPY payment-service/pom.xml payment-service/pom.xml
COPY pom.xml .

RUN mvn -B -e -C dependency:go-offline -DexcludeArtifactIds=auth-service,inventory-service,payment-service

# Copier ensuite le code source et compiler
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY order-service/src /opt/app/order-service/src
COPY shared-service/src /opt/app/shared-service/src

RUN mvn -B -e clean package -DskipTests=true -pl order-service -am

# Étape 2 : Image finale pour exécuter l'application
FROM openjdk:17
WORKDIR /app

# Copier uniquement le JAR final pour une image plus légère
COPY --from=builder /opt/app/order-service/target/order-service-*.jar order-service.jar

# Exposer le port de l'application
EXPOSE ${PORT}

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "/app/order-service.jar"]
