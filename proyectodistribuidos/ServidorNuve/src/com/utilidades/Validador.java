package com.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

	public Boolean validarLetras(String cadena) {

		String regex = "[^A-Za-z ]";
		String Error = null;
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(cadena);
		boolean resultado = emparejador.find();
		boolean retorno = true;

		if (resultado || cadena.isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	public Boolean validarNumeros(String cadena) {

		String regex = "[^0-9]+";
		String Error = null;
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(cadena);
		boolean retorno = true;
		boolean resultado = emparejador.find();

		if (resultado || cadena.isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	public Boolean Correo(String cadena) {

		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String Error = null;
		Pattern patron = Pattern.compile(regex);
		Matcher emparejador = patron.matcher(cadena);
		boolean resultado = emparejador.find();
		boolean retorno = true;

		if (!resultado || cadena.isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	public Boolean Cedula(String cadena) {
		// Valida el formato de la cedula

		boolean cedulaCorrecta = false;

		try {
			if (cadena.length() == 10) {// ConstantesApp.LongitudCedula
				int tercerDigito = Integer.parseInt(cadena.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validaci�n c�dula
					// El decimo digito se lo considera d�gito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cadena.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cadena.length() - 1); i++) {
						digito = Integer.parseInt(cadena.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
		}
		return cedulaCorrecta;
	}

	public boolean validarRUC(String cadena) {

		if (cadena.isEmpty()) {
			return false;
		} else {

			int tipo = Integer.parseInt(cadena.substring(2, 3));
			boolean isValid = false;

			if (tipo < 6 && (cadena.length() == 13)) { // Tipo de RUC persona Natural

				if (cadena == null || cadena.length() != 10) {
					isValid = false;
				}
				final int prov = Integer.parseInt(cadena.substring(0, 2));

				if (!((prov > 0) && (prov <= 24))) {
					isValid = false;
				}

				int[] d = new int[10];
				for (int i = 0; i < d.length; i++) {
					d[i] = Integer.parseInt(cadena.charAt(i) + "");
				}

				int imp = 0;
				int par = 0;

				for (int i = 0; i < d.length; i += 2) {
					d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
					imp += d[i];
				}

				for (int i = 1; i < (d.length - 1); i += 2) {
					par += d[i];
				}

				final int suma = imp + par;

				int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;

				d10 = (d10 == 10) ? 0 : d10;

				if (d10 == d[9]) {
					isValid = true;
				} else {
					isValid = false;
				}

			} else if (tipo == 9 && (cadena.length() == 13)) { // RUC JURIDICOS Y EXTRANJEROS SIN CEDULA

				int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
				isValid = false;
				int constante = 11;
				final int prov = Integer.parseInt(cadena.substring(0, 2));
				if (!((prov > 0) && (prov <= 24))) {
					isValid = false;
				}

				int[] d = new int[10];
				int suma = 0;

				for (int i = 0; i < d.length; i++) {
					d[i] = Integer.parseInt(cadena.charAt(i) + "");
				}

				for (int i = 0; i < d.length - 1; i++) {
					d[i] = d[i] * coeficientes[i];
					suma += d[i];
				}

				int aux, resp;

				aux = suma % constante;
				resp = constante - aux;

				resp = (aux == 0) ? 0 : resp;

				if (resp == d[9]) {
					isValid = true;
				} else {
					isValid = false;
				}
			} else if (tipo == 6 && (cadena.length() == 13)) { // RUC EMPRESAS PUBLICAS
				isValid = false;
				final int prov = Integer.parseInt(cadena.substring(0, 2));

				if (!((prov > 0) && (prov <= 24))) {
					isValid = false;
				}

				// boolean val = false;
				Integer v1, v2, v3, v4, v5, v6, v7, v8, v9;
				Integer sumatoria;
				Integer modulo;
				Integer digito;
				int[] d = new int[cadena.length()];

				for (int i = 0; i < d.length; i++) {
					d[i] = Integer.parseInt(cadena.charAt(i) + "");
				}

				v1 = d[0] * 3;
				v2 = d[1] * 2;
				v3 = d[2] * 7;
				v4 = d[3] * 6;
				v5 = d[4] * 5;
				v6 = d[5] * 4;
				v7 = d[6] * 3;
				v8 = d[7] * 2;
				v9 = d[8];

				sumatoria = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
				modulo = sumatoria % 11;
				if (modulo == 0) {
					isValid = true;
				}

				if (!isValid) {
					digito = 11 - modulo;

					if (digito.equals(v9)) {
						isValid = true;
					} else {
						isValid = false;
					}
				}
			}

			return isValid;
		}
	}

}
