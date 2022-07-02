package swing2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Essa classe contém informações sobre uma compra em supermercado
 * @author Saramcsantos
 *@see Swing2.Pedido
 *@see Swing2.Conexao
 */
public abstract class Produto {
	public int numeroCarrinho;
	public String nomeProduto;
	protected int codigoProduto;
	public static int quantidadePedido;

	Produto() {
		this.nomeProduto = "";
		Produto.quantidadePedido++;
	}
/**
 * 
 * @param idProduto Código do produto
 * @param nCarrinho Número do carrinho
 */
	Produto(int idProduto, int nCarrinho) {
		this();
		this.codigoProduto = idProduto;
		this.numeroCarrinho = nCarrinho;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nProduto) {
		this.nomeProduto = nProduto;
	}

	public int getNumeroCarrinho() {
		return numeroCarrinho;
	}

	public void setNumeroCarrinho(int nCarrinho) {
		this.numeroCarrinho = nCarrinho;
	}

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int idProduto) {
		this.codigoProduto = idProduto;
	}

	public boolean cadastrarPedido(int nCarrinho, int idProduto, String nProduto) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "insert into pedidos set Carrinho=?, Produto=?, nome_produto=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, nCarrinho); 
			ps.setInt(2, idProduto); 
			ps.setString(3, nProduto);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar produto " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarPedido(int nCarrinho, int idProduto) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from pedidos where Carrinho=? and Produto=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, nCarrinho); 
			ps.setInt(2, idProduto);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				return false;
			} else {
				while (rs.next()) {
					this.numeroCarrinho = rs.getInt("Carrinho");
					this.codigoProduto = rs.getInt("Produto");
					this.nomeProduto = rs.getString("nome_produto");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar pedido: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarPedido(int nCarrinho, int idProduto, String nProduto) {
		if (!consultarPedido(nCarrinho, idProduto))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "update pedidos set Carrinho=?, Produto=?, nome_produto=? where Produto=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, nCarrinho);
				ps.setInt(2, idProduto);
				ps.setString(3, nProduto);
				ps.setInt(4, idProduto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o pedido: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	public void excluirPedido(int nCarrinho, int idProduto) {
       Connection conexao = Conexao.conectaBanco();
        String sql = "delete from pedidos where Carrinho = ? and Produto= ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, nCarrinho);
            ps.setInt(2, idProduto);
            ps.execute();
            System.out.println("Excluído com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir");
        }
    }
}
