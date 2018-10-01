package dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bo.Utilisateur;
import dal.DALException;
import dal.DAOUtilisateur;

public class DAOUtilisateurJdbcImpl implements DAOUtilisateur{
	
	private Connection conn = null;
	String selectOne = "Select u.idUtilisateur,u.nom,u.prenom,u.email, from Adherent where id=?";
	String selectAll = "Select * from Adherent";
	String remove = "DELETE FROM Adherent where id=?";
	String add = "INSERT INTO Adherent (nom,prenom,rue,cp,ville) VALUES (?,?,?,?,?)";
	String update = "UPDATE Adherent SET nom=?,prenom=?,rue=?,cp=?,ville=? WHERE id=?";
	
	public DAOUtilisateurJdbcImpl() {
			}
	
	
	private void closeConnection(Connection conn) throws DALException{
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DALException();
		}
	}
	
	
	
	
	
	@Override
	public Utilisateur add(Utilisateur data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Utilisateur data) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String Authentification(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
