package dal;

import java.util.List;

public interface DAOGeneric<T> {
	public T add(T data) throws DALException;
	public T selectOne(int id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void remove(int id) throws DALException;
	public void update (T data) throws DALException;
}
