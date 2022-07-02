package swing2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Scanner;

public class TesteJdbc3 {
		public static void main(String[] args) {
			try {
				String url = "jdbc:mysql://localhost/gabrielsantos"; 
				String user = "root";
				String password = ""; 
				Connection conn = DriverManager.getConnection(url, user, password);
				
				String sql = "select * from pedidos where Carrinho=?, Produto=?, nome_Produto=?";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				Scanner sc = new Scanner(System.in);
				System.out.print("Entre com o numero do carrinho = ");
				int carrinhoCompra = Integer.parseInt(sc.nextLine());
				ps.setInt(1, carrinhoCompra);	
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { 
					System.out.println("Não foram encontrados registros!");
				}
				else
				{
					int totalRegistros = 0;
					while (rs.next()) {
					    int nCarrinho = rs.getInt("Carrinho");
					    int idProduto= rs.getInt("Produto");
					    String nProduto = rs.getString("Nome Produto");
					    System.out.println("Número do registro = " + rs.getRow());
					    System.out.println("Carrinho=" + nCarrinho);
					    System.out.println("Produto=" + idProduto);
					    System.out.println("Nome do Produto=" + nProduto);
					    System.out.println();
					    totalRegistros++;
					}
					System.out.println("Total de registros = " + totalRegistros);
				}
				sc.close();
			} catch (Exception erro) {
				System.out.println("Erro: " + erro.toString());
			}
		}
}

