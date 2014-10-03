package com.lucasdoamaral.ucs.doisgiga.gui.terminal;

import java.util.Scanner;

import com.lucasdoamaral.ucs.doisgiga.gameplay.Jogo;
import com.lucasdoamaral.ucs.doisgiga.gameplay.TipoJogoEnum;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.MenuIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroIntf;

public class MenuTerminal implements MenuIntf {

	private Jogo jogo;
	private TipoJogoEnum tipoJogo;

	public MenuTerminal() {

	}

	@Override
	public void exibir() {

		int jogoEscolhido = 0;
		while ((jogoEscolhido < 2 || jogoEscolhido > 4) && jogoEscolhido != 9) {
			exibirMenu();
			jogoEscolhido = new Scanner(System.in).nextInt();
		}

		tipoJogo = getTipoJogoByOpcao(jogoEscolhido);
		if (tipoJogo == null) {

			// Importar jogo
			System.out.println("Informe o nome completo do arquivo:");
			String nomeArquivo = new Scanner(System.in).nextLine();
			jogo = new Jogo(TipoJogoEnum.DOIS_POR_DOIS);
			jogo.importarJogo(nomeArquivo);
		}

		// Cria o jogo
		jogo = new Jogo(tipoJogo);
		jogo.iniciarJogo();

	}

	private TipoJogoEnum getTipoJogoByOpcao(int opcao) {
		if (opcao >= 1 && opcao <= 4) {
			return TipoJogoEnum.getByTamanho(opcao);
		}
		return null;
	}

	private void exibirMenu() {
		StringBuilder mensagemMenu = new StringBuilder();

		mensagemMenu.append("Informe o tipo de jogo:\n");
		mensagemMenu.append("\n");
		mensagemMenu.append("[2] 2x2 \n");
		mensagemMenu.append("[3] 3x3 \n");
		mensagemMenu.append("[4] 4x4 \n");
		mensagemMenu.append("[9] importar arquivo XML \n");

		System.out.println(mensagemMenu);
	}

	@Override
	public void iniciarJogo() {
		jogo.iniciarJogo();
	}

	@Override
	public boolean jogoDeveContinuar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TabuleiroIntf getTabuleiro() {
		return new TabuleiroTerminal(tipoJogo);
	}

	@Override
	public void importarJogo() {
		// IMPLEMENTAR
	}

}
