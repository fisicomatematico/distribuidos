package com.interfaces;

import java.util.List;

public interface GenericoDAOInterface <T>{

	public T guardar (T endidad);
	public T actualizar(T entidad);
	public void eliminar (T entidad);
	public T buscarId(Object id);
	public List<T> lista(); 
	public List<T> consulta(String consulta);
	
}
