package Interface;

import java.awt.FlowLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CaixaView{
	private JFrame		frame;
	private JPanel		panel;
	private JLabel		lblData, lblNada1;
	private JTextField	txtData;
	private JButton		btnFechar;
	private	JTable		table;
	private JScrollPane	scroll;
	private ResourceBundle	bn=null;
	
	public CaixaView(ResourceBundle bundle){
		bn = bundle; 
	}
	
	public void fecharCaixaView(){
		String [] colunasCaixa = 
			{	bn.getString("columnMesa"),
				bn.getString("columnTotalPedido"),
				bn.getString("columnCartaoCredito"), 
				bn.getString("columnCartaoDebito"),
				bn.getString("columnPagamentoDinheiro"),
				bn.getString("columnValorTotal") };
		   
		Object [][] dadosCaixa = {
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
														{" "," "," "," "," "," "},
		                                       };
		
		frame = new JFrame();
		
		lblData = new JLabel(bn.getString("lblData"));
		lblNada1 = new JLabel("                                                                 ");		
		
		txtData = new JTextField(10);
		
		btnFechar = new JButton(bn.getString("btnFechar"));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		table = new JTable(dadosCaixa, colunasCaixa);
		
		scroll = new JScrollPane(table); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		frame.add(panel);
		panel.add(lblData);
		panel.add(txtData);
		panel.add(scroll);
		panel.add(lblNada1);
		panel.add(btnFechar);
		
		
		frame.setVisible(true);
		frame.setTitle(bn.getString("tituloFecharCaixa"));
		frame.setSize(490, 530);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null); 
	}
}
