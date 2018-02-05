package com.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.controlador.ArchivoControlador;
import com.controlador.UsuarioControlador;
import com.entidades.Archivos;
import com.entidades.Usuarios;

public class EjecucionSocket extends Thread {

	private ServerSocket serverSocket;
	private String obtenerDatos;
	private String nombres;
	private String email;
	private String directorioPath = "/run/media/driera/Almacenamiento/archivos/";
	private String directorioEn;
	private String usuario;
	private String clave;
	private String tockenEscritorio;
	private String tockenAndroid;
	private int identificador;
	private String[] descomprimirDatos;
	private Usuarios usuarios;
	private Usuarios users;
	private Archivos archivo;
	private UsuarioControlador controlador = null;
	private ArchivoControlador controlador2 = null;
	private String archivos;
	private DataOutputStream output;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	byte[] receivedData;
	int inData;
	private String [] listaArchivos;

	public EjecucionSocket(int puerto) throws IOException {
		// TODO Auto-generated constructor stub
		serverSocket = new ServerSocket(puerto);
	}

	public void run() {
		String respuesta = "";
		while (true) {
			respuesta = "";
			try {
				System.out
						.println("Esperando conexion del cliente en el puerto " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("Conexión remota desde " + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				identificador = Integer.parseInt(in.readUTF());
				if (identificador == 1) {
					nombres = in.readUTF();
					email = in.readUTF();
					usuario = in.readUTF();
					directorioPath = directorioPath + usuario+"/";
					clave = in.readUTF();
					respuesta = nuevoUsuario();
					directorioPath = "/run/media/driera/Almacenamiento/archivos/";
				} else if (identificador == 2) {
					usuario = in.readUTF();
					clave = in.readUTF();
					respuesta = loguinUsuarioEscritorio();
				} else if (identificador == 3) {
					usuario = in.readUTF();
					clave = in.readUTF();
					respuesta = loguinUsuarioAndroid();
				} else if (identificador == 4) {
					nombres = in.readUTF();
					email = in.readUTF();
					clave = in.readUTF();
					respuesta = actalizarUsuario();
				} else if (identificador == 5) {
					receivedData = new byte[1024];
					bis = new BufferedInputStream(server.getInputStream());
					tockenEscritorio = in.readUTF();
					if (tomarDirectorioEscritorio() != 0) {
						archivos = in.readUTF();
						directorioEn = directorioEn+archivos;
						bos = new BufferedOutputStream(new FileOutputStream(directorioEn));
						while ((inData = bis.read(receivedData)) != -1) {
							bos.write(receivedData, 0, inData);
						}
						bos.close();
						guardarActualizar();
						directorioEn = "";
						respuesta = "Archivo ingresado";
					}else {
						respuesta = "Archivo no ingresado";
					}
				} else if (identificador == 6) {

				} else if (identificador == 7) {

				} else if (identificador == 8) {

				} else if (identificador == 9) {

				} else if (identificador == 11) {

				}
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF(respuesta);
				
				if (identificador == 2 || identificador == 3) {
					ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
					oos.writeObject(listaArchivos);
				}
				in.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public String nuevoUsuario() {
		String respuesta = "";
		usuarios = new Usuarios();
		usuarios.setUsuario(usuario);
		int busquedaUsuario = 0;
		controlador = new UsuarioControlador(usuarios);
		busquedaUsuario = controlador.verificarUsuarios();
		Date fecha = new Date();
		if (busquedaUsuario == 0) {
			usuarios.setNombres(nombres);
			usuarios.setEmail(email);
			usuarios.setFecha(fecha);
			usuarios.setDirectorioPtha(directorioPath);
			usuarios.setClave(clave);
			controlador = new UsuarioControlador(usuarios);
			respuesta = controlador.ingresarDatos();
			controlador.crearDirectorio(directorioPath);
		} else {
			respuesta = "NO";
		}
		return respuesta;
	}

	public String actalizarUsuario() {
		String respuesta = "";
		usuarios = new Usuarios();
		usuarios.setNombres(nombres);
		usuarios.setEmail(email);
		usuarios.setClave(clave);
		controlador = new UsuarioControlador(usuarios);
		respuesta = controlador.atualizarDatos();
		return respuesta;
	}

	public String loguinUsuarioEscritorio() {
		String respuesta = "";
		usuarios = new Usuarios();
		usuarios.setUsuario(usuario);
		usuarios.setClave(clave);
		controlador = new UsuarioControlador(usuarios);
		respuesta = controlador.loginUsuarioEscritorio();
		controlador2 = new ArchivoControlador();
		listaArchivos = controlador2.archivoList(controlador.users);
		return respuesta;
	}

	public String loguinUsuarioAndroid() {
		String respuesta = "";
		usuarios = new Usuarios();
		usuarios.setUsuario(usuario);
		usuarios.setClave(clave);
		controlador = new UsuarioControlador(usuarios);
		respuesta = controlador.loginUsuarioAndroid();
		controlador2 = new ArchivoControlador();
		listaArchivos = controlador2.archivoList(controlador.users);
		return respuesta;
	}

	public int tomarDirectorioEscritorio() {
		int repuesta = 0;
		usuarios = new Usuarios();
		usuarios.setTockenEscritorio(tockenEscritorio);
		controlador = new UsuarioControlador(usuarios);
		users = controlador.listaUsuarios();
		repuesta = users.getUsuarioId();
		directorioEn = users.getDirectorioPtha();
		return repuesta;
	}
	
	public String guardarActualizar () {
		String respuesta = "";
		archivo = new Archivos();
		archivo.setUsuarios(users);
		archivo.setUbicacion(directorioEn);
		controlador2 = new ArchivoControlador(archivo);
		int idArch = 0;
		idArch = controlador2.buscarSiExiste();
		if (idArch != 0) {
			archivo.setArchivoId(idArch);
			controlador2 = new ArchivoControlador(archivo);
			respuesta = controlador2.actualizarArchivo();
		}else {
			respuesta = controlador2.guardarArchivo();
		}
		return respuesta;
	}

}
