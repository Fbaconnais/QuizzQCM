DROP PROCEDURE CloturerEpreuve
USE QCM_DB

CREATE PROCEDURE CloturerEpreuve
	@idEpreuve int

AS
BEGIN
	DECLARE @POINTSMAX int
	DECLARE @NOTE int
	DECLARE @SEUILHAUT int
	DECLARE @SEUILBAS int
	DECLARE questionsTirage CURSOR FOR SELECT qt.idQuestion,q.points FROM QUESTION_TIRAGE qt JOIN QUESTION q ON (qt.idQuestion = q.idQuestion) WHERE idEpreuve=@idEpreuve
	DECLARE @idQuestion int
	DECLARE @ptsQuestion int
	DECLARE @idProposition int
	DECLARE @bonne BIT
	DECLARE @candidat BIT
	DECLARE @compteur int
	DECLARE @idTest int

	CREATE TABLE tempTable (
			idQuestion			int NOT NULL,
			idProposition		int NOT NULL ,
			estBonne			BIT NOT NULL DEFAULT 'false',
			repCandidat			BIT NOT NULL DEFAULT 'false',
			CONSTRAINT tempTable_PK PRIMARY KEY (idProposition, idQuestion))
			

	OPEN questionsTirage
		FETCH NEXT FROM questionsTirage INTO @idQuestion,@ptsQuestion
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			DECLARE propCandidat CURSOR FOR SELECT idProposition FROM REPONSE_TIRAGE WHERE idEpreuve=@idEpreuve AND idQuestion=@idQuestion
			DECLARE propFormateur CURSOR FOR SELECT idProposition,estBonne FROM PROPOSITION WHERE idQuestion = @idQuestion		
			DECLARE pointsQuestion CURSOR FOR SELECT points FROM QUESTION WHERE idQuestion=@idQuestion

			OPEN propFormateur
				FETCH NEXT FROM propFormateur INTO @idProposition,@bonne
				WHILE @@FETCH_STATUS = 0
				BEGIN
					INSERT INTO tempTable(idQuestion,idProposition,estBonne) VALUES (@idQuestion,@idProposition,@bonne)
					FETCH NEXT FROM propFormateur INTO @idProposition,@bonne
				END
			CLOSE propFormateur
			DEALLOCATE propFormateur

			OPEN propCandidat
				FETCH NEXT FROM propCandidat INTO @idProposition
				WHILE @@FETCH_STATUS = 0
				BEGIN
					UPDATE tempTable SET repCandidat='true' WHERE idQuestion=@idQuestion AND idProposition=@idProposition 
					FETCH NEXT FROM propCandidat INTO @idProposition
				END
			CLOSE propCandidat
			DEALLOCATE propCandidat

			FETCH NEXT FROM questionsTirage INTO @idQuestion
		END
	
	FETCH FIRST FROM questionsTirage INTO @idQuestion,@ptsQuestion
		WHILE @@FETCH_STATUS = 0
		BEGIN
			SET @compteur = 0
			SET @POINTSMAX = @POINTSMAX + @ptsQuestion
			DECLARE curseur CURSOR FOR SELECT estBonne,repCandidat FROM tempTable WHERE idQuestion=@idQuestion
			OPEN curseur
				FETCH NEXT FROM curseur INTO @bonne,@candidat
				WHILE @@FETCH_STATUS = 0
				BEGIN
					IF (@bonne != @candidat)
						BEGIN
						SET @compteur = @compteur + 1
						END
					FETCH NEXT FROM curseur INTO @bonne,@candidat
				END
				IF (@compteur = 0)
					BEGIN
					SET @NOTE = @NOTE + @ptsQuestion
					END
			FETCH NEXT FROM questionsTirage INTO @idQuestion,@ptsQuestion
		END
	CLOSE questionsTirage
	DEALLOCATE questionsTirage


	DECLARE newCurseur CURSOR FOR SELECT  t.seuil_haut, t.seuil_bas FROM TEST t JOIN EPREUVE e ON (e.idTest = t.idTest) WHERE e.idEpreuve = @idEpreuve
	OPEN newCurseur
		FETCH NEXT FROM newCurseur INTO @SEUILHAUT,@SEUILBAS
	CLOSE newCurseur
	DEALLOCATE newCurseur

	DECLARE @NOTEFINALE float
	SET @NOTEFINALE = ROUND(@NOTE / @POINTSMAX * 20,2)
	IF (@NOTEFINALE < @SEUILBAS)
		BEGIN 
		UPDATE EPREUVE SET niveau_obtenu='NA' WHERE idEpreuve=@idEpreuve
		END
	ELSE IF (@NOTEFINALE < @SEUILHAUT)
		BEGIN
		UPDATE EPREUVE SET niveau_obtenu='ECA' WHERE idEpreuve=@idEpreuve
		END
		ELSE
			BEGIN
			UPDATE EPREUVE SET niveau_obtenu='A' WHERE idEpreuve=@idEpreuve
			END
	UPDATE EPREUVE SET etat='T',note_obtenue=@NOTEFINALE WHERE idEpreuve=@idEpreuve
	DROP TABLE tempTable
	DELETE FROM REPONSE_TIRAGE WHERE idEpreuve=@idEpreuve
	DELETE FROM QUESTION_TIRAGE WHERE idEpreuve=@idEpreuve

END
GO