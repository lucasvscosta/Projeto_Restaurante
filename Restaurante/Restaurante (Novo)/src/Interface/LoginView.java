package Interface;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Negocio.Criptografia;
import Negocio.Sessao;

public class LoginView extends JFrame implements ActionListener
{
   private ImageIcon	   icon, icon2, iconLogin, iconCancelar;
   private JPanel		   panel;
   private JFrame		   frame;
   private JMenuBar        bar;
   private JMenu           menuIdioma;
   private JMenuItem       itemIngles, itemPortugues, itemEspanhol;
   private JLabel          lblUsuario, lblSenha, lblIcon, lblNada, lblNada2, lblNada3, lblNada4;
   private JTextField      txtUser, txtSenha;
   private JButton         btnLogin, btnCancelar;
   private ResourceBundle  bn=null;
   private RestauranteView restaurante;
   
   //-------------Construtor---------------
   public LoginView()
   {
	   
	  bn = ResourceBundle.getBundle("inter"); 
      
	  frame = new JFrame();
	  
	  icon = new ImageIcon("imageLoginView1.png");
	  icon2 = new ImageIcon("imageLoginView1.png");
	  iconLogin = new ImageIcon("iconLogin.png");
	  iconCancelar = new ImageIcon("iconCancelar.png");
      
      panel = new JPanel();
      panel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
      
      bar = new JMenuBar();
      
      menuIdioma = new JMenu(bn.getString("menuIdioma"));
      		
      itemPortugues = new JMenuItem(bn.getString("itemPortugues"));
      itemIngles = new JMenuItem(bn.getString("itemIngles"));
      itemEspanhol = new JMenuItem(bn.getString("itemEspanhol"));
      lblUsuario = new JLabel(bn.getString("lblUsuario"));
      lblSenha = new JLabel(bn.getString("lblSenha"));
      lblIcon = new JLabel(icon);
      lblNada = new JLabel("                            ");
      lblNada2 = new JLabel("                           ");
      lblNada3 = new JLabel("");
      lblNada4 = new JLabel("");
      
      txtUser = new JTextField(20); 
      txtSenha = new JPasswordField(20);
            
      btnLogin = new JButton(bn.getString("btnLogin"));
      btnCancelar = new JButton(bn.getString("btnCancelar"));
      
      panel.add(lblNada2);
      panel.add(lblIcon);
      panel.add(lblNada);
      panel.add(lblUsuario);
      panel.add(txtUser);
      panel.add(lblNada3);
      panel.add(lblSenha);
      panel.add(lblNada4);
      panel.add(txtSenha);
      panel.add(btnLogin);
      panel.add(btnCancelar);      

      bar.add(menuIdioma);
      
      menuIdioma.add(itemIngles);
      menuIdioma.add(itemPortugues);
      menuIdioma.add(itemEspanhol);
      
      icon2 = new ImageIcon("imageLoginView1.png");
      frame.setIconImage(icon2.getImage());
      
      itemPortugues.addActionListener(this);
      itemIngles.addActionListener(this);
      itemEspanhol.addActionListener(this);
      btnLogin.addActionListener(this);
      btnCancelar.addActionListener(this);
      
      frame.add(panel);
      frame.setJMenuBar(bar);    
      frame.setVisible(true); //Deixa janela visível
      frame.setTitle(bn.getString("tituloAcesso")); //Título da Janela
      frame.setSize(260, 265); //Tamanho Janela
      frame.setLocationRelativeTo(null); //Posição da Janela
      frame.setResizable(false); //Maximização/Minimização
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   public void refreshIdiom(){
	   menuIdioma.setText(bn.getString("menuIdioma"));            
       
	   itemIngles.setText(bn.getString("itemIngles"));
	   itemPortugues.setText(bn.getString("itemPortugues"));
	   itemEspanhol.setText(bn.getString("itemEspanhol"));
       
       lblUsuario.setText(bn.getString("lblLoginUsuario"));
       lblSenha.setText(bn.getString("lblSenhaUsuario"));
       
       btnLogin.setText(bn.getString("btnLogin"));
       btnCancelar.setText(bn.getString("btnCancelar"));
       
       setTitle(bn.getString("tituloAcesso"));	   
   }    

	@Override
	public void actionPerformed(ActionEvent event) {
		//Int = Português	      
	      if(event.getSource() == itemPortugues){                    	
	    	  bn = ResourceBundle.getBundle("inter", new Locale("pt", "BR"));
	          refreshIdiom();
	          JOptionPane.showMessageDialog(null, "Idioma trocado para Português (BR).");
	      }
	   
	      if(event.getSource() == itemIngles){                    	
	    	  bn = ResourceBundle.getBundle("inter", Locale.US);
	          refreshIdiom();
	          JOptionPane.showMessageDialog(null, "Idiom changed to English.");
	      }
	      
	      if(event.getSource() == itemEspanhol){                    	
	    	  bn = ResourceBundle.getBundle("inter", new Locale("es", "AR"));
	          refreshIdiom();
	          JOptionPane.showMessageDialog(null, "El lenguaje cambiado a Español.");
	      }

	      if(event.getSource() == btnLogin){     
	    	  String login 	= txtUser.getText();
	    	  String senha	= txtSenha.getText();    	  
	    	  
	    	  try {	    		  
		    	  Criptografia cripto = new Criptografia();	 
		    	  //Sessao sessao = new Sessao();
		    	  if(cripto.validarLogin(login, Integer.parseInt(senha))){
		    		  JOptionPane.showMessageDialog(null, bn.getString("mensagemLogar"));
			          frame.setVisible(false);
			          restaurante = new RestauranteView(bn);	 
		    	  }	else {
		    		  JOptionPane.showMessageDialog(null, bn.getString("mensagemLoginInvalido"));
		    	  } 		    	  	    		  
	    	  }
	    	  catch(NumberFormatException e) {
	    		  JOptionPane.showMessageDialog(null, bn.getString("mensagemLoginInvalido"));	    		  
	    	  }	    	     	  
	      }
		
		  if(event.getSource() == btnCancelar){                    	
			  JOptionPane.showMessageDialog(null, bn.getString("mensagemDeslogar"));
	          System.exit(0);
		  }
	}
}