package com.servicios;

import java.util.Date;

import com.dao.UsuariosDAO;
import com.entidades.Usuarios;

public class UsuarioServicios extends UsuariosDAO<Usuarios> {

	private String resultado = "";

	public UsuarioServicios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String guardar(String codigo, String nombre, String email, Date fecha, String directorioPath, String usuario,
			String clave, String tocken, Boolean actualizar, int tipo) {
		Usuarios usuarios = new Usuarios();

		if (actualizar) {
			usuarios = super.buscarId(Integer.parseInt(codigo));
			if (tipo == 1) {
				usuarios.setNombres(nombre);
				usuarios.setEmail(email);
				usuarios.setClave(clave);
				super.actualizar(usuarios);
				resultado = "Datos actualizados";
			} else if (tipo == 2) {
				usuarios.setTockenEscritorio(tocken);
				super.actualizar(usuarios);
			} else if (tipo == 3) {
				usuarios.setTockenAndroid(tocken);
				super.actualizar(usuarios);
			}

		} else {
			usuarios.setNombres(nombre);
			usuarios.setEmail(email);
			usuarios.setFecha(fecha);
			usuarios.setDirectorioPtha(directorioPath);
			usuarios.setUsuario(usuario);
			usuarios.setClave(clave);
			super.guardar(usuarios);
			resultado = "Datos ingresados";
		}

		return resultado;
	}

}
