package dal;

import java.util.List;

public interface DAOGeneric<T> {
	public T add(T data);
	public T selectOne(int id);
	public List<T> selectAll();
	public void remove(int id);
	public void update (T data);
}
