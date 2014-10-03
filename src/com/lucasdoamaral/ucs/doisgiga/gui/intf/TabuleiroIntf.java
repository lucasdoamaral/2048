package com.lucasdoamaral.ucs.doisgiga.gui.intf;

import com.lucasdoamaral.ucs.doisgiga.model.Peca;

public interface TabuleiroIntf {

	void exibirTabuleiro();

	void setPosicoes(Peca[][] matrizDePecas);

	void atualizarTabuleiro();

	void setMovimentoListener(TabuleiroListener movimentoListener);

	void exibirMensagemErroExportacaoXml();

	void exibirMensagemSucessoExportacaoXml(String caminhoArquivoCriado);

}
