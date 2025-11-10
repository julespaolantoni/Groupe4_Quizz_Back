# Description de l'application Quizz Disney

## 1. Introduction

Ce projet est une application web complète conçue pour permettre aux utilisateurs de participer à des quizz sur le thème de l'univers Disney. Elle est développée avec une architecture découplée comprenant un backend en Java (Spring Boot) et un frontend en Angular.

L'objectif est de fournir une plateforme interactive et ludique où les utilisateurs peuvent tester leurs connaissances sur divers sujets Disney, tels que les films, les personnages, les parcs d'attractions, etc.

## 2. Fonctionnalités

L'application propose plusieurs fonctionnalités clés :

*   **Accueil** : Une page de bienvenue simple qui présente l'application.
*   **Authentification** : Un système de connexion et d'inscription pour les utilisateurs. Actuellement, cette fonctionnalité est simulée côté frontend pour permettre le développement de l'interface.
*   **Passer un Quiz** : Les utilisateurs peuvent répondre à une série de questions à choix multiples. À la fin du quiz, leur score est calculé et affiché.
*   **Statistiques** : Une page dédiée à l'affichage des statistiques de performance pour différents quizz, montrant par exemple le pourcentage de réussite par question.

## 3. Architecture Technique

Le projet est divisé en deux parties principales : le backend et le frontend.

### Backend (`Groupe4_Quizz_Back`)

*   **Technologie** : Développé en **Java** avec le framework **Spring Boot**.
*   **Rôle** :
    *   Gère toute la logique métier de l'application.
    *   Expose des **API REST** pour que le frontend puisse récupérer les quizz, les questions, soumettre des réponses, etc.
    *   Assure la persistance des données en se connectant à une base de données.

### Frontend (`Groupe4_Quizz_Front`)

*   **Technologie** : Développé avec **Angular**.
*   **Rôle** :
    *   Fournit l'interface utilisateur interactive et responsive.
    *   Communique avec le backend via des requêtes HTTP pour afficher les données et envoyer les actions de l'utilisateur.
    *   Composants principaux :
        *   `HomeComponent` : Page d'accueil.
        *   `AuthComponent` : Gestion de la connexion/inscription.
        *   `QuizComponent` : Interface pour passer un quiz.
        *   `StatsComponent` : Affichage des statistiques.

### Base de Données

*   **Type** : Une base de données relationnelle (probablement MySQL, comme suggéré par le script d'initialisation).
*   **Schéma** : Le script `initdb/disney_sondages.sql` définit la structure de la base de données avec les tables suivantes :
    *   `sondage` : Contient les informations sur un quiz (titre, catégorie, etc.).
    *   `question` : Stocke le texte des questions liées à un sondage.
    *   `reponse_option` : Contient les différentes options de réponse pour une question.
    *   `resultat` : Enregistre le nombre de votes pour chaque option de réponse.
*   **Données** : Le script inclut également un jeu de données d'exemple pour peupler l'application avec des quizz sur les films, parcs, personnages et méchants de Disney.