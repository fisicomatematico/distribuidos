package com.controlador;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.swing.JPasswordField;

import com.entidades.Archivos;
import com.entidades.Usuarios;
import com.servicios.UsuarioServicios;

public class UsuarioControlador {

	private static String NUMEROS = "0123456789"; 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	
	private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' }; // cadena de carracteres para el cifrado de claves
	
	private UsuarioServicios servicios;
	private boolean actualizar = false;
	
	private Usuarios usuarios;
	public Usuarios users = null;

	public UsuarioControlador(Usuarios usuarios) {
		super();
		this.usuarios = usuarios;
	}
	
	public static String getPassword() {
		return getPassword(8);
	}
 
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
 
	public static String getPassword(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
	
	public String ingresarDatos() {
		actualizar = false;
		servicios = new UsuarioServicios();
		return servicios.guardar("0", usuarios.getNombres(), usuarios.getEmail(), usuarios.getFecha(), usuarios.getDirectorioPtha(),
				usuarios.getUsuario(), encriptaEnMD5(usuarios.getClave()), "",actualizar,0);
	}
	
	public int verificarUsuarios () {
		servicios = new UsuarioServicios();
		return servicios.obtenerUsuario(usuarios.getUsuario());
	}
	
	public String atualizarDatos() {
		actualizar = true;
		String repuesta = "";
		servicios = new UsuarioServicios();
		Usuarios users = servicios.busquedaUsuariosMail(usuarios.getEmail());
		if (users != null) {
		Date fecha = new Date();
		repuesta = servicios.guardar("0", usuarios.getNombres(), usuarios.getEmail(), fecha, "",
				"", encriptaEnMD5(usuarios.getClave()), "",actualizar,1);
		}else {
			repuesta = "Datos no actualizados";
		}
		return repuesta;	
		
	}
	
	public String loginUsuarioEscritorio () {
		String tocken = "";
		Date fecha = new Date();
		servicios = new UsuarioServicios();
		users = servicios.login(usuarios.getUsuario(), encriptaEnMD5(usuarios.getClave()));
		if (users != null) {
			actualizar = true;
			tocken = getPassword()+users.getUsuarioId();
			servicios.guardar(""+users.getUsuarioId(), "", "", fecha, "","", "", encriptaEnMD5(tocken), actualizar,2);
		}else {
			tocken = "false";
		}
		return tocken;
	}
	
	public String loginUsuarioAndroid () {
		String tocken = "";
		
		Date fecha = new Date();
		users = servicios.login(usuarios.getUsuario(), encriptaEnMD5(usuarios.getClave()));
		if (users != null) {
			actualizar = true;
			tocken = getPassword()+users.getUsuarioId();
			servicios.guardar(""+users.getUsuarioId(), "", "", fecha, "", "", "", encriptaEnMD5(tocken), actualizar,3);
		}else {
			tocken = "false";
		}
		return tocken;
	}
	
	public void crearDirectorio(String directorio) {
		File folder = new File (directorio);
		folder.mkdir();
	}
	
	public Usuarios listaUsuarios () {
		servicios = new UsuarioServicios();
		Usuarios usua = servicios.busquedaUsuarios(encriptaEnMD5(usuarios.getTockenEscritorio()));
		return usua;
	}
	
	public static String encriptaEnMD5(String stringAEncriptar)
	{
		try
		{
			MessageDigest msgd = MessageDigest.getInstance("MD5");
			byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
			StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
			for (int i = 0; i < bytes.length; i++)
			{
				int bajo = bytes[i] & 0x0f;
				int alto = (bytes[i] & 0xf0) >> 4;
			strbCadenaMD5.append(CONSTS_HEX[alto]);
			strbCadenaMD5.append(CONSTS_HEX[bajo]);
			}
			return strbCadenaMD5.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	
}
