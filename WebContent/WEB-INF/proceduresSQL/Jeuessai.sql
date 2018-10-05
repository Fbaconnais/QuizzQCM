use QCM_DB

-- Commencer par ins�rer les promotions et les profils

INSERT INTO promotion VALUES ('DJT129', '129�me session de la formation D�veloppeur J-Table');
INSERT INTO promotion VALUES ('DL305', '305�me session de la formation D�veloppeur Logiciel');
INSERT INTO promotion VALUES ('CDA35', '35�me session de la formation Concepteur D�veloppeur Applications');
INSERT INTO promotion VALUES ('DWWM104', '104�me session de la formation D�veloppeur Web et Web Mobile');
INSERT INTO promotion VALUES ('ASR129', '129�me session de la formation Administrateur Syst�me et R�seau');

INSERT INTO profil(libelle) VALUES ('stagiaire');
INSERT INTO profil(libelle) VALUES ('candidat libre');
INSERT INTO profil(libelle) VALUES ('formateur');
INSERT INTO profil(libelle) VALUES ('cellule de recrutement');
INSERT INTO profil(libelle) VALUES ('responsable de formation');
INSERT INTO profil(libelle) VALUES ('administrateur');

-- Ins�rer ensuite les utilisateurs

INSERT INTO utilisateur(nom,prenom,email,password,codeProfil,codePromo) VALUES ('Levallois', 'Damien', 'd.levallois@gmail.com', 'labstinent', 1,'DL305');
INSERT INTO utilisateur(nom,prenom,email,password,codeProfil) VALUES ('Sanchez', 'Denis', 'd.sanchez@free.fr', 'metallourd', 5);
INSERT INTO utilisateur(nom,prenom,email,password,codeProfil) VALUES ('Chichpoichiche', 'Charles', 'c.chichpoichiche@gmail.com', 'gugheru', 2);
INSERT INTO utilisateur(nom,prenom,email,password,codeProfil) VALUES ('Malabry', 'Emmanuel', 'e.malabry@aol.fr', 'lastar', 3);
INSERT INTO utilisateur(nom,prenom,email,password,codeProfil) VALUES ('Istrateur', 'Amine', 'a.istrateur@msn.com', 'admin', 6);
INSERT INTO utilisateur(nom,prenom,email,password,codeProfil) VALUES ('Luche', 'Hercole', 'h.luche@yahoo.fr', 'boss', 4);

-- Cr�ation des tests

INSERT INTO test(libelle, description, duree, seuil_haut, seuil_bas, logo_langage) VALUES ('Epreuve de PL-SQL', 'Les jaunes devront passer cette �preuve pour remporter le sac de riz', 3600, 8, 12,'/images/logos/PLSQL.png');
INSERT INTO test(libelle, description, duree, seuil_haut, seuil_bas, logo_langage) VALUES ('Epreuve de Java EE', 'Les rouges devront d�velopper une application de QCM', 3600, 8, 12,'/images/logos/jee.png');
INSERT INTO test(libelle, description, duree, seuil_haut, seuil_bas, logo_langage) VALUES ('Epreuve de HTML', 'Qui terminera ce QCM? La r�ponse apr�s la pub, dans un nouvel �pisode de Koh Enilanta', 3600, 8, 12,'/images/logos/html5.png');
INSERT INTO test(libelle, description, duree, seuil_haut, seuil_bas, logo_langage) VALUES ('Epreuve de Javascript', '<script console.log(Bonne chance) /script>', 3600, 8, 12,'/images/logos/javascript.png');
INSERT INTO test(libelle, description, duree, seuil_haut, seuil_bas, logo_langage) VALUES ('Epreuve de SQL', 'La r�ponse de la question 3 est DROP DATABASE', 3600, 8, 12,'/images/logos/SQL.jpg');

-- Cr�ation des th�mes

INSERT INTO theme VALUES ('PL-SQL');
INSERT INTO theme VALUES ('Java EE');
INSERT INTO theme VALUES ('HTML');
INSERT INTO theme VALUES ('Javascript');
INSERT INTO theme VALUES ('SQL');

-- Cr�ation des sections

INSERT INTO section_test VALUES (5, 1, 1);
INSERT INTO section_test VALUES (5, 2, 2);
INSERT INTO section_test VALUES (5, 3, 3);
INSERT INTO section_test VALUES (5, 4, 4);
INSERT INTO section_test VALUES (5, 5, 5);

-- Cr�ation des questions

INSERT INTO question(enonce, points, idTheme) VALUES ('En quelle ann�e Napol�on utilisa-t-il sa connaissance du PL-SQL pour coder son fameux jeu de rencontre "Les Bonnes Nanapol�ons"?',  2, 1);
INSERT INTO question(enonce, points, idTheme) VALUES ('Le cookie est un petit animal de Papouasie, vrai ou faux?',  2, 2);
INSERT INTO question(enonce, points, idTheme) VALUES ('Quel attribut ne peut �tre donn� � un bouton:"name", "id", "type" ou "drop_database"?',  2, 3);
INSERT INTO question(enonce, points, idTheme) VALUES ('Que fait la fonction callback?',  2, 4);
INSERT INTO question(enonce, points, idTheme) VALUES ('To drop or not to drop database?',  2, 5);

-- Cr�ation des propositions

INSERT INTO proposition VALUES ('1812', 1, 1);
INSERT INTO proposition VALUES ('1814', 0, 1);
INSERT INTO proposition VALUES ('1712', 0, 1);
INSERT INTO proposition VALUES ('1817', 0, 1);
INSERT INTO proposition VALUES ('1834', 0, 1);
INSERT INTO proposition VALUES ('Vrai', 1, 2);
INSERT INTO proposition VALUES ('Faux', 0, 2);
INSERT INTO proposition VALUES ('name', 0, 3);
INSERT INTO proposition VALUES ('id', 0, 3);
INSERT INTO proposition VALUES ('type', 0, 3);
INSERT INTO proposition VALUES ('drop database', 1, 3);
INSERT INTO proposition VALUES ('Elle permet de se faire appeler sur son t�l�phone intelligent', 0, 4);
INSERT INTO proposition VALUES ('La fonction en param�tre est appel�e de nouveau � la fin du programme', 1, 4);
INSERT INTO proposition VALUES ('To drop', 1, 5);
INSERT INTO proposition VALUES ('Not to drop', 0, 5);

-- Cr�ation des �preuves

INSERT INTO epreuve(dateDedutValidite, dateFinValidite, etat, idTest, idUtilisateur) VALUES (2018-10-07, 2018-10-14, 'EA', 1, 1);
INSERT INTO epreuve(dateDedutValidite, dateFinValidite, etat, idTest, idUtilisateur) VALUES (2018-10-07, 2018-10-14, 'EA', 2, 1);
INSERT INTO epreuve(dateDedutValidite, dateFinValidite, etat, idTest, idUtilisateur) VALUES (2018-10-07, 2018-10-14, 'EC', 3, 2);
INSERT INTO epreuve(dateDedutValidite, dateFinValidite, etat, idTest, idUtilisateur) VALUES (2018-10-07, 2018-10-14, 'EC', 4, 2);
INSERT INTO epreuve(dateDedutValidite, dateFinValidite, etat, idTest, idUtilisateur) VALUES (2018-10-07, 2018-10-14, 'T', 5, 2);