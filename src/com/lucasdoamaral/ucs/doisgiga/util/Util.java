package com.lucasdoamaral.ucs.doisgiga.util;

import java.io.IOException;

import com.lucasdoamaral.ucs.doisgiga.main.configure.Configuration;

public class Util {

	public static int getRandomBetween(int inicio, int fim) {
		return (int) (Math.random() * 100 % fim + inicio);
	}

	public static int definirCoordenadaX(int indiceX) {
		return (indiceX - 1) * Configuration.TAMANHO_DA_PECA + Configuration.MARGEM_LATERAL;
	}

	public static int definirCoordenadaY(int indiceY) {
		return (indiceY - 1) * Configuration.TAMANHO_DA_PECA + Configuration.MARGEM_SUPERIOR;
	}

	public final static void clearConsole() {
		try {
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
