package com.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.controlador.UsuarioControlador;
import com.entidades.Usuarios;

public class EjecucionSocket extends Thread {

	private ServerSocket serverSocket;
	private String obtenerDatos;
	private String [] descomprimirDatos;
	private Usuarios usuarios;
	private UsuarioControlador controlador;
	
	public EjecucionSocket(int puerto) throws IOException {
		// TODO Auto-generated constructor stub
		serverSocket = new ServerSocket(puerto);
	}

	public void run() {
		String respuesta = "";
		while(true){
			respuesta = "";
	         try
	         {
	            System.out.println("Esperando conexion del cliente en el puerto " + serverSocket.getLocalPort() + "...");
	            Socket server = serverSocket.accept();
	            System.out.println("Conexión remota desde " + server.getRemoteSocketAddress());
	            DataInputStream in = new DataInputStream(server.getInputStream());
	            obtenerDatos = in.readUTF();
	            respuesta = descoDatos();
	            DataOutputStream out = new DataOutputStream(server.getOutputStream());
	            out.writeUTF(respuesta);
	            server.close();
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	            break;
	         }
	      }
	}
	
	
	public String descoDatos () {
		String respuesta = "";
		descomprimirDatos = obtenerDatos.split(":");
		Date fecha = new Date();
		if (descomprimirDatos[0].equals("1")) {
			usuarios = new Usuarios();
			usuarios.setNombres(descomprimirDatos[1]);
			usuarios.setEmail(descomprimirDatos[2]);
			usuarios.setFecha(fecha);
			usuarios.setUsuario(descomprimirDatos[3]);
			usuarios.setClave(descomprimirDatos[4]);
			controlador = new UsuarioControlador(usuarios);
			respuesta = controlador.ingresarDatos();
		}
		return respuesta;
	}
}
