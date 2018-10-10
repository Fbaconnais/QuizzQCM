DROP PROCEDURE gererMarquage
USE QCM_DB

CREATE PROCEDURE gererMarquage
	@idQuestion int,
	@idEpreuve int

	AS
	BEGIN
		DECLARE curseur CURSOR FOR SELECT estMarquee FROM QUESTION_TIRAGE WHERE (idQuestion = @idQuestion) AND (idEpreuve = @idEpreuve);
		DECLARE @estMarquee BIT
			OPEN curseur;
				FETCH NEXT FROM curseur INTO @estMarquee
				IF (@@FETCH_STATUS = 0)
					BEGIN
						IF @estMarquee = 'TRUE'
							BEGIN
							UPDATE Question_Tirage SET estMarquee=~estMarquee WHERE (idQuestion = @idQuestion) AND (idEpreuve = @idEpreuve)
							END
						ELSE
							BEGIN
							UPDATE Question_Tirage SET estMarquee=~estMarquee WHERE (idQuestion = @idQuestion) AND (idEpreuve = @idEpreuve)
							END
						END 
		
		
			CLOSE curseur;
			DEALLOCATE curseur;
	
	END
