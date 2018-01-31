package com.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	
	public Archivos() {
		// TODO Auto-generated constructor stub
	}
	
	public Archivos(int archivoId, Usuarios usuarios, String ubicacion) {
		super();
		this.archivoId = archivoId;
		this.usuarios = usuarios;
		this.ubicacion = ubicacion;
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
	@Override
	public String toString() {
		return "Archivos [archivoId=" + archivoId + ", usuarios=" + usuarios + ", ubicacion=" + ubicacion + "]";
	}
	
	
}
