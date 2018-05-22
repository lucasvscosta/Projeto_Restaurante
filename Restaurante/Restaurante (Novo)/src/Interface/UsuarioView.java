package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DAO.UsuarioDAO;
import Model.UsuarioTableModel;
import Negocio.Criptografia;
import Negocio.Usuario;

public class UsuarioView extends JFrame implements ActionListener{

	protected JTable		tableUsuario;	
	protected UsuarioTableModel tableModel;
	protected JCheckBox		checkConsiderar;	
	protected JComboBox		comboTipoUsuario;	
	protected JScrollPane	scroll;	
	protected JLabel		lblIdUsuario, lblNomeUsuario, lblLoginUsuario, lblSenhaUsuario, 
							lblRgUsuario, lblCpfUsuario, lblTipoUsuario;	
	protected JTextField	txtIdUsuario, txtNomeUsuario, txtLoginUsuario,
							txtRgUsuario, txtCpfUsuario;	
	protected JPasswordField txtSenhaUsuario;
	protected JButton		btnCadastrar, btnConsultar, btnAlterar, btnExcluir, btnOk;	
	protected JPanel		panel, panelNorth, panelCenter, panelSouth;	
	protected JFrame		frame;		
	protected ResourceBundle bn = null;	
	protected Usuario 		usuario;
	protected Criptografia	cripto;
	
	public UsuarioView(ResourceBundle bundle){		
		
		bn = bundle; 
		
		lblTipoUsuario = new JLabel(bn.getString("lblTipoUsuario"));
		lblIdUsuario = new JLabel(bn.getString("lblIdUsuario"));
		lblNomeUsuario = new JLabel(bn.getString("lblNomeUsuario"));
		lblLoginUsuario = new JLabel(bn.getString("lblLoginUsuario"));
		lblSenhaUsuario = new JLabel(bn.getString("lblSenhaUsuario"));
		lblRgUsuario = new JLabel(bn.getString("lblRgUsuario"));
		lblCpfUsuario = new JLabel(bn.getString("lblCpfUsuario"));
		
		tableUsuario = new JTable();
	}
	
	public void cadastrarUsuarioView(){
 		panel = new JPanel();  			
 		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
 		
 		comboTipoUsuario = new JComboBox();
 		comboTipoUsuario.setBackground(Color.WHITE);
        comboTipoUsuario.addItem("-----");
        comboTipoUsuario.addItem(bn.getString("comboSupervisor"));
        comboTipoUsuario.addItem(bn.getString("comboGarcom"));
        comboTipoUsuario.addItem(bn.getString("comboCozinheiro"));
        comboTipoUsuario.addItem(bn.getString("comboCaixa"));
 		
   		btnCadastrar = new JButton(bn.getString("btnCadastrar"));
   		
   		txtIdUsuario = new JTextField(25); 
   		txtNomeUsuario = new JTextField(25); 
   		txtLoginUsuario = new JTextField(25);
   		txtSenhaUsuario = new JPasswordField(25);
		txtRgUsuario = new JTextField(25); 
		txtCpfUsuario = new JTextField(25);
		
   		frame = new JFrame(bn.getString("tituloCadastrarUsuario"));
   		frame.add(panel);   
   		
   		panel.add(lblIdUsuario);
   		panel.add(txtIdUsuario);
   		panel.add(lblNomeUsuario);
   		panel.add(txtNomeUsuario);
   		panel.add(lblRgUsuario);
   		panel.add(txtRgUsuario);
   		panel.add(lblCpfUsuario);
   		panel.add(txtCpfUsuario);   		
   		panel.add(lblLoginUsuario);
   		panel.add(txtLoginUsuario);
   		panel.add(lblSenhaUsuario);
   		panel.add(txtSenhaUsuario);
   		panel.add(lblTipoUsuario);
   		panel.add(comboTipoUsuario);
   		panel.add(btnCadastrar);
   		
   		btnCadastrar.addActionListener(this);
   		   		
   		frame.setResizable(false);
   		frame.setVisible(true);
   		frame.setSize(303,357);
   		frame.setLocationRelativeTo(null);
	}	
	
	//CONSULTAR/EDITAR USUARIO
	public void consultarEditarUsuarioView(int idUsuario){
		//Layouts
		frame = new JFrame(bn.getString("tituloConsultarUsuario"));
		frame.setLayout(new BorderLayout(10,10));
				
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(2, 2, 10, 10));      
      
		lblIdUsuario = new JLabel(bn.getString("lblIdUsuario"));
		lblNomeUsuario = new JLabel(bn.getString("lblNomeUsuario"));
		txtIdUsuario    = new JTextField(10);
		txtNomeUsuario  = new JTextField(10);
      
		panelNorth.add(lblIdUsuario);
		panelNorth.add(txtIdUsuario);
		panelNorth.add(lblNomeUsuario);
		panelNorth.add(txtNomeUsuario);
      
		frame.add(panelNorth, BorderLayout.NORTH);	
		
		//Center
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 3, 10, 10));     
            
		btnConsultar   = new JButton(bn.getString("buttonConsultar"));
		btnAlterar     = new JButton(bn.getString("buttonAlterar"));
		btnExcluir     = new JButton(bn.getString("buttonExcluir"));
      
		panelCenter.add(btnConsultar);
		panelCenter.add(btnAlterar);
		panelCenter.add(btnExcluir);
      
		frame.add(panelCenter, BorderLayout.CENTER);	
      
		//South
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout()); 
		
		carregarTable(idUsuario);     
      
		frame.add(panelSouth, BorderLayout.SOUTH);	
		
		btnExcluir.addActionListener(this);
   		btnConsultar.addActionListener(this);
   		btnAlterar.addActionListener(this);
   		      		
		//Definicoes
   		frame.setVisible(true);
   		frame.setResizable(false);
   		frame.setSize(480,562);
   		frame.setLocationRelativeTo(null);
    }
	
	public void carregarTable(int idUsuario){
   		panelSouth.add(carregarScroll(idUsuario));
	}	
	
	public void carregarTable(String nomeUsuario){
   		panelSouth.add(carregarScroll(nomeUsuario));
	}	
	
	//CONSULTA COM ID OU NOME
	//SE 0 - RETORNA TODOS OS DADOS DA TABELA USUARIO
	private JScrollPane carregarScroll(int idUsuario){
    	if(idUsuario == 0) {
    		scroll = new JScrollPane(getTblUsuarios());
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	} else {
    		scroll = new JScrollPane(getTblUsuarios(idUsuario));
    		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
    		return scroll;    		
    	}		
	}	
	
	private JScrollPane carregarScroll(String nomeUsuario){    	
		scroll = new JScrollPane(getTblUsuarios(nomeUsuario));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		return scroll;    		    		
	}
	
	private JTable getTblUsuarios() {   
    	tableUsuario.setModel(getTableModel());       
        return tableUsuario;
    }
	
	private JTable getTblUsuarios(int idUsuario) {		
        tableUsuario.setModel(getTableModel(idUsuario));        
        return tableUsuario;
    }
	
	private JTable getTblUsuarios(String nomeUsuario) {		
        tableUsuario.setModel(getTableModel(nomeUsuario));        
        return tableUsuario;
    }
 
    private UsuarioTableModel getTableModel() {
    	usuario = new Usuario();    	
        tableModel = new UsuarioTableModel(usuario.consultarUsuario(), bn);        
        return tableModel;
    }
        
    private UsuarioTableModel getTableModel(int idUsuario) {
    	usuario = new Usuario();           
        tableModel = new UsuarioTableModel(usuario.consultarUsuario(idUsuario), bn);
        return tableModel;
    }
	
    private UsuarioTableModel getTableModel(String nomeUsuario) {
    	usuario = new Usuario();           
        tableModel = new UsuarioTableModel(usuario.consultarUsuario(nomeUsuario), bn);
        return tableModel;
    }
    
	@Override
	public void actionPerformed(ActionEvent event) {
		//BUTTON CADASTRAR
        if(event.getSource() == btnCadastrar){
        	if(	(txtNomeUsuario == null)  || (txtLoginUsuario == null) || 
        		(txtSenhaUsuario == null) || (txtRgUsuario == null) || 
        		(txtCpfUsuario == null) || (comboTipoUsuario.getSelectedIndex() == 0) ) {
        		
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroCampoBranco"));        		
        	} else {
        		
        		usuario = new Usuario();
        		cripto  = new Criptografia();
        		
        		try {
        			usuario.setId(Integer.parseInt(txtIdUsuario.getText()));
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemIdInvalido"));	  
	        		return;
	        	}
        		
        		try {
        			usuario.setSenha(cripto.cifrar(Integer.parseInt(txtSenhaUsuario.getText())) );
        		}
        		catch(NumberFormatException e) {
	        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSenhaInvalido"));	  
	        		return;
	        	}
        		
        		usuario.setNome	(txtNomeUsuario.getText());
        		usuario.setLogin(txtLoginUsuario.getText());	        		
        		usuario.setRg	(txtRgUsuario.getText());
        		usuario.setCpf	(txtCpfUsuario.getText());
        		usuario.setTipo	(comboTipoUsuario.getSelectedIndex());
        		
        		if(usuario.cadastrarUsuario()) {
        			JOptionPane.showMessageDialog(null, bn.getString("mensagemCadastroSucesso"));
        			txtIdUsuario.setText(null);
	        		txtNomeUsuario.setText(null);
	        		txtLoginUsuario.setText(null);
	        		txtSenhaUsuario.setText(null);
	        		txtRgUsuario.setText(null);
	        		txtCpfUsuario.setText(null);
	        		comboTipoUsuario.setSelectedIndex(0);        			
        		}	    	  		        		
        	}        	
        }        
        
        //BUTTON - CONSULTAR
        if(event.getSource() == btnConsultar){
        	usuario = new Usuario();
        	
			String nomeUsuario = txtNomeUsuario.getText();
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
	    			idUsuario = Integer.parseInt(txtIdUsuario.getText());   			
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
        if(event.getSource() == btnAlterar){
        	usuario = new Usuario();  
    		try {	    
    			int linhaSelecionada = tableUsuario.getSelectedRow();
    			int colunaSelecionada = tableUsuario.getSelectedColumn();
            	usuario = tableModel.getUsuario(linhaSelecionada);  				
            	usuario.alterarUsuario(usuario.getId());
            	panelSouth.removeAll();
    			carregarTable(usuario.getId());
            	
    			panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemAlterarSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}            	
        }
        
        //BUTTON - EXCLUIR
        if(event.getSource() == btnExcluir){
        	usuario = new Usuario();  
    		try {	    
    			int linhaSelecionada = tableUsuario.getSelectedRow();        	
            	usuario = tableModel.getUsuario(linhaSelecionada);
            	usuario.excluirUsuario(usuario.getId());
            	tableModel.removeUsuario(linhaSelecionada);        
            	panelSouth.validate();
            	JOptionPane.showMessageDialog(null, bn.getString("mensagemExcluirSucesso"));
        	}
        	catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, bn.getString("mensagemSomenteNumero"));	    		  
        	}      	
        }
	}
}
