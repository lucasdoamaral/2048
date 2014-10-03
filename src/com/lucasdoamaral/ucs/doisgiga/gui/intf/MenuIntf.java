package com.lucasdoamaral.ucs.doisgiga.gui.intf;

public interface MenuIntf {

	public static final int JOGO_4x4 = 1;

	public static final int JOGO_5x5 = 2;

	public static final int JOGO_4x4_INFINITO = 3;

	public static final int JOGO_5X5_INFINITO = 4;

	/**
	 * Exibe o menu e retorna a opção escolhida.
	 */
	void exibir();

	void iniciarJogo();

	/**
	 * Define se o jogo deve ser finalizado.
	 */
	boolean jogoDeveContinuar();

	TabuleiroIntf getTabuleiro();

	void importarJogo();

}
