package com.lucasdoamaral.ucs.doisgiga.model;

import java.awt.Color;
import java.awt.Font;

import com.lucasdoamaral.ucs.doisgiga.gameplay.Posicao;
import com.lucasdoamaral.ucs.doisgiga.main.configure.Configuration;

public class Peca extends java.awt.geom.RoundRectangle2D.Double {

	private static final long serialVersionUID = 1L;

	/* Constantes com as configurações da peça. */
	private static final Integer CURVATURA_BORDA = Configuration.CURVA_BORDA;
	private static final Integer TAMANHO_DA_PECA = Configuration.TAMANHO_DA_PECA;

	/** Controle sobre a criação de peças iniciadas em 2 ou 4. */
	private static int pecasCriadasComDois = 0;

	private static final int VALOR_INICIAL_DOIS = 2;
	private static final int VALOR_INICIAL_QUATRO = 4;

	private int valorAtual = getValorInicial();

	private Posicao posicao;

	public Peca(Posicao posicao) {
		super(posicao.getCoordenadaX() + 1, posicao.getCoordenadaY() + 1, TAMANHO_DA_PECA - 1, TAMANHO_DA_PECA - 1, CURVATURA_BORDA, CURVATURA_BORDA);

		logarCriacao(posicao);

		this.posicao = posicao;
		init();

		// System.out.println("Peça criada com o valor inicial igual a [" + valorAtual + "].");
	}

	private void logarCriacao(Posicao p) {
		// System.out.println("Peca [" + p.getX() + "][" + p.getY() + "] Coord (" + p.getCoordenadaX() + ", " + p.getCoordenadaY() + ")");
	}

	public Color getCor() {
		ValorPecaEnum valorEnum = ValorPecaEnum.getByValor(valorAtual);
		return new Color(valorEnum.getCorFundo());
	}

	public Font getFonte() {
		ValorPecaEnum valorEnum = ValorPecaEnum.getByValor(valorAtual);
		return valorEnum.getFonte();
	}

	public void atualizar(Posicao posicao) {
		setRoundRect(posicao.getCoordenadaX() + 1, posicao.getCoordenadaY() + 1, TAMANHO_DA_PECA - 1, TAMANHO_DA_PECA - 1, CURVATURA_BORDA,
				CURVATURA_BORDA);
	}

	private void init() {
	}

	private int getValorInicial() {
		if (pecasCriadasComDois++ > 5) {
			pecasCriadasComDois = 0;
			return VALOR_INICIAL_QUATRO;
		}
		return VALOR_INICIAL_DOIS;
	}

	public int getPontuacaoAtual() {
		return valorAtual;
	}

	public void dobrarValor() {
		valorAtual *= 2;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
		atualizar(posicao);
	}

	public int getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(int valorAtual) {
		this.valorAtual = valorAtual;
	}

	@Override
	public String toString() {
		return new Integer(valorAtual).toString();
	}

}
