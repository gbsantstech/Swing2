package swing2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TesteSwing3 {
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Cadastro de Pedido"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(400, 300); 
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		
		JLabel labelnCarrinho = new JLabel("Carrinho: ");
		JLabel labelidProduto = new JLabel("Produto: ");
		JLabel labelnProduto = new JLabel("Nome Produto: ");
		
		labelnCarrinho.setBounds(50, 40, 100, 20); 
		labelidProduto.setBounds(50, 80, 150, 20); 
		labelnProduto.setBounds(50, 120, 100, 20); 
		
		JTextField jTextnCarrinho = new JTextField();
		JTextField jTextidProduto = new JTextField();
		JTextField jTextnProduto= new JTextField();
		
		jTextnCarrinho.setEnabled(true);
		jTextidProduto.setEnabled(true);
		jTextnProduto.setEnabled(false);
		
		jTextnCarrinho.setBounds(180, 40, 50, 20);
		jTextidProduto.setBounds(180, 80, 50, 20);
		jTextnProduto.setBounds(180, 120, 150, 20);
		
		janela.add(labelnCarrinho);
		janela.add(labelidProduto);
		janela.add(labelnProduto);
		janela.add(jTextnCarrinho);
		janela.add(jTextidProduto);
		janela.add(jTextnProduto);
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(155, 200, 100, 20);
        janela.add(botaoExcluir);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janela.add(botaoLimpar);
		Pedido Produto = new Pedido();
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int nCarrinho = Integer.parseInt(jTextnCarrinho.getText());
					int idProduto = Integer.parseInt(jTextidProduto.getText());
					botaoGravar.setEnabled(true);
					String nProduto;
					if (!Produto.consultarPedido(nCarrinho, idProduto))
						nProduto = "";
					else
						nProduto = Produto.getNomeProduto();
					jTextnProduto.setText(nProduto);
					jTextnCarrinho.setEnabled(false);
					jTextidProduto.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextnProduto.setEnabled(true);
					jTextnProduto.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Preencha os campos carrinho e número produto corretamente!!");
				}
			}
		});
		botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nCarrinho = Integer.parseInt(jTextnCarrinho.getText());
                int idProduto= Integer.parseInt(jTextidProduto.getText());
                Produto.excluirPedido(nCarrinho, idProduto);
                jTextnCarrinho.setText("");
                jTextidProduto.setText("");
                jTextnProduto.setText("");
            }
        });
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nCarrinho= Integer.parseInt(jTextnCarrinho.getText());
				int idProduto= Integer.parseInt(jTextidProduto.getText());
				String nProduto = jTextnProduto.getText().trim(); 
				if (nProduto.length()==0) {
					JOptionPane.showMessageDialog(janela, "Preencha o campo Nome Produto");
					jTextnProduto.requestFocus();
				}
				else {
					if (!Produto.consultarPedido(nCarrinho, idProduto)) {
						if (!Produto.cadastrarPedido(nCarrinho, idProduto, nProduto))
							JOptionPane.showMessageDialog(janela, "Erro na inclusão do produto!");
						else
							JOptionPane.showMessageDialog(janela, "Inclusão realizada!");
					} else {
						if (!Produto.atualizarPedido(nCarrinho, idProduto, nProduto))
							JOptionPane.showMessageDialog(janela, "Erro na atualização do produto!");
						else
							JOptionPane.showMessageDialog(janela, "Alteração realizada!");
					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextnCarrinho.setText("");
				jTextidProduto.setText(""); 
				jTextnProduto.setText(""); 
				jTextnCarrinho.setEnabled(true);
				jTextidProduto.setEnabled(true);
				jTextnProduto.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextnCarrinho.requestFocus(); 
			}
		});
		janela.setVisible(true); 
	}
}
