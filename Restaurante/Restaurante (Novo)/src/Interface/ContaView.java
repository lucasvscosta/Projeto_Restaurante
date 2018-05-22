package Interface;

import java.awt.FlowLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ContaView extends JFrame{

	private JTable 		table;
	
	private JFrame 		frame;
	
	private JPanel 		panel;
	
	private JLabel 		lblPedido, lblPagamento;
	
	private JTextField 	txtPedido, txtPagamento;
	
	private JButton 	btnOk, btnCancelar, btnExtrato;
	
	private JRadioButton rbtCredito,rbtDebito, rbtDinheiro;	
	
	private JScrollPane scroll;
	
	private ResourceBundle bn=null;
	
	public ContaView(ResourceBundle bundle){		
		bn = bundle; 
	}
	
	public void fecharContaView(){
		Object [][] data = {
				{" "," "," "," "},
				{" "," "," "," "},
				{" "," "," "," "},
		};
		
		String [] coluna = 
			{	bn.getString("columnProduto"),
				bn.getString("columnPrecoUnitario"), 
				bn.getString("columnQuantidade"),
				bn.getString("columnValorTotal") };
		
		table = new JTable(data, coluna);
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		frame = new JFrame();
				
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
				
		lblPedido = new JLabel(bn.getString("lblPedido"));
		lblPagamento = new JLabel(bn.getString("lblPagamento"));
		
		txtPedido = new JTextField(10);
				
		btnOk = new JButton(bn.getString("btnOk"));
		btnCancelar = new JButton(bn.getString("btnCancelar"));
		btnExtrato = new JButton(bn.getString("btnExtrato"));
				
		rbtCredito = new JRadioButton(bn.getString("rbtCredito"));
		rbtDebito = new JRadioButton(bn.getString("rbtDebito"));
		rbtDinheiro = new JRadioButton(bn.getString("rbtDinheiro"));
		
		frame.add(panel);
		
		panel.add(lblPedido);
		panel.add(txtPedido);
		panel.add(btnOk);
		panel.add(btnCancelar);
		panel.add(btnExtrato);
		panel.add(lblPagamento);
		panel.add(rbtCredito);
		panel.add(rbtDebito);
		panel.add(rbtDinheiro);
		panel.add(scroll);
		                 
		frame.setVisible(true);
		frame.setTitle(bn.getString("tituloFecharConta"));
		frame.setSize(490,520);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
}
