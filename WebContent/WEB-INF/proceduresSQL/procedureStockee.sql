CREATE PROCEDURE genererTest
	-- Add the parameters for the stored procedure here
	@idtheme int, 
	@nbQuestions int,
	@idEpreuve int
	
AS
BEGIN
	DECLARE 
	@idQuestion int

	DECLARE
	questions CURSOR FOR SELECT TOP (@nbQuestions) idQuestion,ROW_NUMBER() over(ORDER BY NEWID()) as ordre FROM QUESTION WHERE idTheme = @idtheme 
	 
	DECLARE
	@ordre int

	OPEN questions
		FETCH NEXT FROM questions INTO @idQuestion,@ordre
		WHILE @@FETCH_STATUS = 0
			BEGIN
				INSERT INTO QUESTION_TIRAGE (estMarquee,idQuestion,numordre,idEpreuve) VALUES ('false',@idQuestion,@ordre,@idEpreuve)
				FETCH NEXT FROM questions INTO @idQuestion,@ordre
			END
	CLOSE questions 
	DEALLOCATE questions
END
GO

