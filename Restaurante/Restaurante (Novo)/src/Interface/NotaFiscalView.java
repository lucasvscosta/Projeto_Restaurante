package Interface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NotaFiscalView {
	private JLabel		lblNumeroNotaFiscal, lblNumeroPedido, lblCpfCnpj, lblData, lblNada1;
	private JTextField	txtNumeroNotaFiscal, txtNumeroPedido, txtCpfCnpj, txtData;
	private JFrame		frame;
	private JButton		btnValidarDados, btnEnviar, btnConsultar;
	private JPanel		panel;
	private JScrollPane	scroll;
	private JTable		table;
	private ResourceBundle bn=null;
	
	//FecharNotaFiscal
	
	
	
	
	public NotaFiscalView(ResourceBundle bundle){
		
		bn = bundle; 
		
		lblNumeroNotaFiscal = new JLabel(bn.getString("lblNumeroNotaFiscal"));
		lblNumeroPedido = new JLabel(bn.getString("lblNumeroPedido"));
		lblCpfCnpj = new JLabel(bn.getString("lblCpfCnpj"));
		lblData = new JLabel(bn.getString("lblData"));
	}
	
	public void emitirNotaFiscalView(){
		frame = new JFrame();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));  
		
		txtNumeroNotaFiscal = new JTextField(10);
		txtNumeroPedido = new JTextField(10);
		txtCpfCnpj = new JTextField(10);
		
		btnValidarDados = new JButton(bn.getString("btnValidarDados"));
		
		frame.add(panel);
		
		panel.add(lblNumeroNotaFiscal);
		panel.add(txtNumeroNotaFiscal);
		panel.add(lblNumeroPedido);
		panel.add(txtNumeroPedido);
		panel.add(btnValidarDados);
		
		frame.setVisible(true);
        frame.setTitle(bn.getString("tituloEmitirNotaFiscal"));
        frame.setSize(270, 150);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
	}
	
	public void fecharNotaFiscalView(){
		String [] colunasFecharNotaFiscal = 
			{bn.getString("columnData/Hora"),
			 bn.getString("columnNumero"), 
			 bn.getString("columnCPF/CNPJ"), 
			 bn.getString("columnValorTotal"), 
			 bn.getString("columnICMS")};
		   
		Object [][] dadosFecharNotaFiscal = {
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""},
							        						{"", "", "", "", ""}
							    					    };
		
		frame = new JFrame();
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));  
		
		lblNada1 = new JLabel("                                                                 ");
		
		txtData = new JTextField(10);
		
		table = new JTable(dadosFecharNotaFiscal, colunasFecharNotaFiscal);
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		btnEnviar = new JButton(bn.getString("btnEnviar"));
		
		frame.add(panel);
		
		panel.add(lblData);
		panel.add(txtData);
		panel.add(scroll);
		panel.add(lblNada1);
		panel.add(btnEnviar);
		
		
        frame.setVisible(true);
        frame.setTitle(bn.getString("tituloFecharNotaFiscal"));
        frame.setSize(487, 530);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null); 
	}
	
	public void consultarNotaFiscalView(){
		//Consultar Nota Fiscal
		String [] colunasConsultarNotaFiscal = 
			{bn.getString("columnNotaFiscal"),
			 bn.getString("columnValor"), 
			 bn.getString("columnData"),
			 bn.getString("columnCPF/CNPJ")
			 };
		   
		Object [][] dadosConsultarNotaFiscal =     {
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
																	{" "," "," "," "},
							    				  	  		   };
		frame = new JFrame();
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));  
		
		txtNumeroNotaFiscal = new JTextField(10);
		
		table = new JTable(dadosConsultarNotaFiscal, colunasConsultarNotaFiscal);
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		btnConsultar = new JButton(bn.getString("btnConsultar"));
		
		frame.add(panel);
		
		panel.add(lblNumeroNotaFiscal);
		panel.add(txtNumeroNotaFiscal);
		panel.add(btnConsultar);
		panel.add(scroll);
		
		frame.setVisible(true);
		frame.setTitle(bn.getString("tituloConsultarNotaFiscal"));
		frame.setSize(495, 506);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);  		
	}
}
