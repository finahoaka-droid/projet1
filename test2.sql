INSERT INTO candidat (nom, prenom) VALUES
('Candidat1','Candidat1'),
('Candidat2','Candidat2'),
('Candidat3','Candidat3'),
('Candidat4','Candidat4');

INSERT INTO correcteur (nom, prenom) VALUES
('Correcteur1','Correcteur1'),
('Correcteur2','Correcteur2'),
('Correcteur3','Correcteur3');


INSERT INTO resolution (nom) VALUES 
('Petite'),
('Grande'),
('Moyenne');

INSERT INTO operateur (operateur) VALUES 
('<'),
('<='),
('>'),
('>=');

INSERT INTO matiere (nom) VALUES 
('JAVA'),
('PHP'),
('Python'),
('C++');

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

(1, 1, 1, 18.00),
(1, 1, 2, 12.00),
(1, 1, 3, 15.00),

(2, 1, 1, 14.00),
(2, 1, 2, 13.00),
(2, 1, 3, 15.00),

(3, 1, 1, 10.00),
(3, 1, 2, 20.00),

(4, 1, 1, 12.00),
(4, 1, 2, 12.00),
(4, 1, 3, 12.00);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

(1, 2, 1, 8.00),
(1, 2, 2, 16.00),

(2, 2, 1, 11.00),
(2, 2, 2, 9.00),

(3, 2, 1, 14.00),
(3, 2, 2, 14.00),
(3, 2, 3, 14.00),

(4, 2, 1, 5.00),
(4, 2, 2, 15.00),
(4, 2, 3, 10.00);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

(1, 3, 1, 17.00),
(1, 3, 2, 13.00),
(1, 3, 3, 11.00),

(2, 3, 1, 10.00),
(2, 3, 2, 12.00),
(2, 3, 3, 14.00),

(3, 3, 1, 9.00),
(3, 3, 2, 9.00),

(4, 3, 1, 6.00),
(4, 3, 2, 18.00),
(4, 3, 3, 12.00);

INSERT INTO note (id_candidat, id_matiere, id_correcteur, note) VALUES 

(1, 4, 1, 15.00),
(1, 4, 2, 16.00),
(1, 4, 3, 14.00),

(2, 4, 1, 7.00),
(2, 4, 2, 13.00),
(2, 4, 3, 10.00),

(3, 4, 1, 11.00),
(3, 4, 2, 11.00),

(4, 4, 1, 8.00),
(4, 4, 2, 12.00);


INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(1, 10, 3, 2),
(1, 14, 1, 1),


(1, 5, 4, 3),

(1, 10, 2, 1),
(1, 10, 4, 2);

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 

(2, 5, 1, 1),
(2, 5, 2, 2),
(2, 5, 3, 3),
(2, 5, 4, 1);

INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(3, 6, 3, 2),
(3, 10, 1, 1),
(3, 9, 4, 3);


INSERT INTO parametre (id_matiere, difference, id_operateur, id_resolution) VALUES 
(4, 0, 2, 2),
(4, 0, 4, 1),

(4, 4, 3, 3),
(4, 5, 1, 2);
