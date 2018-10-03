
CREATE PROCEDURE genererTest
	-- Add the parameters for the stored procedure here
	@idTest int,
	@idEpreuve int
	
AS
BEGIN
	DECLARE 
	@idQuestion int

	DECLARE
	@nbQuestions int

	DECLARE
	@idtheme int

	DECLARE
	@ordreQuestion int

	DECLARE
	sections CURSOR FOR SELECT idTheme,nbQuestionsATirer FROM SECTION_TEST WHERE idTest=@idTest


	CREATE TABLE temp_questions (
	id				int			identity not null CONSTRAINT temp_pk PRIMARY KEY,
	idQuestion		int)	



	OPEN sections
		FETCH NEXT FROM sections INTO @idtheme,@nbQuestions
		WHILE @@FETCH_STATUS = 0
			BEGIN
				DECLARE
				questions CURSOR FOR SELECT TOP (@nbQuestions) idQuestion FROM QUESTION WHERE idTheme = @idtheme ORDER BY NEWID()

				open questions
					FETCH NEXT FROM questions INTO @idQuestion
					WHILE @@FETCH_STATUS = 0
						BEGIN
							INSERT INTO temp_questions(idQuestion) VALUES (@idQuestion)
							FETCH NEXT FROM questions INTO @idQuestion
						END
				CLOSE questions
				DEALLOCATE questions	
				FETCH NEXT FROM sections INTO @idtheme,@nbQuestions
			END
	CLOSE sections
	DEALLOCATE sections

	DECLARE
	listeACopier CURSOR FOR SELECT id,idQuestion FROM temp_questions ORDER BY id ASC

	OPEN listeACopier
		FETCH NEXT FROM listeACopier INTO @ordreQuestion,@idQuestion
		WHILE @@FETCH_STATUS = 0
			BEGIN
				INSERT INTO QUESTION_TIRAGE(estMarquee,idQuestion,numordre,idEpreuve) VALUES ('false',@idQuestion,@ordreQuestion,@idEpreuve)
				FETCH NEXT FROM listeACopier INTO @ordreQuestion,@idQuestion
			END
	CLOSE listeACopier
	DEALLOCATE listeACopier

	DROP TABLE temp_questions
	
	
END
GO

DROP PROCEDURE genererTest