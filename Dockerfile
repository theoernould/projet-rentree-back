# Utilisation de l'image Docker officielle de Maven, basée sur OpenJDK
FROM maven:3.8.4-openjdk-17

# Create app directory
WORKDIR /usr/src/app

# Copiez le fichier pom.xml (et éventuellement d'autres fichiers de configuration Maven)
COPY pom.xml ./
COPY run.sh ./

# Copiez tout le contenu du projet Maven dans le conteneur
COPY src ./src

# Exécutez clean et compile en utilisant Maven et Java 17
RUN mvn clean install

RUN chmod +x /usr/src/app/run.sh

EXPOSE 8000
# Commande par défaut pour exécuter l'application (vous pouvez la personnaliser)
CMD ["/usr/src/app/run.sh"]