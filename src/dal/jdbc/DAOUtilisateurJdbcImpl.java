package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.Utilisateur;
import dal.DALException;
import dal.DAOUtilisateur;
import dal.ConnectionProvider;

public class DAOUtilisateurJdbcImpl implements DAOUtilisateur {

	private Connection conn = null;
	String selectOne = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email," + "p.libelle,"
			+ "pr.libelle" + " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) "
			+ "join PROMOTION pr on (u.codePromo = pr.codePromo)" + " where u.idUtilisateur=?";

	String selectAll = "Select " + "u.idUtilisateur," + "u.nom," + "u.prenom," + "u.email," + "p.libelle,"
			+ "pr.libelle" + " from UTILISATEUR u join PROFIL p on (u.codeProfil = p.codeProfil) "
			+ "join PROMOTION pr on (u.codePromo = pr.codePromo)";

	String remove = "DELETE FROM UTILISATEUR where idUtilisateur=?";
	String add = "INSERT INTO UTILISATEUR (nom,prenom,email,password,codeProfil,codePromo) VALUES (?,?,?,?,?,?)";
	String update = "UPDATE UTILISATEUR SET nom=?,prenom=?,email=?,password=?,codeProfil=?,codePromo=? WHERE idUtilisateur=?";

	public DAOUtilisateurJdbcImpl() {
	}

	private void closeConnection(Connection conn) throws DALException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DALException();
		}
	}

	@Override
	public Utilisateur add(Utilisateur data) {
		PreparedStatement rqt = null;
		try {
			conn = ConnectionProvider.getCnx();
			rqt = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1,data.getNom());
			rqt.setString(2, data.getPrenom());
			rqt.setString(3, data.getEmail());
			rqt.setString(4, data.getCp());
			rqt.setString(5, data.getVille());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					data.setNoAdherent(id);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

		return data;
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
