# TFSD TODO 7 : Run your software anywhere with Docker

### 1. Analyze and explain your understanding of the following folder and files (Dockerfile and docker-entrypoint.sh script): https://github.com/eclipse/mosquitto/tree/master/docker/2.0***

#### `Dockerfile`

Ce Dockerfile est utilisé pour construire une image Docker de l'Eclipse Mosquitto MQTT Broker, un broker MQTT léger et efficace.

* `FROM alpine:3.18` indique que l'image est basée sur Alpine Linux version 3.18.
* `LABEL` est utilisé pour ajouter des métadonnées à l'image
* `ENV` définit plusieurs variables d'environnement utilisées pour télécharger et vérifier les versions spécifiques de Mosquitto et libwebsockets
* `RUN` va exécuter des commandes linux pour effectuer certaines opérations d’installation.
* `VOLUME` indique les dossiers de données et de logs qui peuvent être montés à l'extérieur du conteneur.
* `EXPOSE 1883` expose le port 1883, utilisé par MQTT.
* `COPY docker-entrypoint.sh /` copie le script docker-entrypoint.sh dans l'image.
* `ENTRYPOINT` définit le script d'entrée
* `CMD` définie la commande par défaut pour exécuter Mosquitto.

#### `docker-entrypoint.sh`
Ce script est exécuté au démarrage du conteneur Docker.

* Il vérifie si le script est exécuté en tant que root (utilisateur avec ID 0). Si oui, il change la propriété du dossier /mosquitto pour l'utilisateur mosquitto.
* exec "$@" exécute la commande passée au conteneur. Dans ce cas, c'est la commande définie dans CMD du Dockerfile, qui démarre le broker Mosquitto.

### 2. Dockerize your Spring Boot application and list the steps I need to follow to run this locally. The port where the application is deployed should be configurable as an environment variable

To run the docker container, execute the following commands:
```
cd spring-boot-jpa/
mvn install
docker build -t flashcard_backend .
docker run -p 8080:8080 flashcard_backend
```

We can change the PORT environment variable if needed with the -e option and run this instead. (Default port is `8080`)
```
docker run -p 9090:9090 -e PORT=9090 flashcard_backend
```

### 3. Dockerize your vue.js application and list the steps I need to follow to run this locally. The port where the application is deployed should be configurable as an environment variable

To run the docker container, execute the following commands:
```
cd spring-boot-jpa/
docker build -t flashcard_frontend .
docker run -p 8081:80 flashcard_frontend
```

We can change the PORT environment variable if needed with the -e option and run this instead. (Default port is `8081` with nginx listening at port `80`)
```
docker run -p 6060 -e PORT=6060 flashcard_frontend
```

### 4. Create a multi-container Docker application with Docker Compose to deploy your Web application locally (back + front) and list the steps I need to follow to run this. The ports should be configurable as environment variables.

We skipped this step due to lack of time