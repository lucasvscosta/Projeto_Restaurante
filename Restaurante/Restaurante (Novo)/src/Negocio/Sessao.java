package Negocio;

public class Sessao {
	//vari�veis est�ticas n�o s�o alteradas quando instanciado um objeto
	private static int idUsuarioLogado;

	public int getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(int idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
}
