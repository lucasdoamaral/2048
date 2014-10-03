package com.lucasdoamaral.ucs.doisgiga.gameplay;

public enum TipoJogoEnum {

	DOIS_POR_DOIS(2), TRES_POR_TRES(3), QUATRO_POR_QUATRO(4), CINCO_POR_CINCO(5), SEIS_POR_SEIS(6), SETE_POR_SETE(7), OITO_POR_OITO(8);

	private int tamanhoJogo;

	private TipoJogoEnum(int tamanhoTabuleiro) {
		tamanhoJogo = tamanhoTabuleiro;
	}

	public int getTamanhoTabuleiro() {
		return tamanhoJogo;
	}

	public static TipoJogoEnum getByTamanho(int tamanho) {
		for (TipoJogoEnum tipoJogo : TipoJogoEnum.values()) {
			if (tipoJogo.tamanhoJogo == tamanho) {
				return tipoJogo;
			}
		}
		return null;
	}

}
