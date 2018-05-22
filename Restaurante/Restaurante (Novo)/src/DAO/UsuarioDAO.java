package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import Negocio.Sessao;
import Negocio.Usuario;

public class UsuarioDAO {
	
	//atributos do usuario
	private int 			id;
	private String 			nome;
	private String 			login;
	private int 			senha;
	private int 			tipo;
	private String 			rg;
	private String 			cpf;
	
	//demais variáveis
	private int 			tamanho;
	private Scanner 		input;
	private static int    	vetorSenha[];
	private static String  	vetorLogin[];
	Usuario usuario;
	
	//gets e sets
	public int getTamanhoVetor() {
		return tamanho;
	}
	public void setTamanhoVetor(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	//*************** COMANDOS PARA LOGIN NO SISTEMA ***************************
	public void abrirArquivo() {
		try {
			input = new Scanner( new File( "acesso.txt" ) );
	    } 
		catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Erro ao abrir o arquivo." );
			System.exit( 1 );
	    } 
	}
	
	public void fecharArquivo() {
	      if ( input != null )
	         input.close(); 
	} 
	
	public boolean validarLogin(String login, int senha) {
		//quantidade de registros
		abrirArquivo();
		lerTamanho();
		fecharArquivo();
		
		if(getTamanhoVetor() < 1){
			return false;			
		} else {
		
			//alimentar vetores de senha e login
			abrirArquivo();
			alimentarVetor();
			fecharArquivo();
			
			
			//exibi login e senha desordenadamente
			System.out.println("DESORDENADOS:");
			for(int i = 0; i < vetorLogin.length; i++){
				System.out.println(vetorLogin[i] + " " + vetorSenha[i]);				
			}			
			System.out.println("\n\n");
			
			//método para ordenação dos vetores
			bubble(vetorLogin, vetorSenha);			
			
			//exibi login e senha após ordenados
			System.out.println("ORDENADOS:");
			for(int i = 0; i < vetorLogin.length; i++){
				System.out.println(vetorLogin[i] + " " + vetorSenha[i]);
				
			}
			
			//consulta binária
			int posicao = binaria(vetorSenha, senha);		
			if(posicao == -1){
				return false;			
			} else {
				if(vetorLogin[posicao].equals(login) && vetorSenha[posicao] == senha) {
					Sessao sessao = new Sessao();
					consultarLogin(login);						
					for(Usuario usuario : consultarLogin(login)) {
						System.out.println(usuario.getId());					
						sessao.setIdUsuarioLogado(usuario.getId());
					}					
					return true;
				}			
			}					
		}				
		return false;
	}
	
	//Inserir login e senha
	public boolean cadastrarLogin() throws IOException{		
		String newLine = System.getProperty("line.separator");
		Writer arquivo = new BufferedWriter(new FileWriter("acesso.txt", true));		
		arquivo.write(getLogin() + " " + getSenha() + newLine);
		arquivo.close();
	    return true;
	}
	
	//Lê a quantidade de registros
	public void lerTamanho() {
		int i=0;
		try{
			while (input.hasNext()) {
				setLogin(input.next());
				setSenha(input.nextInt());			
				i++;
			} 
			setTamanhoVetor(i);
			vetorSenha = new int[getTamanhoVetor()];
			vetorLogin = new String[getTamanhoVetor()];
		} 
		catch ( NoSuchElementException elementException ) {
			System.err.println( "Formato Imprópril de Arquivo." );
	        input.close();
	        System.exit( 1 );
		} 
		catch ( IllegalStateException stateException ) {
			System.err.println( "Erro ao ler o arquivo." );
	        System.exit( 1 );
	    } 		
	} 
	
	//Alimenta o vetor de senha e de login
	public void alimentarVetor() {
		int i=0;
		try{
			while (input.hasNext()) {
				setLogin(input.next());
				setSenha(input.nextInt());	
				vetorLogin[i] = getLogin();
				vetorSenha[i] = getSenha();
				i++;
			} 			
		} 
		catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} 
		catch (IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		} 
	} 	 
	
	//Busca binária
	public static int binaria(int v[], int find) {
		int ini = 0, meio, fim = v.length -1;
		do {
			meio = (ini + fim) / 2;
			if      (v[meio] > find) fim = meio - 1;
			else if (v[meio] < find) ini = meio + 1;
			else                     return meio;
		} while (ini <= fim);
		return -1;
	}
	
	//Ordenação 
	public static void bubble(String login[], int senha[]) {
		  int ini, fim, aux;
		  String aux2;
		  for (ini = 0; ini < senha.length - 1; ini++) {
		     for (fim = senha.length - 1; fim > ini; fim--) {
		        if (senha[fim] < senha[fim-1]) {
		           aux      	= senha[fim];
		           senha[fim]   = senha[fim-1];
		           senha[fim-1] = aux;
		           
		           aux2      	= login[fim];
		           login[fim]   = login[fim-1];
		           login[fim-1] = aux2;
		        }
		     }
		  }
	}		
	//********************FIM DOS COMANDO PARA LOGIN*********************//
	
	//********************MANTER USUARIO*********************************//
	//CADASTRAR
	public boolean cadastrar(int id, String nome, String login, int senha, String rg, String cpf, int tipo) {
		String sqlInsert = "INSERT INTO Usuario(idUsuario, nome, login, senha, rg, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stm = null;
		Connection conn = null;
		        
		try {			
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlInsert);
			stm.setInt		(1, id);
			stm.setString	(2, nome);
			stm.setString	(3, login);
			stm.setInt		(4, senha);
			stm.setString	(5, rg);
			stm.setString	(6, cpf);
			stm.setInt		(7, tipo);
			
			//cadastrar no arquivo acesso.txt
			setLogin(login);
			setSenha(senha);
			cadastrarLogin();
			
			stm.execute();
			conn.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
		finally {
			if (stm != null) {
				try {
					stm.close();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//EXCLUIR
	public boolean excluir(int idUsuario) {
		String sqlDelete = "DELETE FROM Usuario WHERE idUsuario = " + idUsuario;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);
			
			stm = conn.prepareStatement(sqlDelete);
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		
		return false;
	}
	
	//ALTERAR
	public boolean alterar(int idUsuario) {		
		String sqlAlter = "UPDATE Usuario SET "
						+ "nome = ?, login = ?, senha = ?, rg = ?, cpf = ?, tipo = ? WHERE idUsuario = " + idUsuario;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);						
			stm = conn.prepareStatement(sqlAlter);
			stm.setString (1, getNome());
			stm.setString (2, getLogin());
			stm.setInt (3, getSenha());
			stm.setString (4, getRg());
			stm.setString (5, getCpf());
			stm.setInt (6, getTipo());
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//CONSULTAR COM CÓDIGO
	public List<Usuario> consultar(int idUsuario) {		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sqlSelect = "SELECT * FROM USUARIO WHERE idUsuario = " + idUsuario;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Usuario usuario = new Usuario();
				 usuario.setId(rs.getInt("idUsuario"));
				 usuario.setNome(rs.getString("nome"));
				 usuario.setLogin(rs.getString("login"));
				 usuario.setSenha(rs.getInt("senha"));
				 usuario.setRg(rs.getString("rg"));
				 usuario.setCpf(rs.getString("cpf"));
				 usuario.setTipo(rs.getInt("tipo"));
				 usuarios.add(usuario);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 /*for(Usuario usuario : usuarios) {
				  System.out.println(usuario.getNome() + " " + usuario.getLogin());
				}*/
			 return usuarios;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return usuarios;
	}
	
	//CONSULTAR COM NOME
	public List<Usuario> consultar(String nomeUsuario) {		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sqlSelect = "SELECT * FROM USUARIO WHERE nome = '" + nomeUsuario + "'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Usuario usuario = new Usuario();
				 usuario.setId(rs.getInt("idUsuario"));
				 usuario.setNome(rs.getString("nome"));
				 usuario.setLogin(rs.getString("login"));
				 usuario.setSenha(rs.getInt("senha"));
				 usuario.setRg(rs.getString("rg"));
				 usuario.setCpf(rs.getString("cpf"));
				 usuario.setTipo(rs.getInt("tipo"));
				 usuarios.add(usuario);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 /*for(Usuario usuario : usuarios) {
				  System.out.println(usuario.getNome() + " " + usuario.getLogin());
				}*/
			 return usuarios;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return usuarios;
	}
	
	//CONSULTAR COM LOGIN
	public List<Usuario> consultarLogin(String loginUsuario) {		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sqlSelect = "SELECT * FROM USUARIO WHERE login = '" + loginUsuario + "'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Usuario usuario = new Usuario();
				 usuario.setId(rs.getInt("idUsuario"));
				 usuario.setNome(rs.getString("nome"));
				 usuario.setLogin(rs.getString("login"));
				 usuario.setSenha(rs.getInt("senha"));
				 usuario.setRg(rs.getString("rg"));
				 usuario.setCpf(rs.getString("cpf"));
				 usuario.setTipo(rs.getInt("tipo"));				
				 usuarios.add(usuario);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 
			 return usuarios;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return usuarios;
	}
	
	//CONSULTAR TODOS
	public List<Usuario> consultar() {		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sqlSelect = "SELECT * FROM USUARIO";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Usuario usuario = new Usuario();
				 usuario.setId(rs.getInt("idUsuario"));
				 usuario.setNome(rs.getString("nome"));
				 usuario.setLogin(rs.getString("login"));
				 usuario.setSenha(rs.getInt("senha"));
				 usuario.setRg(rs.getString("rg"));
				 usuario.setCpf(rs.getString("cpf"));
				 usuario.setTipo(rs.getInt("tipo"));
				 usuarios.add(usuario);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 /*for(Usuario usuario : usuarios) {
				  System.out.println(usuario.getNome() + " " + usuario.getLogin());
				}*/
			 return usuarios;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return usuarios;
	}	
}
