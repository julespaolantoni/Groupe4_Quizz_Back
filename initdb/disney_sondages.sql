-- Base de données : Disney_Sondages
-- Date de création : 2025-10-13

CREATE DATABASE IF NOT EXISTS Disney_Sondages;
USE Disney_Sondages;

-- Table des sondages
CREATE TABLE sondage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    categorie ENUM('Film', 'Parc', 'Personnage', 'Musique', 'Attraction', 'Mechant') NOT NULL,
    description TEXT,
    date_creation DATE
);

-- Table des questions
CREATE TABLE question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sondage_id BIGINT,
    texte_question VARCHAR(255),
    FOREIGN KEY (sondage_id) REFERENCES sondage(id) ON DELETE CASCADE
);

-- Table des options de réponse
CREATE TABLE reponse_option (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT,
    texte_option VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

-- Table des résultats (nombre de votes)
CREATE TABLE resultat (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reponse_id BIGINT,
    nombre_votes INT DEFAULT 0,
    FOREIGN KEY (reponse_id) REFERENCES reponse_option(id) ON DELETE CASCADE
);

-- --------------------
-- Données exemples
-- --------------------

-- Sondage 1 : Films
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quel est votre film Disney préféré ?', 'Film', 'Sondage sur les films d’animation les plus populaires', '2025-09-20');

INSERT INTO question (sondage_id, texte_question) VALUES (1, 'Parmi ces films Disney, lequel préférez-vous ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(1, 'Le Roi Lion'),
(1, 'La Reine des Neiges'),
(1, 'Aladdin'),
(1, 'Vaiana');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (1, 320), (2, 410), (3, 210), (4, 180);

-- Sondage 2 : Parcs d’attraction
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quel est votre parc Disney favori ?', 'Parc', 'Sondage sur les expériences dans les parcs Disney du monde', '2025-09-22');

INSERT INTO question (sondage_id, texte_question) VALUES (2, 'Quel parc Disney préférez-vous visiter ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(2, 'Disneyland Paris'),
(2, 'Walt Disney World (Orlando)'),
(2, 'Tokyo Disneyland'),
(2, 'Disneyland California');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (5, 290), (6, 510), (7, 180), (8, 120);

-- Sondage 3 : Personnages
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quel personnage Disney est le plus emblématique ?', 'Personnage', 'Sondage sur les icônes de l’univers Disney', '2025-09-25');

INSERT INTO question (sondage_id, texte_question) VALUES (3, 'Lequel de ces personnages représente le mieux Disney selon vous ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(3, 'Mickey Mouse'),
(3, 'Donald Duck'),
(3, 'Simba'),
(3, 'Elsa');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (9, 480), (10, 210), (11, 150), (12, 260);

-- Sondage 4 : Musique Disney
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quelle chanson Disney vous touche le plus ?', 'Musique', 'Sondage sur les chansons emblématiques des films Disney', '2025-09-28');

INSERT INTO question (sondage_id, texte_question) VALUES (4, 'Parmi ces chansons Disney, laquelle préférez-vous ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(4, 'Liberee, delivree (La Reine des Neiges)'),
(4, 'Ce reve bleu (Aladdin)'),
(4, 'Hakuna Matata (Le Roi Lion)'),
(4, 'L histoire de la vie (Le Roi Lion)');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (13, 350), (14, 240), (15, 300), (16, 410);

-- Sondage 5 : Attractions
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quelle attraction Disney préférez-vous ?', 'Attraction', 'Sondage sur les attractions les plus appréciées des visiteurs', '2025-10-01');

INSERT INTO question (sondage_id, texte_question) VALUES (5, 'Quelle attraction Disney vous plaît le plus ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(5, 'Space Mountain'),
(5, 'Pirates des Caraibes'),
(5, 'It s a Small World'),
(5, 'Big Thunder Mountain');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (17, 260), (18, 380), (19, 150), (20, 340);

-- Sondage 6 : Mechants Disney
INSERT INTO sondage (titre, categorie, description, date_creation)
VALUES ('Quel mechant Disney est le plus marquant ?', 'Mechant', 'Sondage sur les antagonistes les plus charismatiques de Disney', '2025-10-03');

INSERT INTO question (sondage_id, texte_question) VALUES (6, 'Quel est selon vous le meilleur mechant Disney ?');

INSERT INTO reponse_option (question_id, texte_option) VALUES
(6, 'Malefique'),
(6, 'Scar'),
(6, 'Ursula'),
(6, 'Hades');

INSERT INTO resultat (reponse_id, nombre_votes) VALUES (21, 270), (22, 310), (23, 180), (24, 220);
