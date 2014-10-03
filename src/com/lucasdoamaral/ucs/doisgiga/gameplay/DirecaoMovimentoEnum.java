package com.lucasdoamaral.ucs.doisgiga.gameplay;

import java.awt.event.KeyEvent;

public enum DirecaoMovimentoEnum {

	ACIMA(38, 'W'), DIREITA(39, 'D'), ABAIXO(40, 'S'), ESQUERDA(37, 'A');

	private int codigo;
	private char tecla;

	private DirecaoMovimentoEnum(int codigo, char tecla) {
		this.codigo = codigo;
		this.tecla = tecla;
	}

	public int getCodigo() {
		return codigo;
	}

	public static DirecaoMovimentoEnum getByKeyEvent(KeyEvent event) {
		int codigo = event.getKeyCode();
		for (DirecaoMovimentoEnum direcao : DirecaoMovimentoEnum.values()) {
			if (direcao.codigo == codigo) {
				return direcao;
			}
		}
		return null;
	}

	public static DirecaoMovimentoEnum getByTecla(char tecla) {
		tecla = Character.toUpperCase(tecla);
		for (DirecaoMovimentoEnum direcao : DirecaoMovimentoEnum.values()) {
			if (direcao.tecla == tecla) {
				return direcao;
			}
		}
		return null;
	}
}
