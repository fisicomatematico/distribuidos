package com.servicios;

import java.util.Date;

import com.dao.UsuariosDAO;
import com.entidades.Usuarios;

public class UsuarioServicios extends UsuariosDAO<Usuarios>{
	
	private String resultado ="";

	public UsuarioServicios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String guardar(String codigo, String nombre, String email, Date fecha, String usuario, String clave, Boolean actualizar){
		Usuarios usuarios = new Usuarios();
		
		if(nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || clave.isEmpty()){
			resultado = "Llene todos los campos";
		}else{
			if(actualizar){
				usuarios = super.buscarId(Integer.parseInt(codigo));
				usuarios.setNombres(nombre);
				usuarios.setEmail(email);
				usuarios.setFecha(fecha);
				usuarios.setUsuario(usuario);
				usuarios.setClave(clave);
				super.actualizar(usuarios);

			}else{
				usuarios.setNombres(nombre);
				usuarios.setEmail(email);
				usuarios.setFecha(fecha);
				usuarios.setUsuario(usuario);
				usuarios.setClave(clave);
				super.guardar(usuarios);
				
			}	
		}
		
		return resultado;		
	}
	
}
