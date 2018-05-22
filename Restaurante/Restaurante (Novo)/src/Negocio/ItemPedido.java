package Negocio;

import DAO.ItemPedidoDAO;

public class ItemPedido {
	private int quantidade;
	private ItemPedidoDAO item;
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//CADASTRAR
	public boolean cadastrarItemPedido(int numMesa, int idProduto){
		item = new ItemPedidoDAO();			
        return item.cadastrar(getQuantidade(), numMesa, idProduto);	
	}	
}
