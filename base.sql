DROP DATABASE IF EXISTS correction;
CREATE DATABASE correction;
\c correction;

CREATE TABLE candidat (
    id_candidat SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE correcteur (
    id_correcteur SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE matiere (
    id_matiere SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE resolution (
    id_resolution SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE operateur (
    id_operateur SERIAL PRIMARY KEY,
    operateur VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE note (
    id_note SERIAL PRIMARY KEY,
    id_candidat INTEGER NOT NULL,
    id_matiere INTEGER NOT NULL,
    id_correcteur INTEGER NOT NULL,
    note DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat) ON DELETE CASCADE,
    FOREIGN KEY (id_matiere) REFERENCES Matiere(id_matiere) ON DELETE CASCADE,
    FOREIGN KEY (id_correcteur) REFERENCES Correcteur(id_correcteur) ON DELETE CASCADE
);

CREATE TABLE parametre (
    id_parametre SERIAL PRIMARY KEY,
    id_matiere INTEGER NOT NULL,
    difference DECIMAL(5,2) NOT NULL,
    id_operateur INTEGER NOT NULL,
    id_resolution INTEGER NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES Matiere(id_matiere) ON DELETE CASCADE,
    FOREIGN KEY (id_operateur) REFERENCES Operateur(id_operateur) ON DELETE CASCADE,
    FOREIGN KEY (id_resolution) REFERENCES Resolution(id_resolution) ON DELETE CASCADE
);

CREATE TABLE note_finale (
    id_note_finale SERIAL PRIMARY KEY,
    id_candidat INTEGER NOT NULL,
    id_matiere INTEGER NOT NULL,
    note DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat) ON DELETE CASCADE,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere) ON DELETE CASCADE,
    UNIQUE(id_candidat, id_matiere)
);

INSERT INTO candidat (nom, prenom) VALUES 
('candidat1', 'candidat1'),
('candidat2', 'candidat2');

INSERT INTO Correcteur (nom, prenom) VALUES 
('Correcteur1', 'Correcteur1'),
('Correcteur2', 'Correcteur2'),
('Correcteur3', 'Correcteur3');

INSERT INTO Matiere (nom) VALUES 
('Matiere1'),
('Matiere2');

INSERT INTO Resolution (nom) VALUES
('Petite'),
('Moyenne'),
('Grande');

INSERT INTO Operateur (operateur) VALUES 
('<'),
('='),
('>');

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 1, 1, 10.00),
(1, 1, 2, 14.00),
(1, 1, 3, 16.00),
(1, 2, 1, 10.00),
(1, 2, 2, 11.00),
(1, 2, 3, 13.00);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(2, 1, 1, 10.00),
(2, 1, 2, 10.00);

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(1, 3.00, 1, 1),
(1, 3.00, 3, 2);

SELECT * FROM candidat;
SELECT * FROM correcteur;
SELECT * FROM matiere;
SELECT * FROM note;
SELECT * FROM parametre;
SELECT * FROM resolution;
SELECT * FROM note_finale;
