

INSERT INTO candidat (nom, prenom) VALUES
('Candidat1','Candidat1'),
('Candidat2','Candidat2');

INSERT INTO correcteur (nom, prenom) VALUES
('Correcteur1','Correcteur1'),
('Correcteur2','Correcteur2'),
('Correcteur3','Correcteur3');


INSERT INTO matiere (nom) VALUES
('JAVA'),
('PHP');


INSERT INTO Resolution (nom) VALUES
('Petite'),
('Grande'),
('Moyenne');


INSERT INTO operateur (operateur) VALUES
('<'),
('<='),
('>'),
('>=');

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(1, 7.00, 1, 2),
(1, 7.00, 4, 3),
(2, 2.00, 2, 1),
(2, 2.00, 3, 2);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 1, 1, 15.00),
(1, 1, 2, 10.00),
(1, 1, 3, 12.00),
(1, 2, 1, 10.00),
(1, 2, 2, 10.00),

(2, 1, 1, 9.00),
(2, 1, 2, 8.00),
(2, 1, 3, 11.00),
(2, 2, 1, 13.00),
(2, 2, 2, 11.00);


INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 1, 1, 10.0),
(1, 1, 2, 12.0),
(1, 1, 3, 14.0);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 2, 1, 8.0),
(1, 2, 2, 10.0),
(1, 2, 3, 9.0);

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES
(1, 5.0, 4, 1),
(1, 7.0, 1, 2),
(1, 6.0, 4, 3),
(1, 8.0, 1, 2);

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES
(2, 3.0, 4, 1),
(2, 5.0, 1, 2),
(2, 4.0, 4, 3),
(2, 6.0, 1, 2);