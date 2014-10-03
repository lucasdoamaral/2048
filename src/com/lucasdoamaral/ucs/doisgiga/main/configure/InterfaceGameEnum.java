package com.lucasdoamaral.ucs.doisgiga.main.configure;

/**
 * Define a interface do jogo.
 * 
 * @author Lucas
 * 
 */
public enum InterfaceGameEnum {

	SWING(1), CARACTERE(2);

	private int id;

	private InterfaceGameEnum(int id) {
		this.id = id;
	}

	public static InterfaceGameEnum getById(int id) {
		for (InterfaceGameEnum tipo : InterfaceGameEnum.values()) {
			if (tipo.id == id) {
				return tipo;
			}
		}
		return null;
	}
}
