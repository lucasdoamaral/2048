package com.lucasdoamaral.ucs.doisgiga.model.xml;

public class PecaXml {

	private int indiceX;

	private int indiceY;

	private int pontuacao;

	public PecaXml() {
		// TODO Auto-generated constructor stub
	}

	public PecaXml(int indiceX, int indiceY, int pontuacao) {
		super();
		this.indiceX = indiceX;
		this.indiceY = indiceY;
		this.pontuacao = pontuacao;
	}

	public int getIndiceX() {
		return indiceX;
	}

	public void setIndiceX(int indiceX) {
		this.indiceX = indiceX;
	}

	public int getIndiceY() {
		return indiceY;
	}

	public void setIndiceY(int indiceY) {
		this.indiceY = indiceY;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

}
