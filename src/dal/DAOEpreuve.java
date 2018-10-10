package dal;

import java.util.List;

import bo.Epreuve;

public interface DAOEpreuve extends DAOGeneric<Epreuve> {
		public int getIdTestViaIdEpreuve(int idEpreuve)throws DALException;
		public List<Epreuve> selectAllByIDProfil(int id) throws DALException;
		public void cloturerEpreuve(int idEpreuve) throws DALException;
		public List<Epreuve> selectAllByIDEtDate(int id) throws DALException;
		public List<Epreuve> getEpreuvesTermineesParCandidat (int idCandidat) throws DALException;
}
