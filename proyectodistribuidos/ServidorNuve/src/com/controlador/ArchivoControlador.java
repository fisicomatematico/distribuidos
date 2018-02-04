package com.controlador;

import com.entidades.Archivos;
import com.servicios.ArchivosServicios;

public class ArchivoControlador {

	private ArchivosServicios servicios;
	private Archivos archivos;
	
	private boolean actualizar = false;
	private boolean eliminar = false;

	public ArchivoControlador(Archivos archivos) {
		super();
		this.archivos = archivos;
	}
	
	public int buscarSiExiste () {
		int repuesta = 0;
		servicios = new ArchivosServicios();
		repuesta = servicios.obtenerId(archivos.getUsuarios(), archivos.getUbicacion());
		return repuesta;
	}
	
	public String guardarArchivo () {
		String respuesta = "";
		actualizar = false;
		eliminar = false;
		servicios = new ArchivosServicios();
		respuesta = servicios.guardar(""+0, archivos.getUsuarios(), archivos.getUbicacion(), actualizar,eliminar);	
		return respuesta;
	}
	
	public String actualizarArchivo () {
		String respuesta = "";
		actualizar = true;
		eliminar = false;
		servicios = new ArchivosServicios();
		respuesta = servicios.guardar(""+archivos.getArchivoId(), archivos.getUsuarios(), archivos.getUbicacion(), actualizar, eliminar);
		return respuesta;
	}
	
	public String eliminarArchivos () {
		String respuesta = "";
		actualizar = false;
		eliminar = true;
		servicios = new ArchivosServicios();
		respuesta = servicios.guardar(""+archivos.getArchivoId(), archivos.getUsuarios(), archivos.getUbicacion(), actualizar, eliminar);
		return respuesta;
	}
	
}
