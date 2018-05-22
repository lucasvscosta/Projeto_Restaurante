package Negocio;

public class Criptografia {
	
	//Atributos
	private int           	senha;
	private String		  	login;
	private static String 	chave = "123456789";
	private Usuario		  	usuario;		

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}		
	
	//Criptografar os dados
	public static int cifrar(int senha){
		int j=1;
		for(int i=0;i<9;i++){
			int f = Integer.parseInt(chave.substring(i, j));
			senha = (senha + (f * f) * 123456789);
			j++;			
			/* senhas decifradas
			 12310 (adm1)
			 12311 (adm2)
			 12312 (jeanzito)
			 12313 (calvettiNota10)
		     	12314 (calvettiDa10)
		     	12315 (zezinho)
		     	12316 (adm7)
		     	12317 (adm8)
		     	12318 (adm9) */
		}
		return senha;
	}
	
	//Descriptografar os dados
	public static int decifrar(int senha){
		int j=1;
		for(int i=0;i<9;i++){
			int f = Integer.parseInt(chave.substring(i, j));
			senha = (senha - (f * f) * 123456789);
			j++;			
			/* senhas cifradas
			 825458807
			 825458808
			 825458809
			 825458810
		     	825458811
		     	825458812
		     	825458813
		     	825458814
		     	825458815*/			
		}
		return senha;
	}
	
	public boolean validarLogin(String login, int senha){
		usuario = new Usuario();		
		if(usuario.validarLogin(login, cifrar(senha))){
			return true;
		}		
		return false;
	}	
}
