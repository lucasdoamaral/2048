package com.lucasdoamaral.ucs.doisgiga.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;

public class HsqldbServer {

	static {
		subirBanco();
		criarTabela();
	}

	private static void subirBanco() {

		System.out.println("Iniciando banco de dados.");

		// 'Server' is a class of HSQLDB representing
		// the database server
		Server hsqlServer = new Server();

		// HSQLDB prints out a lot of informations when
		// starting and closing, which we don't need now.
		// Normally you should point the setLogWriter
		// to some Writer object that could store the logs.
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);

		// The actual database will be named 'xdb' and its
		// settings and data will be stored in files
		// testdb.properties and testdb.script
		hsqlServer.setDatabaseName(0, "banco2048");
		hsqlServer.setDatabasePath(0, "c:/tmp/banco2048");
		hsqlServer.getPort();

		// Start the database!
		hsqlServer.start();

		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void criarTabela() {

		String create = "CREATE TABLE IF NOT EXISTS pontuacao (pontuacao_maxima int)";
		try {
			getConnection().createStatement().executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/banco2048", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}