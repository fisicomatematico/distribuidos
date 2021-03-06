package com.controlador;

import java.util.Date;
import java.util.List;

import com.entidades.Archivos;
import com.entidades.Usuarios;
import com.servicios.ArchivosServicios;

public class ArchivoControlador {

	private ArchivosServicios servicios;
	private Archivos archivos;
	
	private boolean actualizar = false;
	private boolean eliminar = false;

	public ArchivoControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	public int buscarSiExisteE () {
		int repuesta = 0;
		servicios = new ArchivosServicios();
		repuesta = servicios.obtenerIdE(archivos.getUsuarios(), archivos.getNombreArchivos());
		return repuesta;
	}
	
	public String guardarArchivo () {
		String respuesta = "";
		actualizar = false;
		eliminar = false;
		servicios = new ArchivosServicios();
		Date fecha = new Date();
		respuesta = servicios.guardar(""+0, archivos.getUsuarios(), archivos.getUbicacion(), archivos.getNombreArchivos(), fecha, fecha, actualizar,eliminar);	
		return respuesta;
	}
	
	public String actualizarArchivo () {
		String respuesta = "";
		actualizar = true;
		eliminar = false;
		servicios = new ArchivosServicios();
		Date fecha = new Date();
		respuesta = servicios.guardar(""+archivos.getArchivoId(), archivos.getUsuarios(), archivos.getUbicacion(), archivos.getNombreArchivos(), fecha, fecha, actualizar, eliminar);
		return respuesta;
	}
	
	public String eliminarArchivos () {
		String respuesta = "";
		actualizar = false;
		eliminar = true;
		servicios = new ArchivosServicios();
		Date fecha = new Date();
		respuesta = servicios.guardar(""+archivos.getArchivoId(), archivos.getUsuarios(), archivos.getUbicacion(), archivos.getNombreArchivos(), fecha, fecha, actualizar, eliminar);
		return respuesta;
	}
	
	public String obtenerPath() {
		String respuesta = "";
		servicios = new ArchivosServicios();
		Archivos pathArchivo = servicios.buscarId(archivos.getArchivoId());
		respuesta = pathArchivo.getUbicacion();
		return respuesta;
	}
	
	public String [] archivoList (Usuarios usuarios){
		servicios = new ArchivosServicios();
		String listaArchivos [] = null;
		int extension = 0;
		int cont = 0;
		List <Archivos> archList = servicios.busquedaListArchivos(usuarios);
		extension = archList.size();
		listaArchivos = new String [extension];
		for (Archivos archivo : archList) {
			listaArchivos [cont] = archivo.getNombreArchivos();
			cont++;
		}
		return listaArchivos;
		
	}
}
