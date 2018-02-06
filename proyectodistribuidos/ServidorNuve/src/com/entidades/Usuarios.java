package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118308956059002432L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuarioId;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "email")
	private String email;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "directorio_path")
	private String directorioPtha;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "clave")
	private String clave;

	@Column(name = "tocken_escritorio")
	private String tockenEscritorio;

	@Column(name = "tocken_web")
	private String tockenWeb;

	@Column(name = "token_android")
	private String tockenAndroid;

	public Usuarios() {

		// TODO Auto-generated constructor stub
	}

	public Usuarios(int usuarioId, String nombres, String email, Date fecha, String directorioPtha, String usuario,
			String clave, String tockenEscritorio, String tockenWeb, String tockenAndroid) {
		super();
		this.usuarioId = usuarioId;
		this.nombres = nombres;
		this.email = email;
		this.fecha = fecha;
		this.directorioPtha = directorioPtha;
		this.usuario = usuario;
		this.clave = clave;
		this.tockenEscritorio = tockenEscritorio;
		this.tockenWeb = tockenWeb;
		this.tockenAndroid = tockenAndroid;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDirectorioPtha() {
		return directorioPtha;
	}

	public void setDirectorioPtha(String directorioPtha) {
		this.directorioPtha = directorioPtha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTockenEscritorio() {
		return tockenEscritorio;
	}

	public void setTockenEscritorio(String tockenEscritorio) {
		this.tockenEscritorio = tockenEscritorio;
	}

	public String getTockenWeb() {
		return tockenWeb;
	}

	public void setTockenWeb(String tockenWeb) {
		this.tockenWeb = tockenWeb;
	}

	public String getTockenAndroid() {
		return tockenAndroid;
	}

	public void setTockenAndroid(String tockenAndroid) {
		this.tockenAndroid = tockenAndroid;
	}

	@Override
	public String toString() {
		return "Usuarios [usuarioId=" + usuarioId + ", nombres=" + nombres + ", email=" + email + ", fecha=" + fecha
				+ ", directorioPtha=" + directorioPtha + ", usuario=" + usuario + ", clave=" + clave
				+ ", tockenEscritorio=" + tockenEscritorio + ", tockenWeb=" + tockenWeb + ", tockenAndroid="
				+ tockenAndroid + "]";
	}

	

}
