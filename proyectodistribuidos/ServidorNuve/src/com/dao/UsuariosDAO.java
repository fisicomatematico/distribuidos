package com.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

public class UsuariosDAO<Usuarios> extends GenericoDAOImplementa<Usuarios> {

	@SuppressWarnings("unchecked")
	public Usuarios login(String usuario, String clave) {
		Session entityManager = this.abriSession();
		try {
			return (Usuarios) entityManager.createQuery(
					"Select usuario from Usuarios usuario where usuario.usuario = :username and usuario.clave = :pass")
					.setParameter("username", usuario).setParameter("pass", clave).getSingleResult();
		} catch (NoResultException e) {
			return null;

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
	
	@SuppressWarnings("unchecked")
	public Usuarios busquedaUsuariosA(String tocken) {
		try {
			Session entityManager = this.abriSession();
			return (Usuarios) entityManager
					.createQuery("Select usuario from Usuarios usuario where usuario.tockenAndroid = :tocken")
					.setParameter("tocken", tocken).getSingleResult();
		} catch (NoResultException nre) {
			return null; // envia un null en caso de no encontrar el usuario
		}

	}
	
	@SuppressWarnings("unchecked")
	public Usuarios busquedaUsuariosMail(String email) {
		try {
			Session entityManager = this.abriSession();
			return (Usuarios) entityManager
					.createQuery("Select usuario from Usuarios usuario where usuario.email = :email")
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException nre) {
			return null; // envia un null en caso de no encontrar el usuario
		}

	}
}
