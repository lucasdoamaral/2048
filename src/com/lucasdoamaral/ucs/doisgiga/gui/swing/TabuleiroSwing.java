package com.lucasdoamaral.ucs.doisgiga.gui.swing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lucasdoamaral.ucs.doisgiga.bd.PontuacaoDao;
import com.lucasdoamaral.ucs.doisgiga.gameplay.DirecaoMovimentoEnum;
import com.lucasdoamaral.ucs.doisgiga.gameplay.Movimentacao;
import com.lucasdoamaral.ucs.doisgiga.gameplay.Posicao;
import com.lucasdoamaral.ucs.doisgiga.gameplay.TipoJogoEnum;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroListener;
import com.lucasdoamaral.ucs.doisgiga.main.configure.Configuration;
import com.lucasdoamaral.ucs.doisgiga.model.Peca;
import com.lucasdoamaral.ucs.doisgiga.util.Util;

public class TabuleiroSwing extends JFrame implements TabuleiroIntf {

	private static final long serialVersionUID = 1L;

	private static final boolean NAO_REDIMENSIONAVEL = false;

	private static final boolean EXIBIR = true;

	private int tamanhoDoTabuleiro;

	private Peca[][] matrizDePecas;
	protected DirecaoMovimentoEnum movimento;
	private TabuleiroListener movimentoListener;

	public TabuleiroSwing(TipoJogoEnum tipoJogo) {
		super();
		tamanhoDoTabuleiro = tipoJogo.getTamanhoTabuleiro();
		init();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics2D = (Graphics2D) g.create();
		desenharTitulo();
		desenharGrade(graphics2D);
		desenharPecas(graphics2D);
		super.validate();
	}

	private void desenharTitulo() {

		JLabel titulo = new JLabel("2048");
		titulo.setFont(Configuration.FONTE_TITULO);
		titulo.setFocusable(true);

		JButton xmlButton = new JButton("XML");
		xmlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nomeArquivo = JOptionPane.showInputDialog("Informe o nome do arquivo");
				movimentoListener.exportarParaXml(nomeArquivo);
			}
		});
		xmlButton.setVisible(true);
		add(xmlButton, BorderLayout.SOUTH);

		titulo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// Não faz nada
			}

			@Override
			public void keyReleased(KeyEvent evento) {

				DirecaoMovimentoEnum movimento = DirecaoMovimentoEnum.getByKeyEvent(evento);
				if (movimento != null) {
					TabuleiroSwing.this.movimento = movimento;
					movimentar(movimento);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// Não faz nada
			}
		});

		JPanel painelSuperior = new JPanel();
		painelSuperior.setLayout(new GridLayout(2, 1));

		painelSuperior.add(titulo);
		titulo.setFocusable(true);
		titulo.requestFocus();

		Integer pontuacaoMaxima = PontuacaoDao.buscarPontuacaoMaxima();
		JLabel pontuacao = new JLabel("Pontuação Máxima: " + (pontuacaoMaxima != null ? pontuacaoMaxima : "Nenhuma pontuação registrada."));
		painelSuperior.add(pontuacao);

		add(painelSuperior, BorderLayout.NORTH);

	}
	@Override
	public void setMovimentoListener(TabuleiroListener movimentoListener) {
		this.movimentoListener = movimentoListener;
	}

	private void gravarPontuacaoAtual() {
		BigDecimal pontos = BigDecimal.ZERO;
		for (int i = 0; i < tamanhoDoTabuleiro; i++) {
			for (int j = 0; j < tamanhoDoTabuleiro; j++) {
				if (matrizDePecas[i][j] != null) {
					pontos = pontos.add(new BigDecimal(matrizDePecas[i][j].getValorAtual()));
				}
			}
		}
		PontuacaoDao.gravarNovaPontuacao(pontos.intValue());
	}

	private void movimentar(DirecaoMovimentoEnum movimento) {

		if (!Movimentacao.get(matrizDePecas).haMovimento()) {

			gravarPontuacaoAtual();
			declararFimJogo();

		} else {

			switch (movimento) {
				case ABAIXO:
					acaoDown();
					break;

				case ACIMA:
					acaoUp();
					break;

				case DIREITA:
					acaoRight();
					break;

				case ESQUERDA:
					acaoLeft();
					break;

				default:
					break;
			}

		}
	}

	private void declararFimJogo() {
		final JDialog fimDeJogo = new JDialog(this, "FIM DE JOGO", true);
		fimDeJogo.setLayout(new BorderLayout());
		JLabel texto = new JLabel("FIM DE JOGO");
		texto.setFont(Configuration.FONTE_TITULO);
		texto.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				teclaPressionada(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				teclaPressionada(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				teclaPressionada(e);
			}

			private void teclaPressionada(KeyEvent evento) {
				if (evento.getKeyCode() == 27) {
					TabuleiroSwing.this.dispose();
					fimDeJogo.dispose();
				}
			}
		});
		fimDeJogo.add(texto, BorderLayout.CENTER);
		fimDeJogo.setSize(new Dimension(400, 100));
		fimDeJogo.setLocationRelativeTo(this);
		fimDeJogo.setModal(true);
		fimDeJogo.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		fimDeJogo.setVisible(true);
		texto.requestFocus();

		dispose();
	}

	private void acaoUp() {
		movimentoListener.moveUp();
	}

	private void acaoDown() {
		movimentoListener.moveDown();
	}

	private void acaoLeft() {
		movimentoListener.moveLeft();
	}

	private void acaoRight() {
		movimentoListener.moveRight();
	}

	private void desenharGrade(Graphics2D graphics2d2) {

		for (int y = 1; y <= tamanhoDoTabuleiro; y++) {
			for (int x = 1; x <= tamanhoDoTabuleiro; x++) {

				// Define as posições x e y
				int posicaoX = Util.definirCoordenadaX(x);
				int posicaoY = Util.definirCoordenadaY(y);

				// Desenha cada um dos quadrados do tabuleiro
				desenharPosicaoGrade(graphics2d2, posicaoX, posicaoY);
			}
		}
	}

	private void desenharPosicaoGrade(Graphics2D graphics2D, int posicaoX, int posicaoY) {
		graphics2D.draw(new Rectangle2D.Double(posicaoX, posicaoY, Configuration.TAMANHO_DA_PECA, Configuration.TAMANHO_DA_PECA));
	}

	private void desenharModeloTabuleiro() {
		// System.out.println();
		// System.out.print("_");
		// for (int i = 0; i < tamanhoDoTabuleiro; i++) {
		// System.out.print("_");
		// }
		// System.out.print("_");
		// System.out.println("");
		// for (int y = 0; y < tamanhoDoTabuleiro; y++) {
		// System.out.print("|");
		// for (int x = 0; x < tamanhoDoTabuleiro; x++) {
		// if (matrizDePecas[x][y] != null) {
		// System.out.print(matrizDePecas[x][y].getValorAtual());
		// } else {
		// System.out.print("_");
		// }
		// }
		// System.out.println("|");
		// }
		// System.out.print("_");
		// for (int i = 0; i < tamanhoDoTabuleiro; i++) {
		// System.out.print("_");
		// }
		// System.out.print("_");
		// System.out.println("");
		// System.out.println("");
	}

	private void recarregarPecas() {

		for (int i = 0; i < tamanhoDoTabuleiro; i++) {
			for (int j = 0; j < tamanhoDoTabuleiro; j++) {
				if (matrizDePecas[i][j] != null) {
					int valorAtual = matrizDePecas[i][j].getValorAtual();
					matrizDePecas[i][j] = new Peca(matrizDePecas[i][j].getPosicao());
					matrizDePecas[i][j].setValorAtual(valorAtual);
				}
			}
		}
	}

	private void desenharPecas(final Graphics2D graphics2D) {

		desenharModeloTabuleiro();

		recarregarPecas();

		desenharModeloTabuleiro();

		for (int y = 1; y <= tamanhoDoTabuleiro; y++) {
			for (int x = 1; x <= tamanhoDoTabuleiro; x++) {

				if (matrizDePecas[x - 1][y - 1] != null) {

					final int finalY = y;
					final int finalX = x;

					// System.out.println("Peca [" + finalX + "][" + finalY + "].");

					// Desenha cada uma das peças existente
					desenharPeca(graphics2D, finalX, finalY);
				}
			}
		}
	}

	@Override
	public void repaint() {
		// System.out.println("Realizando o REPAINT.");
		super.repaint();
	}

	private void desenharPeca(Graphics2D graphics2D, int indiceX, int indiceY) {

		Peca peca = matrizDePecas[indiceX - 1][indiceY - 1];

		// Desenhar borda
		BasicStroke stroke = new BasicStroke(3);
		graphics2D.setPaint(Color.BLACK);
		graphics2D.setStroke(stroke);
		graphics2D.draw(peca);

		// Desenhar peça
		graphics2D.setPaint(peca.getCor());
		graphics2D.fill(peca);

		// Desenhar número
		desenharNumero(graphics2D, peca);
	}

	private void desenharNumero(Graphics2D graphics2D, Peca peca) {

		Posicao posicaoPeca = peca.getPosicao();
		int coordenadaX = posicaoPeca.getCoordenadaX();
		int coordenadaY = peca.getPosicao().getCoordenadaY();

		int posicaoY = (int) (coordenadaY + Configuration.TAMANHO_DA_PECA / 1.5);

		graphics2D.setColor(Color.BLACK);
		// TODO lucas.amaral testar
		// graphics2D.setFont(Configuration.FONTE_NORMAL);
		graphics2D.setFont(peca.getFonte());
		graphics2D.drawString(new Integer(peca.getValorAtual()).toString(), coordenadaX, posicaoY);

	}

	@Override
	public void setPosicoes(Peca[][] matrizDePecas) {
		this.matrizDePecas = matrizDePecas;
	}

	@Override
	public void atualizarTabuleiro() {
		repaint();
	}

	private void init() {
		setSize(calcularTamanhoTabuleiro());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(NAO_REDIMENSIONAVEL);
	}

	private Dimension calcularTamanhoTabuleiro() {
		int larguraTabuleiro = Configuration.TAMANHO_DA_PECA * tamanhoDoTabuleiro + Configuration.MARGEM_LATERAL * 2;
		int alturaTabuleiro = Configuration.TAMANHO_DA_PECA * tamanhoDoTabuleiro + Configuration.MARGEM_SUPERIOR + Configuration.MARGEM_INFERIOR;
		return new Dimension(larguraTabuleiro, alturaTabuleiro);
	}

	@Override
	public void exibirTabuleiro() {
		setVisible(EXIBIR);
	}

	@Override
	public void exibirMensagemErroExportacaoXml() {
		new JOptionPane("Erro ao realizar a exportação para XML!", JOptionPane.ERROR_MESSAGE).setVisible(true);
	}

	@Override
	public void exibirMensagemSucessoExportacaoXml(String nomeArquivo) {
		new JOptionPane("Arquivo exportado com sucesso!\n[" + nomeArquivo + "]", JOptionPane.INFORMATION_MESSAGE).setVisible(true);
	}

}