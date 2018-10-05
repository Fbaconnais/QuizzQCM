CREATE PROCEDURE gererReponseTirage
	@idProposition int,
	@idQuestion int,
	@idEpreuve int
	

AS
BEGIN
	DECLARE curseur CURSOR FOR SELECT * FROM REPONSE_TIRAGE WHERE (idProposition = @idProposition) AND (idQuestion = @idQuestion) AND (idEpreuve = @idEpreuve);
	DECLARE @Propid int
	DECLARE @Quesid int
	DECLARE @Eprid int
	
	OPEN curseur;
		FETCH NEXT FROM curseur INTO @Propid, @Quesid, @Eprid
		IF (@@FETCH_STATUS = 0)
			BEGIN
			DELETE FROM REPONSE_TIRAGE WHERE (idProposition = @idProposition) AND (idQuestion = @idQuestion) AND (idEpreuve = @idEpreuve)
			END
		ELSE
			BEGIN
			INSERT INTO REPONSE_TIRAGE VALUES (@idProposition, @idQuestion, @idEpreuve)
			END

	CLOSE curseur
	DEALLOCATE curseur
			
END
GO