## Prérequis pour un démarrage en local sans docker

- Java 17 ou plus (JDK ou JRE)
- Spring boot 3.2
- Maven (pour build initial)


# Kata Spring Project (with OpenAPI 3)
docker hub: https://hub.docker.com/repository/docker/arsene341/kata-spring-image-with-file/general
- lancement de l application à partir du tag V2 de l'image docker avec la commande
  docker run -p 8080:8080 arsene341/kata-spring-image-with-file:v2

- Après le lancement de l'application on peut tester le rest controlleur avec le swagger sur l'url
http://localhost:8080/swagger-ui/index.html

en spécifiant l'entré en paramètre on aura la chaine de retour adaptée.

