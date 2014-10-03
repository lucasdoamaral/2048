package com.lucasdoamaral.ucs.doisgiga.util;

import java.util.ArrayList;
import java.util.List;

import com.lucasdoamaral.ucs.doisgiga.gameplay.Posicao;
import com.lucasdoamaral.ucs.doisgiga.model.Peca;
import com.lucasdoamaral.ucs.doisgiga.model.xml.JogoXml;
import com.lucasdoamaral.ucs.doisgiga.model.xml.PecaXml;

public class XmlUtil {

	public static JogoXml criarEstruturaJogo(Peca[][] pecas) {

		List<PecaXml> pecasXml = new ArrayList<>();
		JogoXml jogo = new JogoXml();

		int tamanhoTabuleiro = pecas.length;

		for (int j = 0; j < tamanhoTabuleiro; j++) {
			for (int i = 0; i < pecas[j].length; i++) {
				Peca peca = pecas[i][j];
				int pontuacao = peca != null ? peca.getPontuacaoAtual() : 0;
				pecasXml.add(new PecaXml(i, j, pontuacao));
			}
		}

		jogo.setTamanhoTabuleiro(tamanhoTabuleiro);
		jogo.setPecasXml(pecasXml);

		return jogo;
	}

	public static Peca[][] extrairPecas(JogoXml jogo) {
		int tamanho = jogo.getTamanhoTabuleiro();
		Peca[][] pecas = new Peca[tamanho][tamanho];
		for (PecaXml pecaXml : jogo.getPecasXml()) {
			if (pecaXml.getPontuacao() != 0) {
				Peca peca = new Peca(new Posicao(pecaXml.getIndiceX(), pecaXml.getIndiceY()));
				peca.setValorAtual(pecaXml.getPontuacao());
				pecas[pecaXml.getIndiceX()][pecaXml.getIndiceY()] = peca;
			}
		}
		return pecas;
	}

}
