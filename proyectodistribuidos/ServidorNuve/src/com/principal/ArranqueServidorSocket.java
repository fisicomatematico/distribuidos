package com.principal;

import java.io.IOException;

import com.socket.EjecucionSocket;

public class ArranqueServidorSocket {

	public static void main(String[] args) {
		try {
			Thread hilo= new EjecucionSocket(8888);
			hilo.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
