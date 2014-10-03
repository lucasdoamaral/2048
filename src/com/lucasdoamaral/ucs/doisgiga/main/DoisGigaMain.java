package com.lucasdoamaral.ucs.doisgiga.main;

import java.util.Scanner;

import com.lucasdoamaral.ucs.doisgiga.gui.intf.MenuIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.swing.MenuSwing;
import com.lucasdoamaral.ucs.doisgiga.gui.terminal.MenuTerminal;
import com.lucasdoamaral.ucs.doisgiga.main.configure.Configuration;
import com.lucasdoamaral.ucs.doisgiga.main.configure.InterfaceGameEnum;

public class DoisGigaMain {

	/**
	 * Método main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DoisGigaMain();
	}

	/**
	 * Construtor
	 */
	public DoisGigaMain() {

		definirTipoInterface();

		exibirMenu();

	}

	private void definirTipoInterface() {

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("*** 2048 ***\n");
		mensagem.append("\n");
		mensagem.append("Informe a interface desejada:\n");
		mensagem.append("\n");
		mensagem.append("[1] Interface Gráfica\n");
		mensagem.append("[2] Interface Caracter\n");

		System.out.println(mensagem);

		InterfaceGameEnum tipoInterface = null;
		while (tipoInterface == null) {
			@SuppressWarnings("resource")
			int opcao = new Scanner(System.in).nextInt();
			tipoInterface = InterfaceGameEnum.getById(opcao);
		}

		// FIXME Verificar se posso fechar o Scanner sem dar erro

		Configuration.INTERFACE_JOGO = tipoInterface;

		if (InterfaceGameEnum.SWING.equals(tipoInterface)) {
			System.out.println("NAO FECHE essa janela.");
		}

	}

	private void exibirMenu() {
		// Cria o menu
		final MenuIntf menu = getMenu();

		// Cria o processo para iniciar o jogo
		Thread processsoJogo = new Thread(new Runnable() {

			@Override
			public void run() {
				menu.exibir();
			}
		});

		// Inicia o processo do jogo
		processsoJogo.run();
	}

	/**
	 * Cria uma instância do menu definido.
	 * 
	 * @return
	 */
	private MenuIntf getMenu() {
		switch (Configuration.INTERFACE_JOGO) {
			case CARACTERE:
				return new MenuTerminal();
			case SWING:
				return new MenuSwing();
		}
		return null;
	}
}
