package Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import DAO.UsuarioDAO;

public class Usuario {
	
	//atributos do usuario
	private int				id;
	private String 			nome;
	private String 			login;
	private int 			senha;
	private int				tipo;
	private String 			rg;
	private String 			cpf;
	
	//demais variáveis
	private int 			tamanho;
	private Scanner 		input;
	private static int    	vetorSenha[];
	private static String  	vetorLogin[];

	UsuarioDAO usuario;
	
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

	//VALIDAR LOGIN
	public boolean validarLogin(String login, int senha){
		usuario = new UsuarioDAO();
		usuario.abrirArquivo();
		if(usuario.validarLogin(login, senha)){
			setLogin(login);
			setSenha(senha);
			usuario.fecharArquivo();
			return true;			
		}
		usuario.fecharArquivo();		
		return false;		
	}
	
	//CADASTRAR
	public boolean cadastrarUsuario(){
		usuario = new UsuarioDAO();			         
        return usuario.cadastrar(getId(), getNome(), getLogin(), getSenha(), getRg(), getCpf(), getTipo());	
	}	
	
	//CONSULTAR COM ID TABLE
	public List<Usuario> consultarUsuario(int idUsuario) {
		usuario = new UsuarioDAO();	
		return usuario.consultar(idUsuario);
	}
	
	//CONSULTAR COM ID TABLE
	public List<Usuario> consultarUsuario(String nome) {
		usuario = new UsuarioDAO();		
		return usuario.consultar(nome);
	}
	
	//CONSULTAR TODOS TABLE
	public List<Usuario> consultarUsuario() {
		usuario = new UsuarioDAO();		        
        for(Usuario usuario : usuario.consultar()) {
			  System.out.println(usuario.getNome() + " " + usuario.getLogin());
		}
        return usuario.consultar();
	}
	
	//EXCLUIR
	public boolean excluirUsuario(int idUsuario){
		usuario = new UsuarioDAO();			  
        return usuario.excluir(idUsuario);	
	}
	
	//ALTERAR
	public boolean alterarUsuario(int idUsuario){
		usuario = new UsuarioDAO();			  
		usuario.setId(getId());
		usuario.setNome(getNome());
		usuario.setLogin(getLogin());
		usuario.setSenha(getSenha());
		usuario.setRg(getRg());
		usuario.setCpf(getCpf());
		usuario.setTipo(getTipo());
        return usuario.alterar(idUsuario);	
	}	
	
	
}
