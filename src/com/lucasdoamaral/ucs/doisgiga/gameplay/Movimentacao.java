package com.lucasdoamaral.ucs.doisgiga.gameplay;

import com.lucasdoamaral.ucs.doisgiga.model.Peca;

public class Movimentacao {

	private Peca[][] pecas;

	private int tamanho;

	private boolean moveu;

	private Movimentacao(Peca[][] pecas) {
		this.pecas = pecas;
		tamanho = pecas.length;
	}

	public static Movimentacao get(Peca[][] pecas) {
		return new Movimentacao(pecas);
	}

	public boolean haMovimento() {
		if (haPosicaoVazia()) {
			return true;
		}
		boolean temMovimentoAbaixo = movimentar(DirecaoMovimentoEnum.ABAIXO, true);
		boolean temMovimentoAcima = movimentar(DirecaoMovimentoEnum.ACIMA, true);
		boolean temMovimentoEsquerda = movimentar(DirecaoMovimentoEnum.ESQUERDA, true);
		boolean temMovimentoDireita = movimentar(DirecaoMovimentoEnum.DIREITA, true);
		return temMovimentoAbaixo || temMovimentoAcima || temMovimentoEsquerda || temMovimentoDireita;
	}

	private boolean haPosicaoVazia() {
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				if (pecas[i][j] == null) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean movimentar(DirecaoMovimentoEnum movimento) {
		return movimentar(movimento, false);
	}

	private boolean movimentar(DirecaoMovimentoEnum movimento, boolean ehTeste) {

		moveu = false;

		for (int i = 0; i < 4; i++) {
			switch (movimento) {
				case ABAIXO:
					movimentarParaBaixo(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case ACIMA:
					movimentarParaCima(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case DIREITA:
					movimentarParaDireita(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case ESQUERDA:
					movimentarParaEsquerda(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				default:
					break;
			}
		}

		switch (movimento) {
			case ABAIXO:
				movimentarParaBaixo(TipoMovimentoEnum.MERGE, ehTeste);
				break;
			case ACIMA:
				movimentarParaCima(TipoMovimentoEnum.MERGE, ehTeste);
				break;
			case DIREITA:
				movimentarParaDireita(TipoMovimentoEnum.MERGE, ehTeste);
				break;
			case ESQUERDA:
				movimentarParaEsquerda(TipoMovimentoEnum.MERGE, ehTeste);
				break;
			default:
				break;
		}
		for (int i = 0; i < 4; i++) {
			switch (movimento) {
				case ABAIXO:
					movimentarParaBaixo(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case ACIMA:
					movimentarParaCima(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case DIREITA:
					movimentarParaDireita(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				case ESQUERDA:
					movimentarParaEsquerda(TipoMovimentoEnum.MOVE, ehTeste);
					break;
				default:
					break;
			}
		}

		return moveu;

	}

	private void tryMerge(int origemX, int origemY, int destinoX, int destinoY, boolean ehTeste) {
		if (destinoX >= 0 && destinoX < tamanho && destinoY >= 0 && destinoY < tamanho) {
			Peca destino = pecas[destinoX][destinoY];
			Peca origem = pecas[origemX][origemY];
			if (origem != null && destino != null) {
				if (origem.getValorAtual() == destino.getValorAtual()) {
					if (!ehTeste) {
						pecas[origemX][origemY] = null;
						destino.dobrarValor();
					}
					moveu = true;
				}
			}
		}
	}

	private void tryMove(int origemX, int origemY, int destinoX, int destinoY, boolean ehTeste) {
		if (destinoX >= 0 && destinoX < tamanho && destinoY >= 0 && destinoY < tamanho) {
			Peca destino = pecas[destinoX][destinoY];
			Peca origem = pecas[origemX][origemY];
			if (destino == null && origem != null) {
				if (!ehTeste) {
					pecas[origemX][origemY] = null;
					pecas[destinoX][destinoY] = origem;
					origem.setPosicao(new Posicao(destinoX + 1, destinoY + 1));
				}
				moveu = true;
			}
		}
	}

	private void tryMergeOrMove(int origemX, int origemY, int destinoX, int destinoY, TipoMovimentoEnum tipo, boolean ehTeste) {
		if (TipoMovimentoEnum.MOVE.equals(tipo)) {
			tryMove(origemX, origemY, destinoX, destinoY, ehTeste);
		} else {
			tryMerge(origemX, origemY, destinoX, destinoY, ehTeste);
		}
	}

	public void movimentarParaDireita(TipoMovimentoEnum tipo, boolean ehTeste) {
		for (int y = 0; y < tamanho; y++) {
			for (int x = 0; x < tamanho; x++) {
				tryMergeOrMove(x, y, x + 1, y, tipo, ehTeste);
			}
		}
	}

	public void movimentarParaBaixo(TipoMovimentoEnum tipo, boolean ehTeste) {
		for (int x = 0; x < tamanho; x++) {
			for (int y = tamanho - 1; y >= 0; y--) {
				tryMergeOrMove(x, y, x, y + 1, tipo, ehTeste);
			}
		}
	}

	public void movimentarParaEsquerda(TipoMovimentoEnum tipo, boolean ehTeste) {
		for (int y = 0; y < tamanho; y++) {
			for (int x = 0; x < tamanho; x++) {
				tryMergeOrMove(x, y, x - 1, y, tipo, ehTeste);
			}
		}
	}

	public void movimentarParaCima(TipoMovimentoEnum tipo, boolean ehTeste) {
		for (int x = 0; x < tamanho; x++) {
			for (int y = 0; y < tamanho; y++) {
				tryMergeOrMove(x, y, x, y - 1, tipo, ehTeste);
			}
		}
	}

	private enum TipoMovimentoEnum {
		MOVE, MERGE;
	}

}
