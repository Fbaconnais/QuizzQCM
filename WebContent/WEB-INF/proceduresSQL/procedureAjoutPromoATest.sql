CREATE PROCEDURE inscrirePromoATest
	-- Add the parameters for the stored procedure here
	@codePromo varchar(10),
	@idTest int,
	@dateDebut date,
	@dateFin date
	
AS
BEGIN
	

	DECLARE
	@idUtilisateur int

	DECLARE
	users CURSOR FOR SELECT idUtilisateur FROM UTILISATEUR WHERE codePromo=@codePromo

	OPEN users
		FETCH NEXT FROM users INTO @idUtilisateur
		WHILE @@FETCH_STATUS = 0
			BEGIN
				INSERT INTO EPREUVE(dateDedutValidite,dateFinValidite,etat,idTest,idUtilisateur)
				 VALUES (@dateDebut,@dateFin,'EA',@idTest,@idUtilisateur)
				
				FETCH NEXT FROM users INTO @idUtilisateur
			END
	CLOSE users
	DEALLOCATE users

	
	
END
GO

DROP PROCEDURE genererTest