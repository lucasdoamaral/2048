package com.lucasdoamaral.ucs.doisgiga.gameplay.intf;

import com.lucasdoamaral.ucs.doisgiga.gameplay.TipoJogoEnum;

public interface JogoIntf {

	void iniciarJogo();

	void setTipoJogo(TipoJogoEnum tipoJogo);

	void exportarParaXML(String nomeArquivoDestino);

}
