package com.lucasdoamaral.ucs.doisgiga.model;

import java.awt.Font;

import com.lucasdoamaral.ucs.doisgiga.main.configure.Colors;

public enum ValorPecaEnum {

	PECA_2(2, Colors._2), PECA_4(4, Colors._4), PECA_8(8, Colors._8), PECA_16(16, Colors._16), PECA_32(32, Colors._32), PECA_64(64, Colors._64), PECA_128(
			128, Colors._128), PECA_256(256, Colors._256), PECA_512(512, Colors._512), PECA_1024(1024, Colors._1024), PECA_2048(2048, Colors._2048);

	private int corFundo;
	private int valor;

	private ValorPecaEnum(int valor, int corFundo) {
		this.valor = valor;
		this.corFundo = corFundo;
	}

	public int getCorFundo() {
		return corFundo;
	}

	/**
	 * Cria uma fonte e define a cor da fonte.
	 */
	public Font getFonte() {
		if (valor < 128) {
			return new Font("Monospaced", Font.BOLD, 40);
		}
		if (valor < 1024) {
			return new Font("Monospaced", Font.BOLD, 30);
		}
		return new Font("Monospaced", Font.BOLD, 20);
	}

	public static ValorPecaEnum getByValor(Integer valor) {
		if (valor != null) {
			for (ValorPecaEnum estilo : ValorPecaEnum.values()) {
				if (valor.equals(estilo.valor)) {
					return estilo;
				}
			}
		}
		return null;
	}

}
