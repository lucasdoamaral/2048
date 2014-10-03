package com.lucasdoamaral.ucs.doisgiga.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PontuacaoDao {

	public static void gravarNovaPontuacao(int pontuacao) {

		Integer pontuacaoMaxima = buscarPontuacaoMaxima();

		if (pontuacao > pontuacaoMaxima) {
			try {
				String insert = "INSERT INTO pontuacao (pontuacao_maxima) VALUES (" + pontuacao + ")";
				HsqldbServer.getConnection().createStatement().executeUpdate(insert);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void limparPontuacoes() {
		try {
			String insert = "DELETE FROM pontuacao;";
			HsqldbServer.getConnection().createStatement().executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Integer buscarPontuacaoMaxima() {

		String select = "SELECT MAX(pontuacao_maxima) AS maximo FROM pontuacao";
		ResultSet result;
		try {
			result = HsqldbServer.getConnection().createStatement().executeQuery(select);
			if (result.next()) {
				return result.getInt("maximo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

}
