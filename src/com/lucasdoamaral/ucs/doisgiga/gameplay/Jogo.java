package com.lucasdoamaral.ucs.doisgiga.gameplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lucasdoamaral.ucs.doisgiga.bd.PontuacaoDao;
import com.lucasdoamaral.ucs.doisgiga.gameplay.intf.JogoIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroIntf;
import com.lucasdoamaral.ucs.doisgiga.gui.intf.TabuleiroListener;
import com.lucasdoamaral.ucs.doisgiga.gui.swing.TabuleiroSwing;
import com.lucasdoamaral.ucs.doisgiga.gui.terminal.TabuleiroTerminal;
import com.lucasdoamaral.ucs.doisgiga.main.configure.Configuration;
import com.lucasdoamaral.ucs.doisgiga.main.configure.InterfaceGameEnum;
import com.lucasdoamaral.ucs.doisgiga.model.Peca;
import com.lucasdoamaral.ucs.doisgiga.model.xml.JogoXml;
import com.lucasdoamaral.ucs.doisgiga.model.xml.PecaXml;
import com.lucasdoamaral.ucs.doisgiga.util.XmlUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Jogo implements JogoIntf {

	private TabuleiroIntf tabuleiro;

	private Peca[][] matrizDePecas;

	private TipoJogoEnum tipoJogo;

	private int tamanhoDoTabuleiro;

	private XStream xstream;

	public Jogo(TipoJogoEnum tipoJogo) {
		this.tipoJogo = tipoJogo;
		inicializarXStream();
	}

	@Override
	public void setTipoJogo(TipoJogoEnum tipoJogo) {
		this.tipoJogo = tipoJogo;
	}

	@Override
	public void iniciarJogo() {
		if (tipoJogo != null) {
			init();
		}
	}

	private void init() {

		PontuacaoDao.gravarNovaPontuacao(0);

		tamanhoDoTabuleiro = tipoJogo.getTamanhoTabuleiro();
		tabuleiro = getTabuleiro();

		matrizDePecas = new Peca[tamanhoDoTabuleiro][tamanhoDoTabuleiro];

		tabuleiro.setPosicoes(matrizDePecas);

		criarPrimeirasPeca();

		setListenerMovimentacao();

		tabuleiro.exibirTabuleiro();

	}

	private TabuleiroIntf getTabuleiro() {
		switch (Configuration.INTERFACE_JOGO) {
			case CARACTERE:
				return new TabuleiroTerminal(tipoJogo);
			case SWING:
				return new TabuleiroSwing(tipoJogo);
		}
		return null;
	}

	private void setListenerMovimentacao() {

		tabuleiro.setMovimentoListener(new TabuleiroListener() {

			@Override
			public void moveUp() {
				boolean moveu = Movimentacao.get(matrizDePecas).movimentar(DirecaoMovimentoEnum.ACIMA);
				if (moveu) {
					criarNovaPeca();
					atualizarTabuleiro();
				}
			}

			@Override
			public void moveRight() {
				boolean moveu = Movimentacao.get(matrizDePecas).movimentar(DirecaoMovimentoEnum.DIREITA);
				if (moveu) {
					criarNovaPeca();
					atualizarTabuleiro();
				}
			}

			@Override
			public void moveLeft() {
				boolean moveu = Movimentacao.get(matrizDePecas).movimentar(DirecaoMovimentoEnum.ESQUERDA);
				if (moveu) {
					criarNovaPeca();
					atualizarTabuleiro();
				}
			}

			@Override
			public void moveDown() {
				boolean moveu = Movimentacao.get(matrizDePecas).movimentar(DirecaoMovimentoEnum.ABAIXO);
				if (moveu) {
					criarNovaPeca();
					atualizarTabuleiro();
				}
			}

			@Override
			public void exportarParaXml(String arquivoDestino) {
				Jogo.this.exportarParaXML(arquivoDestino);
			}

		});
	}
	private void criarPrimeirasPeca() {
		criarNovaPeca();
	}

	@SuppressWarnings("unused")
	private void criarTodasPecas() {
		int quantidadePecasCriadas = 0;
		while (quantidadePecasCriadas < tamanhoDoTabuleiro * tamanhoDoTabuleiro) {
			criarNovaPeca();
			quantidadePecasCriadas++;
		}
	}

	private Peca criarNovaPeca() {
		Posicao posicao = criarPosicaoNaoUtilizada();
		Peca peca = new Peca(posicao);
		posicionarPeca(peca);
		if (Configuration.INTERFACE_JOGO.equals(InterfaceGameEnum.SWING)) {
			atualizarTabuleiro();
		}
		return peca;
	}

	private void posicionarPeca(Peca peca) {

		int x = peca.getPosicao().getX();
		int y = peca.getPosicao().getY();

		// System.out.println("Posicionando a peça na posição (x, y) = (" + x + ", " + y + ").");

		matrizDePecas[x - 1][y - 1] = peca;
	}

	private Posicao criarPosicaoNaoUtilizada() {
		Posicao posicao = new Posicao(tipoJogo);
		int x = posicao.getX();
		int y = posicao.getY();
		return matrizDePecas[x - 1][y - 1] == null ? posicao : criarPosicaoNaoUtilizada();
	}

	private void atualizarTabuleiro() {
		tabuleiro.setPosicoes(matrizDePecas);
		tabuleiro.atualizarTabuleiro();
	}

	@Override
	public void exportarParaXML(String arquivoDestino) {

		JogoXml jogoEstruturado = XmlUtil.criarEstruturaJogo(matrizDePecas);

		String nomeArquivoGerado = "";

		try {
			File diretorioAtual = new File(".").getCanonicalFile();
			nomeArquivoGerado = diretorioAtual + File.separator + arquivoDestino + ".xml";
			File arquivo = new File(nomeArquivoGerado);
			xstream.toXML(jogoEstruturado, new FileOutputStream(arquivo));
		} catch (FileNotFoundException e) {
			tabuleiro.exibirMensagemErroExportacaoXml();
		} catch (IOException e) {
			tabuleiro.exibirMensagemErroExportacaoXml();
		}
		tabuleiro.exibirMensagemSucessoExportacaoXml(nomeArquivoGerado);

	}

	private XStream inicializarXStream() {
		xstream = new XStream(new StaxDriver());
		xstream.alias("jogo", JogoXml.class);
		xstream.alias("peca", PecaXml.class);
		return xstream;
	}

	public void importarJogo(String nomeCompletoArquivo) {
		nomeCompletoArquivo = nomeCompletoArquivo.contains(".xml") ? nomeCompletoArquivo : nomeCompletoArquivo + ".xml";
		iniciarJogoImportado((JogoXml) xstream.fromXML(new File(nomeCompletoArquivo)));
	}

	public void iniciarJogoImportado(JogoXml jogo) {

		PontuacaoDao.gravarNovaPontuacao(0);

		tamanhoDoTabuleiro = jogo.getTamanhoTabuleiro();
		tabuleiro = getTabuleiro();

		matrizDePecas = new Peca[tamanhoDoTabuleiro][tamanhoDoTabuleiro];

		Peca[][] pecasExtraidas = XmlUtil.extrairPecas(jogo);

		tabuleiro.setPosicoes(pecasExtraidas);

		setListenerMovimentacao();

		tabuleiro.exibirTabuleiro();

	}

}
