package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "archivos")
public class Archivos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5575603970181418841L;

	@Id
	@Column(name="id_archivos")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int archivoId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuarios usuarios;
	
	@Column(name="ubicacion")
	private String ubicacion;
	
	@Column(name="nombre_archivo")
	private String nombreArchivos;
	
	@Column(name ="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora")
	private Date hora;
	
	public Archivos() {
		// TODO Auto-generated constructor stub
	}
	
	public Archivos(int archivoId, Usuarios usuarios, String ubicacion, String nombreArchivos, Date fecha, Date hora) {
		super();
		this.archivoId = archivoId;
		this.usuarios = usuarios;
		this.ubicacion = ubicacion;
		this.nombreArchivos = nombreArchivos;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getArchivoId() {
		return archivoId;
	}

	public void setArchivoId(int archivoId) {
		this.archivoId = archivoId;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNombreArchivos() {
		return nombreArchivos;
	}

	public void setNombreArchivos(String nombreArchivos) {
		this.nombreArchivos = nombreArchivos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Archivos [archivoId=" + archivoId + ", usuarios=" + usuarios + ", ubicacion=" + ubicacion
				+ ", nombreArchivos=" + nombreArchivos + ", fecha=" + fecha + ", hora=" + hora + "]";
	}


	
	
}
