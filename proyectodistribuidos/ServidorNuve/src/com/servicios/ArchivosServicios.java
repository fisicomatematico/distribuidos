package com.servicios;

import java.util.Date;

import com.dao.ArchivosDAO;
import com.entidades.Archivos;
import com.entidades.Usuarios;

public class ArchivosServicios extends ArchivosDAO<Archivos>{

	private String resultado = "";

	public ArchivosServicios	() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String guardar(String codigo,Usuarios usuarios, String ubicacion, boolean actualizar) {
		Archivos archivos = new Archivos();

		if (actualizar) {
			archivos = super.buscarId(Integer.parseInt(codigo));
			archivos.setUsuarios(usuarios);
			archivos.setUbicacion(ubicacion);
			super.actualizar(archivos);
		} else {
			archivos.setUsuarios(usuarios);
			archivos.setUbicacion(ubicacion);
			super.guardar(archivos);
			resultado = "Datos ingresados";
		}

		return resultado;
	}
	
}
