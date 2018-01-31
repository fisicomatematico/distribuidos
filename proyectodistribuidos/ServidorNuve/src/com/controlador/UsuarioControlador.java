package com.controlador;

import com.entidades.Usuarios;
import com.servicios.UsuarioServicios;

public class UsuarioControlador {

	private UsuarioServicios servicios;
	private boolean actualizar = false;
	private String mensajes;
	
	private Usuarios usuarios;

	public UsuarioControlador(Usuarios usuarios) {
		super();
		this.usuarios = usuarios;
	}
	
	public String ingresarDatos() {
		actualizar = false;
		servicios = new UsuarioServicios();
		return servicios.guardar("0", usuarios.getNombres(), usuarios.getEmail(), usuarios.getFecha(), usuarios.getUsuario(), usuarios.getClave(), actualizar);
	}
	
	public String atualizarDatos() {
		actualizar = false;
		servicios = new UsuarioServicios();
		return servicios.guardar("0", usuarios.getNombres(), usuarios.getEmail(), usuarios.getFecha(), usuarios.getUsuario(), usuarios.getClave(), actualizar);
	}
	
}
