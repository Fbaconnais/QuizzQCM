USE master
DROP DATABASE QCM_DB
CREATE DATABASE QCM_DB
USE QCM_DB




CREATE
  TABLE EPREUVE
  (
    idEpreuve         int NOT NULL identity,
    dateDedutValidite DATETIME NOT NULL ,
    dateFinValidite   DATETIME NOT NULL ,
    tempsEcoule       int ,
    etat              CHAR (2) NOT NULL ,
    note_obtenue FLOAT ,
    niveau_obtenu CHAR (15) ,
    idTest        int NOT NULL ,
    idUtilisateur int NOT NULL ,
    CONSTRAINT EPREUVE_PK PRIMARY KEY CLUSTERED (idEpreuve)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE PROFIL
  (
    codeProfil int NOT NULL identity,
    libelle    VARCHAR (100) NOT NULL ,
    CONSTRAINT PROFIL_PK PRIMARY KEY CLUSTERED (codeProfil)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE PROMOTION
  (
    codePromo CHAR (8) NOT NULL ,
    Libelle   VARCHAR (200) NOT NULL ,
    CONSTRAINT PROMOTION_PK PRIMARY KEY CLUSTERED (codePromo)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE PROPOSITION
  (
    idProposition int NOT NULL identity,
    enonce        VARCHAR (500) NOT NULL ,
    estBonne BIT NOT NULL ,
    idQuestion int NOT NULL ,
    CONSTRAINT Proposition_PK PRIMARY KEY CLUSTERED (idProposition, idQuestion)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE QUESTION
  (
    idQuestion int NOT NULL identity,
    enonce     VARCHAR (500) NOT NULL ,
    media VARCHAR (200) ,
	type_media VARCHAR(20) ,
    points  int NOT NULL ,
    idTheme int NOT NULL ,
    CONSTRAINT Question_PK PRIMARY KEY CLUSTERED (idQuestion)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE QUESTION_TIRAGE
  (
    estMarquee BIT NOT NULL ,
    idQuestion int NOT NULL ,
    numordre   int NOT NULL ,
    idEpreuve  int NOT NULL ,
    CONSTRAINT PK_questionTiré PRIMARY KEY CLUSTERED (idQuestion, idEpreuve)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE REPONSE_TIRAGE
  (
    idProposition int NOT NULL ,
    idQuestion    int NOT NULL ,
    idEpreuve     int NOT NULL ,
    CONSTRAINT Reponse_donnee_PK PRIMARY KEY CLUSTERED (idQuestion, idEpreuve,
    idProposition)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE SECTION_TEST
  (
    nbQuestionsATirer int NOT NULL ,
    idTest            int NOT NULL ,
    idTheme           int NOT NULL ,
    CONSTRAINT PK_SECTION_TEST PRIMARY KEY CLUSTERED (idTest, idTheme)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE TEST
  (
    idTest      int NOT NULL identity,
    libelle     VARCHAR (100) NOT NULL ,
    description VARCHAR (200) NOT NULL ,
    duree       int NOT NULL ,
    seuil_haut  int NOT NULL ,
    seuil_bas   int NOT NULL ,
	logo_langage VARCHAR (200),
    CONSTRAINT Test_PK PRIMARY KEY CLUSTERED (idTest)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE THEME
  (
    idTheme int NOT NULL identity,
    libelle VARCHAR (200) NOT NULL ,
    CONSTRAINT Theme_PK PRIMARY KEY CLUSTERED (idTheme)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO

CREATE
  TABLE UTILISATEUR
  (
    idUtilisateur int NOT NULL identity,
    nom           VARCHAR (250) NOT NULL ,
    prenom        VARCHAR (250) NOT NULL ,
    email         VARCHAR (250) NOT NULL ,
    password      VARCHAR (100) NOT NULL ,
    codeProfil    int NOT NULL ,
    codePromo     CHAR (8) ,
    CONSTRAINT UTILISATEUR_PK PRIMARY KEY CLUSTERED (idUtilisateur)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default" ,
  CONSTRAINT UTILISATEUR_EMAIL_UQ UNIQUE NONCLUSTERED (email) ON "default"
  )
  ON "default"
GO

ALTER TABLE UTILISATEUR
ADD CONSTRAINT Candidat_Promotion FOREIGN KEY
(
codePromo
)
REFERENCES PROMOTION
(
codePromo
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE EPREUVE
ADD CONSTRAINT Epreuve_Candidat FOREIGN KEY
(
idUtilisateur
)
REFERENCES UTILISATEUR
(
idUtilisateur
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE EPREUVE
ADD CONSTRAINT Epreuve_Test FOREIGN KEY
(
idTest
)
REFERENCES TEST
(
idTest
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE PROPOSITION
ADD CONSTRAINT Proposition_Question FOREIGN KEY
(
idQuestion
)
REFERENCES QUESTION
(
idQuestion
)
ON
DELETE CASCADE ON
UPDATE NO ACTION
GO

ALTER TABLE QUESTION
ADD CONSTRAINT Question_Theme FOREIGN KEY
(
idTheme
)
REFERENCES THEME
(
idTheme
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE REPONSE_TIRAGE
ADD CONSTRAINT Reponse_Proposition FOREIGN KEY
(
idProposition,
idQuestion
)
REFERENCES PROPOSITION
(
idProposition ,
idQuestion
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE REPONSE_TIRAGE
ADD CONSTRAINT Reponse_Tirage_Question_Tirage_fk FOREIGN KEY
(
idQuestion,
idEpreuve
)
REFERENCES QUESTION_TIRAGE
(
idQuestion ,
idEpreuve
)
ON
DELETE CASCADE ON
UPDATE NO ACTION
GO

ALTER TABLE SECTION_TEST
ADD CONSTRAINT Section_Test_Test_fk FOREIGN KEY
(
idTest
)
REFERENCES TEST
(
idTest
)
ON
DELETE CASCADE ON
UPDATE NO ACTION
GO

ALTER TABLE SECTION_TEST
ADD CONSTRAINT Section_Theme FOREIGN KEY
(
idTheme
)
REFERENCES THEME
(
idTheme
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE QUESTION_TIRAGE
ADD CONSTRAINT Tirage_Epreuve FOREIGN KEY
(
idEpreuve
)
REFERENCES EPREUVE
(
idEpreuve
)
ON
DELETE CASCADE ON
UPDATE NO ACTION
GO

ALTER TABLE QUESTION_TIRAGE
ADD CONSTRAINT Tirage_Question FOREIGN KEY
(
idQuestion
)
REFERENCES QUESTION
(
idQuestion
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO

ALTER TABLE UTILISATEUR
ADD CONSTRAINT Utilisateur_Profil FOREIGN KEY
(
codeProfil
)
REFERENCES PROFIL
(
codeProfil
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO