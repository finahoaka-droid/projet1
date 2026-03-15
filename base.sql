DROP DATABASE IF EXISTS correction2;
CREATE DATABASE correction2;
\c correction2;

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
