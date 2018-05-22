package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Model.CardapioTableModel;
import Model.UsuarioTableModel;
import Negocio.Cardapio;
import Negocio.Criptografia;
import Negocio.Usuario;

public class CardapioView extends JFrame implements ActionListener{
	private JLabel			   
      labelNomePrato, labelNomeBebida, labelNomeSobremesa, labelNumProduto, labelNomeProduto, labelDescProduto, 
      labelValorProduto, labelTipoProduto;
	private JTextField		textNumProduto, textNomeProduto, textDispProduto, textValorProduto, textDescProduto;
	private JButton			
      buttonConsultar, buttonCadastrar, buttonAlterar, buttonExcluir, buttonCancelar;
	private JComboBox       comboTipoProduto;
	private JRadioButton	radioAtivo, radioDesativo;
	private JTable          tableProduto;
	private CardapioTableModel tableModel;
	private JScrollPane     scroll;
	private JPanel 			
      panelNorth, panelCenter, panelSouth, panelTabPrato, panelTabBebida, panelTabSobremesa;
	private JFrame			janela;
	private JTabbedPane 	tabbedCardapio;
	private ResourceBundle	bn=null;
	private Cardapio cardapio;
                                   
	public CardapioView(ResourceBundle bundle){
		super();		
		
		bn = bundle; 
		
		labelNumProduto   = new JLabel(bn.getString("labelNumProduto"));
		labelNomeProduto  = new JLabel(bn.getString("labelNomeProduto"));
		labelDescProduto  = new JLabel(bn.getString("labelDescProduto"));
		labelValorProduto = new JLabel(bn.getString("labelValorProduto"));  
		labelTipoProduto  = new JLabel(bn.getString("labelTipoProduto"));	
		
		tableProduto = new JTable();
	}
	
	//CONSULTAR/EDITAR CARDAPIO
	public void editarCardapioView(int idProduto){
		//Layouts
		janela = new JFrame(bn.getString("tituloEditarCardapioView"));
		janela.setLayout(new BorderLayout(10,10));
      
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(2, 2, 10, 10));      
      
		textNumProduto    = new JTextField(10);
		textNomeProduto   = new JTextField(10);
      
		panelNorth.add(labelNumProduto);
		panelNorth.add(textNumProduto);
		panelNorth.add(labelNomeProduto);
		panelNorth.add(textNomeProduto);
      
		janela.add(panelNorth, BorderLayout.NORTH);	
		
		//Center
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 3, 10, 10));     
            
		buttonConsultar   = new JButton(bn.getString("buttonConsultar"));
		buttonAlterar     = new JButton(bn.getString("buttonAlterar"));
		buttonExcluir     = new JButton(bn.getString("buttonExcluir"));
      
		panelCenter.add(buttonConsultar);
		panelCenter.add(buttonAlterar);
		panelCenter.add(buttonExcluir);
      
		janela.add(panelCenter, BorderLayout.CENTER);	
      
		//South
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
            
		carregarTable(idProduto); 
      
		janela.add(panelSouth, BorderLayout.SOUTH);	
		
		buttonConsultar.addActionListener(this);
		buttonAlterar.addActionListener(this);
		buttonExcluir.addActionListener(this);
      		
		//Definicoes
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(480,562);
		janela.setLocationRelativeTo(null);
	}
	
	public void carregarTable(int idProduto){
   		panelSouth.add(carregarScroll(idProduto));
	}	
	
	public void carregarTable(String nomeProduto){
   		panelSouth.add(carregarScroll(nomeProduto));
	}	
	
	//CONSULTA COM ID OU NOME
	//SE 0 - RETORNA TODOS OS DADOS DA TABELA cardapio
	private JScrollPane carregarScroll(int idProduto){
    	if(idProduto == 0) {
    		scroll = new JScrollPane(getTblProdutos());
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	} else {
    		scroll = new JScrollPane(getTblProdutos(idProduto));
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	}		
	}	
	
	private JScrollPane carregarScroll(String nomeProduto){    	
		scroll = new JScrollPane(getTblProdutos(nomeProduto));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		return scroll;    		    		
	}
	
	private JTable getTblProdutos() {   
    	tableProduto.setModel(getTableModel());       
        return tableProduto;
    }
	
	private JTable getTblProdutos(int idProduto) {		
		tableProduto.setModel(getTableModel(idProduto));        
        return tableProduto;
    }
	
	private JTable getTblProdutos(String nomeProduto) {		
		tableProduto.setModel(getTableModel(nomeProduto));        
        return tableProduto;
    }
 
    private CardapioTableModel getTableModel() {
    	cardapio = new Cardapio();    	
        tableModel = new CardapioTableModel(cardapio.consultarCardapio(), bn); 
        /*for(Cardapio ca : cardapio.consultarCardapio()) {
			  System.out.println(ca.getIdProduto() + " " + ca.getNomeProduto() + " " + ca.getDescricaoProduto() + " " + ca.getValorUnitarioProduto() + " " + ca.isDisponibilidadeProduto() + " " + ca.getTipoProduto());
		 }*/
        return tableModel;
    }
        
    private CardapioTableModel getTableModel(int idProduto) {
    	cardapio = new Cardapio();    	
        tableModel = new CardapioTableModel(cardapio.consultarCardapio(idProduto), bn);        
        return tableModel;
    }
	
    private CardapioTableModel getTableModel(String nomeProduto) {
    	cardapio = new Cardapio();    	
        tableModel = new CardapioTableModel(cardapio.consultarCardapio(nomeProduto), bn);        
        return tableModel;
    }
   
	//INCLUIR CARDAPIO
	public void incluirCardapioView(){
		//Layouts
		janela = new JFrame(bn.getString("tituloincluirCardapioView"));
		janela.setLayout(new BorderLayout(10,10));
      
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(6, 2, 10, 10));      
      
		labelNumProduto = new JLabel(bn.getString("labelNumProduto"));
		
		textNomeProduto   	= new JTextField(10);
		textDescProduto    	= new JTextField(10);
		textValorProduto    = new JTextField(10);
		textDispProduto    	= new JTextField(10);
		textNumProduto		= new JTextField(10);
		
		radioAtivo 		= new JRadioButton("Ativo", true);
		radioDesativo 	= new JRadioButton("Desativo", false);
		
		comboTipoProduto = new JComboBox();
		comboTipoProduto.setBackground(Color.WHITE);
		comboTipoProduto.addItem("----");
		comboTipoProduto.addItem(bn.getString("comboPrato"));
		comboTipoProduto.addItem(bn.getString("comboSobremesa"));
		comboTipoProduto.addItem(bn.getString("comboBebida"));  
      
		radioAtivo.addActionListener(this);
      	radioDesativo.addActionListener(this);
		
      	panelNorth.add(labelNumProduto);
		panelNorth.add(textNumProduto);
		panelNorth.add(labelNomeProduto);
		panelNorth.add(textNomeProduto);
		panelNorth.add(labelValorProduto);
		panelNorth.add(textValorProduto);
		panelNorth.add(labelDescProduto);
		panelNorth.add(textDescProduto); //<--- COLOCAR JTEXTAREA
      	panelNorth.add(labelTipoProduto);
      	panelNorth.add(comboTipoProduto);
      	panelNorth.add(radioAtivo);
      	panelNorth.add(radioDesativo);
      	
      	      	
      
		janela.add(panelNorth, BorderLayout.NORTH);	
		
		//Center
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 2, 10, 10));     
            
		buttonCadastrar   = new JButton(bn.getString("buttonCadastrar"));
		buttonCancelar    = new JButton(bn.getString("buttonCancelar"));
      
      	panelCenter.add(buttonCadastrar);
      	panelCenter.add(buttonCancelar);
      	
      	buttonCadastrar.addActionListener(this);
      	buttonCancelar.addActionListener(this);
      
		janela.add(panelCenter, BorderLayout.CENTER);	     	
      		
		//Definicoes
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(300,280);	
		janela.setLocationRelativeTo(null);
	}

   
	//CARDAPIO
	public void vizualizarCardapioView(){
		//Layouts
		janela = new JFrame(bn.getString("tituloVisualizarCardapioView"));
		janela.setLayout(new BorderLayout(10,10));
		
		
		//North
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT));	
		
		panelTabPrato		= new JPanel();
		panelTabBebida		= new JPanel();
		panelTabSobremesa	= new JPanel();
		panelTabPrato.setPreferredSize(new Dimension(300, 250));
		
		
		labelNomePrato = new JLabel("nomePrato | descrição | disponibilidade");		
		panelTabPrato.add(labelNomePrato);
		
		labelNomeBebida = new JLabel("nomeBebida | descrição | disponibilidade");
		panelTabBebida.add(labelNomeBebida);
		
		labelNomeSobremesa = new JLabel("nomeSobremesa | descrição | disponibilidade");
		panelTabSobremesa.add(labelNomeSobremesa);
		
		
		tabbedCardapio = new JTabbedPane();
		//ImageIcon icon = createImageIcon("icon.prato.png");
		tabbedCardapio.addTab("Prato",  	panelTabPrato);
		tabbedCardapio.addTab("Bebida",		panelTabBebida);
		tabbedCardapio.addTab("Sobremesa", 	panelTabSobremesa);
		//tabbedCardapio.addTab("Tab 1", icon, panelTabPratos, "Pratos");
		
		panelNorth.add(tabbedCardapio);
		janela.add(panelNorth, BorderLayout.NORTH);	
			
		//Definicoes
		janela.setVisible(true);
		janela.setResizable(false);		
		janela.setSize(300,200);	
		janela.setLocationRelativeTo(null);		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//RADIOBUTTON
		if(event.getSource() == radioAtivo){ 
			radioDesativo.setSelected(false);
		}
		if(event.getSource() == radioDesativo){ 
			radioAtivo.setSelected(false);
		}
		//BUTTON CADASTRAR
        if(event.getSource() == buttonCadastrar){
        	if(	(textNomeProduto == null)  || (textDescProduto == null) || 
        		(textValorProduto == null) || (textDispProduto == null) || 
        		(textNumProduto == null) || (comboTipoProduto.getSelectedIndex() == 0) ) {        		
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroCampoBranco"));        		
        	} else {       	
	        		    		  
        		cardapio = new Cardapio();
        		
        		try {
        			cardapio.setIdProduto(Integer.parseInt(textNumProduto.getText()));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemIdInvalido"));	  
	        		return;
	        	}
        		
        		try {
	        		cardapio.setValorUnitarioProduto(Double.parseDouble(textValorProduto.getText() ));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemValorInvalido"));	  
	        		return;
	        	}	        		
        		
        		cardapio.setNomeProduto	(textNomeProduto.getText());
        		cardapio.setDescricaoProduto(textDescProduto.getText());        		
        		cardapio.setTipoProduto	(comboTipoProduto.getSelectedIndex());
        		
        		if(radioAtivo.isSelected()){        				
        			cardapio.setDisponibilidadeProduto(true);
				}else if(radioDesativo.isSelected()){			
					cardapio.setDisponibilidadeProduto(false);
				}
        		
        		if(cardapio.cadastrarCardapio()) {
        			JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroSucesso"));
        			textNumProduto.setText(null);
        			textNomeProduto.setText(null);
        			textDescProduto.setText(null);
        			textValorProduto.setText(null);
        			textDispProduto.setText(null);
	        		comboTipoProduto.setSelectedIndex(0);        			
        		}	    	  		        		
        	}
        }  
        //BUTTON CANCELAR
        if(event.getSource() == buttonCancelar){
        	this.dispose();
        	janela.setDefaultCloseOperation( EXIT_ON_CLOSE );
        } 
        
        //BUTTON - CONSULTAR
        if(event.getSource() == buttonConsultar){
        	cardapio = new Cardapio();
        	
			String nomeUsuario = textNomeProduto.getText();
			int idUsuario = 0;			
			
			if(!(nomeUsuario.equals(""))){	
				try {					
					panelSouth.removeAll();
					carregarTable(nomeUsuario);
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
				
			} else if(nomeUsuario.equals("")) {
				try {	    		    
	    			idUsuario = Integer.parseInt(textNumProduto.getText());   			
	        	}
	        	catch(NumberFormatException e) {
	        		
	        	}				
				if (idUsuario != 0) {
					try {	    		    		    				    			
						panelSouth.removeAll();
						carregarTable(idUsuario);
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
        }
        
        //BUTTON - ALTERAR
        if(event.getSource() == buttonAlterar){
        	cardapio = new Cardapio();  
    		try {	    
    			int linhaSelecionada = tableProduto.getSelectedRow();
    			int colunaSelecionada = tableProduto.getSelectedColumn();
            	cardapio = tableModel.getCardapio(linhaSelecionada);  				
            	cardapio.alterarCardapio(cardapio.getIdProduto());
            	panelSouth.removeAll();
    			carregarTable(cardapio.getIdProduto());
            	
    			panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemAlterarSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}            	
        }
        
        //BUTTON - EXCLUIR
        if(event.getSource() == buttonExcluir){
        	cardapio = new Cardapio();  
    		try {	    
    			int linhaSelecionada = tableProduto.getSelectedRow();        	
    			cardapio = tableModel.getCardapio(linhaSelecionada);
    			cardapio.excluirUsuario(cardapio.getIdProduto());
            	tableModel.removeCardapio(linhaSelecionada);        
            	panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemExcluirSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}      	
        }
		
	}
}

