package dal;

import java.util.List;

import bo.Epreuve;

public interface DAOEpreuve extends DAOGeneric<Epreuve> {

		public List<Epreuve> selectAllByIDProfil(int id) throws DALException;
}
