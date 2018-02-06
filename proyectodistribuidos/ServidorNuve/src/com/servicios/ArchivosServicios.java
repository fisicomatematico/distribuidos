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

	public String guardar(String codigo,Usuarios usuarios, String ubicacion, String nombreArchivo, Date fecha, Date hora, boolean actualizar, boolean eliminar) {
		Archivos archivos = new Archivos();

		if (actualizar) {
			archivos = super.buscarId(Integer.parseInt(codigo));
			archivos.setUsuarios(usuarios);
			archivos.setUbicacion(ubicacion);
			archivos.setNombreArchivos(nombreArchivo);
			archivos.setFecha(fecha);
			archivos.setHora(hora);
			super.actualizar(archivos);
			resultado = "Datos actualizados";
		}else if (eliminar) {
			archivos = super.buscarId(Integer.parseInt(codigo));
			archivos.setUsuarios(usuarios);
			archivos.setUbicacion(ubicacion);
			archivos.setNombreArchivos(nombreArchivo);
			archivos.setFecha(fecha);
			archivos.setHora(hora);
			super.eliminar(archivos);
			resultado = "Datos eliminados";
		}else {
			archivos.setUsuarios(usuarios);
			archivos.setUbicacion(ubicacion);
			archivos.setNombreArchivos(nombreArchivo);
			archivos.setFecha(fecha);
			archivos.setHora(hora);
			super.guardar(archivos);
			resultado = "Datos ingresados";
		}

		return resultado;
	}
	
}
