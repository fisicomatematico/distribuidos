package com.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import com.entidades.Usuarios;

public class ArchivosDAO <Archivos> extends GenericoDAOImplementa<Archivos>{

	public int obtenerId(Usuarios usuarios, String rutaDirecto) {
		Session query = this.abriSession();
		try {
			return Integer.parseInt(String.valueOf(query
					.createQuery("Select archivos.archivoId from Archivos archivos where archivos.usuarios = :usuarios and archivos.ubicacion = :rutaDirecto")
					.setParameter("usuarios", usuarios)
					.setParameter("rutaDirecto", rutaDirecto)
					.getSingleResult()));
		} catch (NoResultException e) {
			return 0;

		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Archivos> busquedaListArchivos(Usuarios usuarios){
		Session entityManager = this.abriSession();
		return (List<Archivos>) entityManager.createQuery("Select archivos from Archivos archivos where archivos.usuarios = :usuarios")
				.setParameter("usuarios", usuarios)
				.getResultList();

	}
	
	
}
