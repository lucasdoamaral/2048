package com.lucasdoamaral.ucs.doisgiga.model.xml;

import java.util.List;

public class JogoXml {

	private int tamanhoTabuleiro;

	private List<PecaXml> pecasXml;

	public JogoXml() {
		// TODO Auto-generated constructor stub
	}

	public JogoXml(int tamanhoTabuleiro, List<PecaXml> pecasXml) {
		this.tamanhoTabuleiro = tamanhoTabuleiro;
		this.pecasXml = pecasXml;
	}

	public int getTamanhoTabuleiro() {
		return tamanhoTabuleiro;
	}

	public void setTamanhoTabuleiro(int tamanhoTabuleiro) {
		this.tamanhoTabuleiro = tamanhoTabuleiro;
	}

	public List<PecaXml> getPecasXml() {
		return pecasXml;
	}

	public void setPecasXml(List<PecaXml> pecasXml) {
		this.pecasXml = pecasXml;
	}

}
