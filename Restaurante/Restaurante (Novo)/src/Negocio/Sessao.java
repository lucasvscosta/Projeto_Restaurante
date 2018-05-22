package Negocio;

public class Sessao {
	//variáveis estáticas não são alteradas quando instanciado um objeto
	private static int idUsuarioLogado;

	public int getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(int idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
}
