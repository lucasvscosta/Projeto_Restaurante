package Negocio;

import java.util.List;

import DAO.CardapioDAO;

public class Cardapio {
	private String nomeProduto, descricaoProduto;
	private int idProduto, tipoProduto;
	private double valorUnitarioProduto;
	private boolean disponibilidadeProduto;
	private CardapioDAO cardapio;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public int getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(int tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public double getValorUnitarioProduto() {
		return valorUnitarioProduto;
	}
	public void setValorUnitarioProduto(double valorUnitarioProduto) {
		this.valorUnitarioProduto = valorUnitarioProduto;
	}
	public boolean isDisponibilidadeProduto() {
		return disponibilidadeProduto;
	}
	public void setDisponibilidadeProduto(boolean disponibilidadeProduto) {
		this.disponibilidadeProduto = disponibilidadeProduto;
	}
	
	//CADASTRAR
	public boolean cadastrarCardapio(){
		cardapio = new CardapioDAO();			         
        return cardapio.cadastrar(getIdProduto(), getNomeProduto(), getDescricaoProduto(), getValorUnitarioProduto(), isDisponibilidadeProduto(), getTipoProduto());	
	}	
	
	//CONSULTAR COM ID TABLE
	public List<Cardapio> consultarCardapio(int idProduto) {
		cardapio = new CardapioDAO();
		return cardapio.consultar(idProduto);
	}
	
	//CONSULTAR COM NOME TABLE
	public List<Cardapio> consultarCardapio(String nome) {
		cardapio = new CardapioDAO();		
		return cardapio.consultar(nome);
	}
	
	//CONSULTAR TODOS TABLE
	public List<Cardapio> consultarCardapio() {
		System.out.println("AQUI");
		cardapio = new CardapioDAO();		        
        for(Cardapio cardapio : cardapio.consultar()) {
			  System.out.println(cardapio.getNomeProduto());
		}
        return cardapio.consultar();
	}
	
	//EXCLUIR
	public boolean excluirUsuario(int idProduto){
		cardapio = new CardapioDAO();			  
        return cardapio.excluir(idProduto);	
	}
	
	//ALTERAR
	public boolean alterarCardapio(int idProduto){
		cardapio = new CardapioDAO();			  
		cardapio.setIdProduto(getIdProduto());
		cardapio.setNomeProduto(getNomeProduto());
		cardapio.setDescricaoProduto(getDescricaoProduto());
		cardapio.setValorUnitarioProduto(getValorUnitarioProduto());
		cardapio.setDisponibilidadeProduto(isDisponibilidadeProduto());		
		cardapio.setTipoProduto(getTipoProduto());
        return cardapio.alterar(idProduto);	
	}

}
