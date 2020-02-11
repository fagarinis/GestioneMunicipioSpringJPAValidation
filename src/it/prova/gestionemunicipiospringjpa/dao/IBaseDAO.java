package it.prova.gestionemunicipiospringjpa.dao;

import java.util.List;

public interface IBaseDAO<T> {

	public List<T> list();

	public T get(long id);

	public void update(T o);

	public void insert(T o);

	public void delete(T o);

	public List<T> findByExample(T o);
	
}
