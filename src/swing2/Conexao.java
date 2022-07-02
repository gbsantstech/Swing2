package swing2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/gabrielsantos"; 
			String user = "root"; 
			String password = ""; 
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�oo ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�oo identificado: " + erro.toString());
		} 
		return conexao;
	}
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conex�oo: " + erro.toString());
		}
	}
}

