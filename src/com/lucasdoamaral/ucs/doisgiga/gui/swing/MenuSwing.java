package com.lucasdoamaral.ucs.doisgiga.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lucasdoamaral.ucs.doisgiga.bd.PontuacaoDao;
import com.lucasdoamaral.ucs.doisgiga.gameplay.Jogo;
import com.lucasdoamaral.ucs.doisgiga.gameplay.TipoJogoEnum;
import com.lucasdoamaral.ucs.doisgiga.gameplay.intf.JogoIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.MenuIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroIntf;

public class MenuSwing implements MenuIntf {

	private static final String TITULO_JOGO = "2048";

	private JogoIntf jogo;

	private JButton botao4x4;

	private JButton botao5x5;

	private JButton botao6x6;

	private JButton botao7x7;

	private JButton botao8x8;

	private JButton botao3x3;

	private JButton botao2x2;

	// private JCheckBox checkInfinito;

	private static final Font FONTE_TITULO = new Font("Arial", Font.BOLD, 26);

	private static final Dimension TAMANHO_BOTAO = new Dimension(20, 20);

	public MenuSwing() {
		super();
	}

	@Override
	public void exibir() {

		// Cria a janela
		JFrame menu = new JFrame();
		menu.setSize(200, 200);
		menu.setVisible(true);

		// Cria o label central
		menu.setLayout(new BorderLayout());

		// Cria o label com o título
		JLabel titulo = new JLabel(TITULO_JOGO);
		titulo.setFont(FONTE_TITULO);

		menu.add(titulo, BorderLayout.NORTH);

		menu.add(getPainelBotoes(), BorderLayout.SOUTH);

		menu.pack();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLocationRelativeTo(null);

	}
	private JPanel getPainelBotoes() {

		// Cria o panel com os botões de opções
		JPanel botoes = new JPanel(new GridLayout(1, 3));

		// Botão para opção 2x2
		botao2x2 = new JButton("2x2");
		botao2x2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo2x2();
			}
		});
		botoes.add(botao2x2);
		botao2x2.setSize(TAMANHO_BOTAO);

		// Botão para opção 3x3
		botao3x3 = new JButton("3x3");
		botao3x3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo3x3();
			}
		});
		botoes.add(botao3x3);
		botao3x3.setSize(TAMANHO_BOTAO);

		// Botão para opção 4x4
		botao4x4 = new JButton("4x4");
		botao4x4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo4x4();
			}
		});
		botoes.add(botao4x4);
		botao4x4.setSize(TAMANHO_BOTAO);

		// Botão para opção 5x5
		botao5x5 = new JButton("5x5");
		botao5x5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo5x5();
			}
		});
		botoes.add(botao5x5);
		botao5x5.setSize(TAMANHO_BOTAO);

		// Botão para opção 6x6
		botao6x6 = new JButton("6x6");
		botao5x5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo6x6();
			}
		});
		botoes.add(botao6x6);
		botao5x5.setSize(TAMANHO_BOTAO);

		// Botão para opção 7x7
		botao7x7 = new JButton("7x7");
		botao7x7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo7x7();
			}
		});
		botoes.add(botao7x7);
		botao7x7.setSize(TAMANHO_BOTAO);

		// Botão para opção 8x8
		botao8x8 = new JButton("8x8");
		botao8x8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoJogo8x8();
			}
		});
		botoes.add(botao8x8);
		botao8x8.setSize(TAMANHO_BOTAO);

		JButton limparRecordes = new JButton("Limpar Recordes!");
		limparRecordes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acaoLimparRecordes();
			}
		});
		botoes.add(limparRecordes);

		return botoes;
	}

	private void acaoLimparRecordes() {
		PontuacaoDao.limparPontuacoes();
	}

	private void acaoJogo2x2() {
		jogo = new Jogo(TipoJogoEnum.DOIS_POR_DOIS);
		jogo.iniciarJogo();
	}

	private void acaoJogo3x3() {
		jogo = new Jogo(TipoJogoEnum.TRES_POR_TRES);
		jogo.iniciarJogo();
	}

	private void acaoJogo4x4() {
		jogo = new Jogo(TipoJogoEnum.QUATRO_POR_QUATRO);
		jogo.iniciarJogo();
	}

	private void acaoJogo5x5() {
		jogo = new Jogo(TipoJogoEnum.CINCO_POR_CINCO);
		jogo.iniciarJogo();
	}

	private void acaoJogo6x6() {
		jogo = new Jogo(TipoJogoEnum.SEIS_POR_SEIS);
		jogo.iniciarJogo();
	}

	private void acaoJogo7x7() {
		jogo = new Jogo(TipoJogoEnum.SETE_POR_SETE);
		jogo.iniciarJogo();
	}

	private void acaoJogo8x8() {
		jogo = new Jogo(TipoJogoEnum.OITO_POR_OITO);
		jogo.iniciarJogo();
	}

	@Override
	public void iniciarJogo() {
		if (jogo != null) {
			jogo.iniciarJogo();
		}
		System.err.println("Nenhum jogo foi definido para iniciar o jogo.");
	}

	@Override
	public boolean jogoDeveContinuar() {
		return true;
	}

	@Override
	public TabuleiroIntf getTabuleiro() {
		// FIXME Corrigir as criações de tabuleiros para que os métodos das implementações de menu façam as instanciações
		// MAS ATUALMENTE ESTÁ TUDO FUNCIONANDO.
		return null;
	}

	@Override
	public void importarJogo() {
		// TODO IMPLEMENTAR
	}
}
