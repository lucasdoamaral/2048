package com.lucasdoamaral.ucs.doisgiga.gameplay;

import com.lucasdoamaral.ucs.doisgiga.util.Util;

public class Posicao {

	private int indiceX;
	private int indiceY;

	private TipoJogoEnum tipoJogo;

	public Posicao(TipoJogoEnum tipoJogo) {
		this.tipoJogo = tipoJogo;
		gerarPosicaoRandomica();
	}

	private void gerarPosicaoRandomica() {
		indiceX = Util.getRandomBetween(1, tipoJogo.getTamanhoTabuleiro());
		indiceY = Util.getRandomBetween(1, tipoJogo.getTamanhoTabuleiro());
	}

	public Posicao(int x, int y) {
		indiceX = x;
		indiceY = y;
	}

	public int getX() {
		return indiceX;
	}

	public int getY() {
		return indiceY;
	}

	/**
	 * Gera uma nova posição aleatória.
	 */
	public void refresh() {
		gerarPosicaoRandomica();
	}

	public int getCoordenadaX() {
		return Util.definirCoordenadaX(indiceX);
	}

	public int getCoordenadaY() {
		return Util.definirCoordenadaY(indiceY);
	}

}
