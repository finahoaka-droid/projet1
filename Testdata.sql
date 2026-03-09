

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
(1, 4.00, 4, 2),
(1, 2.00, 1, 1),
(2, 10.00, 2, 3),
(2, 11.00, 3, 2);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 
(1, 1, 1, 8.00),
(1, 1, 2, 9.00),
(1, 1, 3, 10.00),
(1, 2, 1, 13.00),
(1, 2, 2, 12.00),
(1, 2, 3, 8.50),

(2, 1, 1, 9.50),
(2, 1, 2, 10.00),
(2, 1, 3, 10.00),
(2, 2, 1, 10.00),
(2, 2, 2, 20.00);
