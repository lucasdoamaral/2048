package com.lucasdoamaral.ucs.doisgiga.gui.terminal;

import java.util.Scanner;

import com.lucasdoamaral.ucs.doisgiga.gameplay.DirecaoMovimentoEnum;
import com.lucasdoamaral.ucs.doisgiga.gameplay.TipoJogoEnum;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroListener;
import com.lucasdoamaral.ucs.doisgiga.model.Peca;

/**
 * Tabuleiro em interface caractere.<br />
 * O tamanho máximo para esse tabuleiro é de 4x4, devido às limitações de espaçamento de caractere.
 * 
 * @author Lucas
 * 
 */
public class TabuleiroTerminal implements TabuleiroIntf {

	private TabuleiroListener movimentoListener;
	private Peca[][] matrizDePecas;
	private int tamanhoTabuleiro;

	private boolean primeiraVez = true;

	public TabuleiroTerminal(TipoJogoEnum tipoJogo) {

		if (tipoJogo != null && tipoJogo.getTamanhoTabuleiro() > 4) {
			throw new RuntimeException("O tabuleiro terminal pode ser jogado até o tamanho 4x4.");
		}
		tamanhoTabuleiro = tipoJogo.getTamanhoTabuleiro();
	}

	@Override
	public void exibirTabuleiro() {

		if (primeiraVez) {
			desenharTabuleiro();
			primeiraVez = false;
		}

		exibirInstrucoes();

		while (true) {
			DirecaoMovimentoEnum movimento = esperarLeitura();
			if (movimento != null) {
				movimentar(movimento);
			}
		}
	}
	private void movimentar(DirecaoMovimentoEnum movimento) {
		switch (movimento) {
			case ABAIXO:
				movimentoListener.moveDown();
				break;
			case ACIMA:
				movimentoListener.moveUp();
				break;
			case DIREITA:
				movimentoListener.moveRight();
				break;
			case ESQUERDA:
				movimentoListener.moveLeft();
				break;
		}
	}

	private DirecaoMovimentoEnum esperarLeitura() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		DirecaoMovimentoEnum movimento = null;

		while (movimento == null) {
			char tecla = 0;
			String leitura = scanner.nextLine();
			if (leitura != null && !leitura.isEmpty()) {
				tecla = leitura.toCharArray()[0];
			}
			movimento = DirecaoMovimentoEnum.getByTecla(tecla);

			if (movimento == null) {
				if ("xml".equals(leitura.trim().toLowerCase())) {
					acaoExportar();
				}
			}
		}

		// FIXME Verificar se posso fechar o Scanner sem dar erro

		return movimento;
	}

	private void exibirInstrucoes() {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Utilize as setas do teclado para movimentar as peças.\n");
		mensagem.append("  [W]\n");
		mensagem.append("[A][S][D]\n");
		mensagem.append("\n");
		mensagem.append("[xml] para exportar o tabuleiro");
		System.out.println(mensagem);
	}

	private void acaoExportar() {
		System.out.println("Informe o nome do arquivo para exportacao:");
		String nomeArquivo = new Scanner(System.in).nextLine();
		movimentoListener.exportarParaXml(nomeArquivo);
	}

	private void desenharTabuleiro() {

		// Barra Superior
		System.out.print("+");
		for (int i = 0; i < tamanhoTabuleiro; i++) {
			System.out.print("---");
		}
		System.out.println("+");

		// Centro do Tabuleiro
		for (int y = 0; y < tamanhoTabuleiro; y++) {
			System.out.print("|");
			for (int x = 0; x < tamanhoTabuleiro; x++) {
				Peca peca = matrizDePecas[x][y];
				if (peca != null) {
					System.out.print(paddingLeft(peca.getValorAtual(), 3, ' '));
				} else {
					System.out.print("   ");
				}
			}
			System.out.println("|");
		}

		// Barra Inferior
		System.out.print("+");
		for (int i = 0; i < tamanhoTabuleiro; i++) {
			System.out.print("---");
		}
		System.out.println("+");
		System.out.println();

	}

	private static String paddingLeft(int valor, int numeroCaracteres, char preencher) {
		String valorEmString = new Integer(valor).toString();
		while (valorEmString.length() < numeroCaracteres) {
			valorEmString = preencher + valorEmString;
		}
		return valorEmString;
	}

	@Override
	public void setPosicoes(Peca[][] matrizDePecas) {
		this.matrizDePecas = matrizDePecas;
	}

	@Override
	public void atualizarTabuleiro() {
		desenharTabuleiro();
	}

	@Override
	public void setMovimentoListener(TabuleiroListener movimentoListener) {
		this.movimentoListener = movimentoListener;
	}

	@Override
	public void exibirMensagemErroExportacaoXml() {
		System.out.println("Erro ao realizar a exportação para XML.");
	}

	@Override
	public void exibirMensagemSucessoExportacaoXml(String arquivoGerado) {
		System.out.println("Arquivo exportado com sucesso!\n[" + arquivoGerado + "]");
	}

}
