package com.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

public class UsuariosDAO<Usuarios> extends GenericoDAOImplementa<Usuarios> {

	public int login(String usuario, String clave) {
		Session query = this.abriSession();
		try {
			return Integer.parseInt(String.valueOf(query.createQuery(
					"Select usuario.usuarioId from Usuarios usuario where usuario.usuario = :username and usuario.clave = :pass")
					.setParameter("username", usuario).setParameter("pass", clave).getSingleResult()));
		} catch (NoResultException e) {
			return -1;

		}
	}

	public int obtenerUsuario(String user) {
		Session query = this.abriSession();
		try {
			return Integer.parseInt(String.valueOf(query
					.createQuery("Select usuario.usuarioId from Usuarios usuario where usuario.usuario = :username")
					.setParameter("username", user).getSingleResult()));
		} catch (NoResultException e) {
			return 0;

		}
	}

	@SuppressWarnings("unchecked")
	public Usuarios busquedaUsuarios(String tocken) {
		try {
			Session entityManager = this.abriSession();
			return (Usuarios) entityManager
					.createQuery("Select usuario from Usuarios usuario where usuario.tockenEscritorio = :tocken")
					.setParameter("tocken", tocken).getSingleResult();
		} catch (NoResultException nre) {
			return null; // envia un null en caso de no encontrar el usuario
		}

	}
}
