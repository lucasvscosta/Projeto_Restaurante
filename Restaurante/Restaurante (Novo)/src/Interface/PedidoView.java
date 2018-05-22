package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Model.PedidoTableModel;
import Model.UsuarioTableModel;
import Negocio.Cardapio;
import Negocio.Criptografia;
import Negocio.ItemPedido;
import Negocio.Pedido;
import Negocio.Sessao;
import Negocio.Usuario;

public class PedidoView extends JFrame implements ActionListener{
	
	private JLabel		labelNumPedido, labelNumProduto, labelQuantidade, labelNumMesa, 
   						labelIdGarcom, labelNovaPosicao, labelAlterarStatus, labelNumMesaAtual, labelData;
	
	private JTextField	textNumProduto, textNumPedido, textQuantidade, textNumMesa, 
   						textIdGarcom, textNovaPosicao, textData;
	
	private JButton	    buttonConsultar, buttonIncluir, buttonAlterar, buttonExcluir, buttonFinalizar, buttonCancelar;  
	
	private JButton     arrayButtonMesa[];
	
	private JComboBox   comboAlterarStatus;
	
	private JScrollPane scroll;
	
	private JTable 		tablePedido, table;
	
	private PedidoTableModel tableModel;
	
	private JPanel 		panelCenter, panelNorth, panelSouth, panelFlow;  
	
	private JFrame		janela;
	
	private JCheckBox 	checkGarcom;
	
	private Pedido pedido;
	private Cardapio produto;
	private ItemPedido item;
	private Usuario	usuario;
   	
	int i;   
	int numMesaSelecionada;
	
	private ResourceBundle	 bn=null;    
   	
   	public PedidoView(ResourceBundle bundle){
   		super();    
   		
		bn = bundle; 
   		
   		//frames usados em mais de um método
   		//LABELS      
   		labelNumProduto		= new JLabel(bn.getString("labelNumProduto"));
   		labelQuantidade   	= new JLabel(bn.getString("labelQuantidade"));
   		labelNumMesa 	  	= new JLabel(bn.getString("labelNumMesa"));
   		labelNumPedido 		= new JLabel(bn.getString("labelNumPedido"));
   		labelNovaPosicao	= new JLabel(bn.getString("labelNovaPosicao"));	  
   		labelAlterarStatus	= new JLabel(bn.getString("labelAlterarStatus"));
   		labelIdGarcom		= new JLabel(bn.getString("labelIdGarcom"));
   		
   		tablePedido = new JTable();
   		
        
   	}//fim do construtor
	
   	//CONSULTAR, ALTERAR E EXCLUIR PEDIDO
   	public void editarPedidoView(int idPedido){   		
   		//itemPedido
   	   	/*String [] column = 
   	   		{	bn.getString("columnCodigo"), 
   	   			bn.getString("columnNome"), 
   	   			bn.getString("columnQuantidade") }; */
   	   	
   		panelNorth 	= new JPanel();
   		panelCenter = new JPanel();   	
   		panelSouth 	= new JPanel();
   		
   		//layouts
   		janela 	= new JFrame(bn.getString("tituloPedidoEditarPedido"));  		
   		janela.setLayout(new BorderLayout(10, 10));				
   	   	
   		//NORTH      
   		panelNorth.setLayout(new GridLayout(2, 2, 10, 10));
   		
   		janela.add(panelNorth, BorderLayout.NORTH);
   		
   		textNumPedido 	= new JTextField(15);
   		textNumMesa 	= new JTextField(15);		
   		
   		panelNorth.add(labelNumPedido);
   		panelNorth.add(textNumPedido);
   		panelNorth.add(labelNumMesa);
   		panelNorth.add(textNumMesa);
   		
   		//CENTER      
   		panelCenter.setLayout(new GridLayout(1, 3, 10, 10));
   		
   		janela.add(panelCenter, BorderLayout.CENTER);
   		
   		buttonConsultar = new JButton(bn.getString("buttonConsultar"));
   		buttonAlterar 	= new JButton(bn.getString("buttonAlterar"));
   		buttonExcluir	= new JButton(bn.getString("buttonExcluir"));
   		
   		buttonExcluir.addActionListener(this);
   		buttonConsultar.addActionListener(this);
   		buttonAlterar.addActionListener(this);
   		
   		panelCenter.add(buttonConsultar);
   		panelCenter.add(buttonAlterar);
   		panelCenter.add(buttonExcluir);
   		
   		//SOUTH			
   		panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));			
   		
   		janela.add(panelSouth, BorderLayout.SOUTH);	
   		
   		carregarTable(idPedido);   
   		   		
   		janela.setResizable(false);
   		janela.setVisible(true);
   		janela.setSize(474, 562);
   		janela.setLocationRelativeTo(null);
   	} //fim do método manterPedidoView
   	
   	public void carregarTable(int idPedido){
   		panelSouth.add(carregarScroll(idPedido));
	}	
	
	public void carregarTableMesa(int numMesaPedido){
   		panelSouth.add(carregarScrollMesa(numMesaPedido));
	}	
	
	//CONSULTA COM ID OU NOME
	//SE 0 - RETORNA TODOS OS DADOS DA TABELA USUARIO
	private JScrollPane carregarScroll(int idPedido){
    	if(idPedido == 0) {
    		scroll = new JScrollPane(getTblPedidos());
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	} else {
    		scroll = new JScrollPane(getTblPedidos(idPedido));
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	}		
	}	
	
	private JScrollPane carregarScrollMesa(int numMesaPedido){    	
		scroll = new JScrollPane(getTblPedidosMesa(numMesaPedido));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		return scroll;    		    		
	}
	
	private JTable getTblPedidos() {   
		tablePedido.setModel(getTableModel());       
        return tablePedido;
    }
	
	private JTable getTblPedidos(int idUsuario) {		
		tablePedido.setModel(getTableModel(idUsuario));        
        return tablePedido;
    }
	
	private JTable getTblPedidosMesa(int numMesaPedido) {		
        tablePedido.setModel(getTableModelMesa(numMesaPedido));        
        return tablePedido;
    }
 
    private PedidoTableModel getTableModel() {
    	pedido = new Pedido();    	
        tableModel = new PedidoTableModel(pedido.consultarPedido(), bn);        
        return tableModel;
    }
        
    private PedidoTableModel getTableModel(int idPedido) {
    	pedido = new Pedido();           
        tableModel = new PedidoTableModel(pedido.consultarPedido(idPedido), bn);
        return tableModel;
    }
	
    private PedidoTableModel getTableModelMesa(int numMesaPedido) {
    	pedido = new Pedido();           
        tableModel = new PedidoTableModel(pedido.consultarMesaPedido(numMesaPedido), bn);
        return tableModel;
    }
   	
   	//INCLUIR PEDIDO 1 - SELECIONAR MESA
   	public void incluirPedidoView1() {	
   		//Layouts
   		janela = new JFrame(bn.getString("tituloPedidoSelecionarMesa"));		        	
   		janela.setLayout(new BorderLayout(10, 10));	  	         		
   	
   		//Center
   		panelCenter = new JPanel();
   		panelCenter.setLayout(new GridLayout(10, 5, 10, 10));	
   	
   		janela.add(panelCenter, BorderLayout.CENTER);
   	      
   		arrayButtonMesa = new JButton[50];
   		for(i = 0; i < arrayButtonMesa.length; i++){
   			arrayButtonMesa[i] = new JButton((bn.getString("mesa") + " " + (i + 1)));		
   			panelCenter.add(arrayButtonMesa[i]);
   			//System.out.println(arrayButtonMesa[i].getText());
   			arrayButtonMesa[i].addActionListener(this);
   		}   		
   		janela.setResizable(false);
   		janela.setVisible(true);      
   		janela.setSize(650,420);	
   		janela.setLocationRelativeTo(null);
   	}//fim do método incluirPedidoView1
	
	//INCLUIR PEDIDO 2 - SELECIONAR PRODUTO
   	public void incluirPedidoView2(int numMesa) {       
		//Layouts
   		janela = new JFrame(bn.getString("tituloPedidoEscolherProduto"));		
		janela.setLayout(new BorderLayout(10, 10));		
				
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(3, 3, 10, 10));	
		janela.add(panelNorth, BorderLayout.NORTH);
		
		textNumProduto = new JTextField(10);
		textQuantidade = new JTextField(10);
		textIdGarcom = new JTextField(10);
		
		numMesaSelecionada = numMesa;
		labelNumMesaAtual 	= new JLabel((bn.getString("mesa") + " " + numMesaSelecionada));
		
		buttonIncluir 	= new JButton(bn.getString("buttonIncluir"));
		buttonCancelar = new JButton(bn.getString("buttonCancelar"));
		
		checkGarcom = new JCheckBox(bn.getString("checkGarcom"));
		
		buttonIncluir.addActionListener(this);
		buttonCancelar.addActionListener(this);
		checkGarcom.addActionListener(this);
		
		panelNorth.add(labelNumProduto);
		panelNorth.add(textNumProduto);
		panelNorth.add(labelNumMesaAtual);
		panelNorth.add(labelQuantidade);
		panelNorth.add(textQuantidade);
		panelNorth.add(buttonIncluir);
		panelNorth.add(labelIdGarcom);
		panelNorth.add(textIdGarcom);
		panelNorth.add(buttonCancelar);
		 
		janela.add(checkGarcom, BorderLayout.CENTER);
		
		janela.setResizable(false);
		janela.setVisible(true);		
		janela.setSize(370,210);	
		janela.setLocationRelativeTo(null);
   	}//fim do método incluirPedidoView2
   	
   	//PRIORIZAR PEDIDO
   	public void priorizarPedidoView() {
   		Object [][] data = {   
   	           {"001", "Macarrão", "1"},      
   	           {"002", "Lasanha", "1"},      
   	           {"003", "Coca-cola", "2"},      
   	           {"004", "Suco Maracujá", "1"},      
   	           {"005", "Pudim", "2"}   
   	    };  
   	   	String [] column = 
   	   		{	bn.getString("columnCodigo"), 
   	   			bn.getString("columnNome"), 
   	   			bn.getString("columnQuantidade") }; 
   	   	
		//Layouts
   		janela = new JFrame(bn.getString("tituloPedidoPriorizarPedido"));		
		janela.setLayout(new BorderLayout(10, 10));		
				
		//North
		panelNorth = new JPanel();  
		panelNorth.setLayout(new GridLayout(3, 2, 10, 10));	
		
		janela.add(panelNorth, BorderLayout.NORTH);
		
		textNumPedido = new JTextField(10);
		textNovaPosicao = new JTextField(10);
		
		buttonAlterar = new JButton(bn.getString("buttonAlterar"));
		
		panelNorth.add(labelNumPedido);
		panelNorth.add(textNumPedido);
		panelNorth.add(labelNovaPosicao);
		panelNorth.add(textNovaPosicao);
		panelNorth.add(buttonAlterar);
		
		//Center
		panelCenter = new JPanel();
		panelCenter.setLayout(new FlowLayout());	
   	
		janela.add(panelCenter, BorderLayout.CENTER);
   	   	           
		table 		= new JTable(data, column);  
		scroll = new JScrollPane(table);
      
		panelCenter.add(scroll);  
		
		janela.setResizable(true);
		janela.setVisible(true);		
		janela.setSize(473,264);	
		janela.setLocationRelativeTo(null);
	}//fim do método priorizarPedidoView
   
   	//FINALIZAR PRATO
   	public void finalizarPratoView() {
		//Layouts
   		janela 	= new JFrame(bn.getString("tituloPedidoFinalizarPrato"));		
		janela.setLayout(new BorderLayout(10, 10));		
		
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(3, 3, 10, 10));	
		
		janela.add(panelNorth, BorderLayout.NORTH);
		
		textNumPedido        = new JTextField(10);     
		
		comboAlterarStatus   = new JComboBox();
		comboAlterarStatus.setBackground(Color.WHITE);
		comboAlterarStatus.addItem("----");
		comboAlterarStatus.addItem(bn.getString("comboEmAndamento"));
		comboAlterarStatus.addItem(bn.getString("comboPronto"));
		comboAlterarStatus.addItem(bn.getString("comboRecusado"));  
		
		buttonFinalizar      = new JButton(bn.getString("buttonFinalizar"));
		
		panelNorth.add(labelNumPedido);
		panelNorth.add(textNumPedido);
		panelNorth.add(labelAlterarStatus);
		panelNorth.add(comboAlterarStatus);
		panelNorth.add(buttonFinalizar);
						
		janela.setResizable(false);
		janela.setVisible(true);		
		janela.setSize(300,150);	
		janela.setLocationRelativeTo(null);
   	}//fim do método finalizarPratoView
   	
   	//Consultar Horário de Pico
   	public void consultarHorarioDePicoView(){
   	    String [] colunasConsultarHorarioDePico = 
   	    	{ 	bn.getString("columnData"), 
   	    		bn.getString("columnHorario"), 
   	    		bn.getString("columnTotalPedido"), 
   	    		bn.getString("columnSaidaPrato"), 
   	    		bn.getString("columnCaixa") };
   	    
   	    Object [][] dadosConsultarHorarioDePico = {
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
   	    
   		janela 	= new JFrame(bn.getString("tituloConsultarHorarioDePico"));	
   		
   		panelFlow = new JPanel();
   		panelFlow.setLayout(new FlowLayout(FlowLayout.LEFT));
   		
   		labelData = new JLabel(bn.getString("labelData"));
   		
   		textData = new JTextField(10);
   		
   		table = new JTable(dadosConsultarHorarioDePico, colunasConsultarHorarioDePico);
   		
   		buttonConsultar = new JButton(bn.getString("buttonConsultar"));
   		
   		scroll = new JScrollPane(table);
   		
   		janela.add(panelFlow);   		
   		
   		panelFlow.add(labelData);
   		panelFlow.add(textData);
   		panelFlow.add(buttonConsultar);
   		panelFlow.add(scroll);
   		
   		janela.setResizable(false);
		janela.setVisible(true);		
		janela.setSize(480, 210);	
		janela.setLocationRelativeTo(null);
   	}
   	
   	//Pratos Mais Vendidos
   	public void pratosMaisVendidosView(){
   	    String [] colunasPratosMaisVendidos = 
   	    	{	bn.getString("columnPrato"), 
   	    		bn.getString("columnQuantidade") };
   	    
   	    Object [][] dadosPratosMaisVendidos = {
   	 					        					 	{"", ""},
   	 					        					 	{"", ""},
   	 					        					 	{"", ""},
   	 					        					 	{"", ""},
   	 					        					 	{"", ""}
   	 					    					   	 };
   	    
   		janela 	= new JFrame(bn.getString("tituloConsultarHorarioDePico"));	
   		
   		panelFlow = new JPanel();
   		panelFlow.setLayout(new FlowLayout(FlowLayout.LEFT));
   		
   		labelData = new JLabel(bn.getString("labelData"));
   		
   		textData = new JTextField(10);
   		
   		table = new JTable(dadosPratosMaisVendidos, colunasPratosMaisVendidos);
   		
   		buttonConsultar = new JButton(bn.getString("buttonConsultar"));
   		
   		scroll = new JScrollPane(table);
   		
   		janela.add(panelFlow);   		
   		
   		panelFlow.add(labelData);
   		panelFlow.add(textData);
   		panelFlow.add(buttonConsultar);
   		panelFlow.add(scroll);
   		
   		janela.setResizable(false);
		janela.setVisible(true);		
		janela.setSize(480, 210);	
		janela.setLocationRelativeTo(null);
   	}  	
   	
   	public void actionPerformed( ActionEvent event ) {
   		
   		if(arrayButtonMesa != null) {
	   		for(int i = 0; i < arrayButtonMesa.length; i++){
	   			if ( event.getSource() == arrayButtonMesa[i] ) { 
	   				incluirPedidoView2(i + 1);   				
	   			}
	   		}
   		}
   		
   		
   		//CHECK MANTER GARÇOM
   		if(event.getSource() == checkGarcom){
   			if(checkGarcom.isSelected()) {
   				textIdGarcom.setEditable(false);
   			} else {
   				textIdGarcom.setEditable(true);
   			}
   		}
   		
   		//BUTTON Incluir
        if(event.getSource() == buttonIncluir){
        	if(	(textNumProduto == null)  || (textQuantidade == null) || 
        		(textIdGarcom == null) ) {        		
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroCampoBranco"));        		
        	} else {
        		
        		pedido 	= new Pedido();
        		produto = new Cardapio();
        		item	= new ItemPedido();
        		usuario = new Usuario();
        		Date hora = Calendar.getInstance().getTime();  
        		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        		String horaFormatada = sdf.format(hora);       		
        		
        		try {
        			produto.setIdProduto(Integer.parseInt(textNumProduto.getText()));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemIdInvalido"));	  
	        		return;
	        	}
        		
        		try {
        			item.setQuantidade(Integer.parseInt(textQuantidade.getText()) );
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemQuantidadeInvalido"));	  
	        		return;
	        	}
        		
        		try {        
        			if(checkGarcom.isSelected()) {
        				Sessao sessao = new Sessao();
        				pedido.setIdGarcom(sessao.getIdUsuarioLogado());           				
           			} else {
           				pedido.setIdGarcom(Integer.parseInt(textIdGarcom.getText()));
           			}        			
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemIdInvalido"));	  
	        		return;
	        	}
        		
        		pedido.setHoraEntrada(horaFormatada);
        		pedido.setNumMesa(numMesaSelecionada);    
        		
        		//SE A MESA ESTIVER LIVRE, INSERE NOVO PEDIDO E OS ITENS
        		//if(pedido.checarDispMesa(numMesaSelecionada)){
        			if(pedido.cadastrarPedido()) {
            			JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroSucesso"));
            			textNumProduto.setText(null);
    	        		textQuantidade.setText(null);
    	        		textIdGarcom.setText(null);        			
            		}
        		//} 
        		//SENAO, INSERE APENAS OS ITENS
        		/*else {        			
        			if(item.cadastrarItemPedido(numMesaSelecionada, produto.getIdProduto())) {
            			JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroSucesso")); 			
                		
            			//JOptionPane.showMessageDialog(null, "MESA OCUPADA");
            			textNumProduto.setText(null);
    	        		textQuantidade.setText(null);
    	        		textIdGarcom.setText(null);        			
            		}
        			
        		}   */    			    	  		        		
        	}        	
        }    
        //BUTTON - CONSULTAR
	    if(event.getSource() == buttonConsultar){
	    	pedido = new Pedido();
	    	
			int idPedido;
			int numMesa;			
			System.out.println("AQUI");
			if(!(textNumPedido.getText().equals(""))){	
				try {	   
					idPedido = Integer.parseInt(textNumPedido.getText());
	        	}
	        	catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	
	        		return;
	        	}
				try {					
					panelSouth.removeAll();
					carregarTable(idPedido);
					if(tableModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	
					} else {
		    			//.validate() = método para atualizar as informações
		    			panelSouth.validate();
		    			JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaSucesso"));	
					}	    			
	        	}
	        	catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	    		  
	        	} 
				
			} else if(!(textNumMesa.getText().equals(""))) {
				try {	    		    
	    			numMesa = Integer.parseInt(textNumMesa.getText());   			
	        	}
	        	catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	
	        		return;
	        	}				
				try {	    		    		    				    			
					panelSouth.removeAll();
					carregarTableMesa(numMesa);
					if(tableModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	
					} else {
		    			//.validate() = método para atualizar as informações
		    			panelSouth.validate();
		    			JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaSucesso"));	
					}
	    			
	        	}
	        	catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
	        	} 					
			} else {					
				JOptionPane.showMessageDialog(null, bn.getString("mensagemConsultaVazia"));	 
			}
	    }  
	    
	    //BUTTON - ALTERAR
        if(event.getSource() == buttonAlterar){
        	pedido = new Pedido();  
    		try {	    
    			int linhaSelecionada = tablePedido.getSelectedRow();
    			int colunaSelecionada = tablePedido.getSelectedColumn();
            	pedido = tableModel.getPedido(linhaSelecionada);  				
            	pedido.alterarPedido(pedido.getIdPedido());
            	panelSouth.removeAll();
    			carregarTable(pedido.getIdPedido());
            	
    			panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemAlterarSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}            	
        }
	    
	    //BUTTON - EXCLUIR
        if(event.getSource() == buttonExcluir){
        	pedido = new Pedido();  
    		try {	    
    			int linhaSelecionada = tablePedido.getSelectedRow();        	
            	pedido = tableModel.getPedido(linhaSelecionada);
            	pedido.excluirPedido(pedido.getIdPedido());
            	tableModel.removePedido(linhaSelecionada);        
            	panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemExcluirSucesso"));
        	}
        	catch(NumberFormatException e) {        		
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}      	
        }
   	}   	
}//fim da classe PedidoView
