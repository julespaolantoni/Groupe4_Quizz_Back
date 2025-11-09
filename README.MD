~~# Your backend API

## Set up 
1. Copie-colle le `.env.sample` en `.env`
2. Remplis le fichier `.env` avec les credentials de ton choix
3. Fait un `docker compose up`
4. Rajoute le plugin : [env-file](https://plugins.jetbrains.com/plugin/7861-envfile)
5. Ajoute ton fichier `.env` comme ci-dessous (n'oublie pas d'activer les fichiers cachés)
<img src="img-readme/img.png">
<img src="img-readme/img_1.png">
<img src="img-readme/img_2.png">~~

# Groupe4_Quizz_Back — README backend

## But
Service REST Spring Boot pour gérer utilisateurs, quizz, questions et résultats.

## Prérequis
- JDK 17+
- Maven
- Base de données (ex: MySQL)
- Variables d'environnement ou fichier `src/main/resources/application.properties`

## Configuration
Exemples de propriétés (ou variables d'environnement équivalentes) :
- `spring.datasource.url=jdbc:mysql://localhost:3306/quizzdb`
- `spring.datasource.username=quizz`
- `spring.datasource.password=secret`
- `spring.jpa.hibernate.ddl-auto=update`
- `server.port=8080`

## Lancer en local
- Avec IntelliJ : exécuter la classe principale `BackSkeletonApplication` (ou `Application`).
- En ligne de commande :
  - `mvn spring-boot:run`
  - ou `mvn clean package` puis `java -jar target/*.jar`

## Endpoints (exemples)
- `POST /api/auth/login` — authentification
- `POST /api/auth/register` — création utilisateur
- `GET /api/quizzes` — lister quizz
- `POST /api/quizzes` — créer quizz (admin)
- `GET /api/quizzes/{id}/questions` — récupérer questions

## Fonctionnalités principales
- Authentification / autorisation (JWT ou session)
- CRUD Quizzes, Questions, Utilisateurs
- Enregistrement des scores / résultats
- API REST documentée (Swagger si présent)

## Base de données / migrations
- Si Flyway/Liquibase utilisé, vérifier dossiers `db/migration`.
- Sinon fournir script SQL d'initialisation dans `src/main/resources/sql/`.

## Logs & dépannage
- Consulter la console / `logs/` du jar.
- Pour erreurs de compilation liées à l'environnement Java, s'assurer d'utiliser JDK 17 et configurer Maven en conséquence.