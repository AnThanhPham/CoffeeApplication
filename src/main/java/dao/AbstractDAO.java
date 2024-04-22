package dao;

public interface AbstractDAO<T> {
	public void insert(T t);
	public void delete(T t);
	public void update(T t);
}