# Groupe4_Quizz_Back — README

Présentation
------------
Service backend Spring Boot du projet de quizz. Fournit une API REST pour gérer les sondages/quizz, questions, options de réponses, résultats et utilisateurs. Le frontend Angular se trouve dans `../Groupe4_Quizz_Front` et communique avec ce service.

Ce README explique comment configurer et lancer le backend en local (Windows), via Docker, comment configurer la base de données, et donne des conseils de dépannage fréquents.

Fonctionnalités principales
---------------------------
- API REST pour : quizz/sondages, questions, options de réponse, résultats
- CRUD pour les entités (avec endpoints REST)
- Enregistrement des résultats / historique
- Contrôles d'erreurs et gestion d'exceptions centralisée
- Configuration possible pour JWT/CORS/Swagger selon le code (voir `config/`)

Prérequis
---------
- JDK 17+ (JAVA_HOME pointant sur le JDK)
- Maven wrapper fourni (`mvnw` / `mvnw.cmd`) — utilisation recommandée
- Base de données compatible (MySQL/Postgres, etc.) ou Docker
- (Optionnel) Docker & Docker Compose pour lancer une base de données rapidement

Fichiers utiles dans le repo
---------------------------
- `pom.xml` — dépendances et plugins Maven
- `mvnw`, `mvnw.cmd` — wrapper Maven (utilisez `mvnw.cmd` sur Windows)
- `docker-compose.yml` — définition de services (ex : base de données)
- `initdb/disney_sondages.sql` — script d'initialisation (exemple)
- `src/main/resources/application.properties` — configuration des propriétés Spring

Configuration
-------------
Le backend lit sa configuration dans `src/main/resources/application.properties` ou via des variables d'environnement. Exemples de propriétés à adapter :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quizzdb
spring.datasource.username=quizz
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

Vous pouvez également passer ces valeurs via des variables d'environnement (par ex. `SPRING_DATASOURCE_URL`) si vous déployez en conteneur.

Lancer en local (Windows)
-------------------------
Depuis la racine `Groupe4_Quizz_Back`, utilisez le Maven Wrapper fourni (commande pour cmd.exe) :

```cmd
cd ..\Groupe4_Quizz_Back
mvnw.cmd spring-boot:run
```

Ou pour builder puis exécuter le jar :

```cmd
mvnw.cmd clean package -DskipTests
java -jar target\back-skeleton-0.0.1-SNAPSHOT.jar
```

Si vous utilisez IntelliJ/IDEA, vous pouvez exécuter la classe principale `com.takima.backskeleton.BackSkeletonApplication` directement.

Lancer via Docker Compose
-------------------------
Si vous préférez démarrer une base de données via Docker (vérifiez `docker-compose.yml`) :

```cmd
cd ..\Groupe4_Quizz_Back
docker compose up -d
```

Ensuite, adaptez `application.properties` pour pointer vers le service DB du compose et démarrez l'application comme ci‑dessus.

Script d'initialisation
-----------------------
Un script d'exemple `initdb/disney_sondages.sql` est inclus. Vous pouvez l'utiliser pour préremplir la base (via Docker init ou exécution manuelle).

Endpoints exemples
------------------
(à adapter selon l'implémentation réelle) :

- POST /api/auth/login — authentification
- POST /api/auth/register — créer utilisateur
- GET /api/sondages — lister sondages/quizz
- GET /api/sondages/{id}/questions — récupérer les questions d'un sondage
- POST /api/sondages — créer sondage (admin)
- POST /api/resultats — envoyer un résultat

Consultez les controlleurs dans `src/main/java/com/takima/backskeleton/controllers` pour la liste exacte des endpoints.

Servir le frontend depuis le backend
-----------------------------------
Pour servir le frontend compilé avec Spring Boot :
1. Compiler le frontend (`Groupe4_Quizz_Front`) :
   - `npx ng build --configuration production` (ou `ng build` si `ng` installé)
2. Copier le contenu du dossier `dist/<app-name>` dans `Groupe4_Quizz_Back/src/main/resources/static`
3. Rebuilder le jar et démarrer :

```cmd
mvnw.cmd clean package -DskipTests
java -jar target\back-skeleton-0.0.1-SNAPSHOT.jar
```

Dépannage & erreurs fréquentes
------------------------------
- ExceptionInInitializerError / com.sun.tools.javac.code.TypeTag :: UNKNOWN
  - Causes possibles : incompatibilité entre JDK utilisé par Maven et la version du code, classpath contenant un ancien `tools.jar`, ou un plugin Maven disposant de configurations non compatibles.
  - Vérifications et solutions :
    1. Assurez-vous que `JAVA_HOME` pointe vers le JDK 17+ et que `mvnw.cmd` utilise ce JDK. Dans cmd :

```cmd
echo %JAVA_HOME%
%JAVA_HOME%\bin\java -version
mvnw.cmd -v
```

    2. Forcer le plugin `maven-compiler-plugin` dans `pom.xml` pour `source` et `target` 17 si nécessaire.
    3. Nettoyer le build et forcer la mise à jour des dépendances :

```cmd
mvnw.cmd clean -U package
```

    4. Recherchez et retirez toute dépendance ou configuration qui ajouterait d'anciens JAR d'outils Java (ex : `tools.jar`) sur le classpath.

- Problèmes de connexion à la base
  - Vérifiez l'URL, le nom d'utilisateur/mot de passe; démarrez la DB (Docker) et appliquez le script d'init si besoin.

- Erreurs CORS (le frontend ne peut pas appeler l'API)
  - En développement : utilisez un proxy Angular (`proxy.conf.json`) côté frontend ou activez CORS dans le backend (via `WebConfig` ou `@CrossOrigin`). Voir `src/main/java/com/takima/backskeleton/config/WebConfig.java`.

Conseils pour CI/Build
----------------------
- Utiliser le Maven Wrapper (`mvnw` / `mvnw.cmd`) pour garantir la même version de Maven sur les CI.
- Configurer des variables d'environnement pour les credentials DB sur CI/CD.
- Exécuter `mvnw.cmd test` pour lancer les tests unitaires avant le build.

Commandes utiles (Windows - cmd)
--------------------------------
- Lancer en dev :

```cmd
cd ..\Groupe4_Quizz_Back
mvnw.cmd spring-boot:run
```

- Builder le jar :

```cmd
mvnw.cmd clean package -DskipTests
```

- Exécuter les tests :

```cmd
mvnw.cmd test
```

Ressources et support
---------------------
- Consulter les sources dans `src/main/java/com/takima/backskeleton` pour comprendre le comportement réel des endpoints et la configuration.
- Pour les questions liées au code, ouvrez une issue dans le dépôt avec le message d'erreur complet et les étapes pour reproduire.

Licence / Contribuer
--------------------
- Respectez la licence du projet (si définie) et les conventions de contribution. Ouvrez une issue ou une PR pour proposer des corrections ou améliorations.

---

Si vous voulez, j'ajoute aussi automatiquement un `proxy.conf.json` d'exemple au frontend, ou j'harmonise le README racine pour pointer vers ces deux README locaux. Indiquez ce que vous préférez et je l'implémente.
