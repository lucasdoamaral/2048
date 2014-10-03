package com.lucasdoamaral.ucs.doisgiga.main.configure;

import java.awt.Font;

/**
 * Configuração de toda a aplicação.
 * 
 * @author Lucas
 * 
 */
public class Configuration {

	public static final Integer TAMANHO_DA_PECA = 60;

	public static final Integer MARGEM_SUPERIOR = TAMANHO_DA_PECA * 3;

	public static final Integer MARGEM_LATERAL = TAMANHO_DA_PECA / 3;

	public static final Integer MARGEM_INFERIOR = TAMANHO_DA_PECA / 3 + 20;

	public static final Integer CURVA_BORDA = 10;

	public static final Font FONTE_NORMAL = new Font("Monospaced", Font.BOLD, 40);

	public static final Font FONTE_TITULO = new Font("Monospaced", Font.BOLD, 45);

	public static InterfaceGameEnum INTERFACE_JOGO = InterfaceGameEnum.CARACTERE;

}
