## Prérequis

- Java 17 ou plus (JDK ou JRE)
- Spring boot 3.2
- Maven (pour build initial)


# Kata Spring Project (with OpenAPI 3)
- Après le lancement de l'application on peut tester le rest controlleur avec le swagger sur l'url
http://localhost:8080/swagger-ui/index.html

en spécifiant l'entré en paramètre on aura la chaine de retour adaptée.

- pour tester avec le fichier input.txt , il est à ajouter
   dans le dossier inputs à la racine du projet; 
   ensuite après avoir exécuté le projet , les logs spécifieront  quand le job s'est terminé.
    sachant que le job(cron)  a été  configuré pour s'exécuter toutes les 5 secondes.
    à la fin de l'exécution , à l'arret du programme en local un fichier output.txt est généré dans
    un dossier /dist à la racine du projet avec les chaines équivalentes des entiers décrits dans le fichier input fourni en entrée


