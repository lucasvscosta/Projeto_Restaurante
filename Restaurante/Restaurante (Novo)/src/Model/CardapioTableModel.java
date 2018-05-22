package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Negocio.Cardapio;

public class CardapioTableModel extends AbstractTableModel{
	// Lista de Sócios a serem exibidos na tabela
	private List<Cardapio> linhas;
	private ResourceBundle	bn=null;
	
	// Array com os nomes das colunas.
    private String[] colunas;
    
    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int DESCRICAO = 2;
    private static final int VALOR = 3;
    private static final int DISPONIBILIDADE = 4;
    private static final int TIPO = 5;
	
    // Cria um UsuarioTableModel sem nenhuma linha
    public CardapioTableModel(ResourceBundle bundle) {
    	bn = bundle;
    	colunas = new String[]  
			{	bn.getString("columnID"), 
				bn.getString("columnNome"), 
				bn.getString("columnDescricao"),
				bn.getString("columnValorUnitario"), 
				bn.getString("columnDisponibilidade"), 
				bn.getString("columnTipo") 
			};
        linhas = new ArrayList<Cardapio>();
         
    }
    
    // Cria um UsuarioTableModel contendo a lista recebida por parâmetro
    public CardapioTableModel(List<Cardapio> listaDeCardapios, ResourceBundle bundle) {
    	bn = bundle;
    	colunas = new String[]  
    			{	bn.getString("columnID"), 
					bn.getString("columnNome"), 
					bn.getString("columnDescricao"),
					bn.getString("columnValorUnitario"), 
					bn.getString("columnDisponibilidade"), 
					bn.getString("columnTipo")  
    			};
        linhas = new ArrayList<Cardapio>(listaDeCardapios);         
    }
    
    @Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Cardapio pruduto = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
	    	pruduto.setIdProduto((Integer) aValue);
	        break;
	    case NOME:
	    	pruduto.setNomeProduto((String) aValue);
	        break;
	    case DESCRICAO:
	    	pruduto.setDescricaoProduto((String) aValue);
	        break;
	    case VALOR:
	    	pruduto.setValorUnitarioProduto((Double) aValue);
	        break;
	    case DISPONIBILIDADE:
	    	pruduto.setDisponibilidadeProduto((Boolean) aValue);
            break;
	    case TIPO:
	    	pruduto.setTipoProduto((Integer) aValue);
            break;
	    default:
	        // Não deve ocorrer, pois só existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Cardapio produto = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
            return produto.getIdProduto();
	    case NOME:
	        return produto.getNomeProduto();
	    case DESCRICAO:
	        return produto.getDescricaoProduto();
	    case VALOR:
            return produto.getValorUnitarioProduto();
	    case DISPONIBILIDADE:
            return produto.isDisponibilidadeProduto();
	    case TIPO:
            return produto.getTipoProduto();
	    default:
	        // Não deve ocorrer, pois só existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	}	
	
	@Override
	public String getColumnName(int columnIndex) {
	    return colunas[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    switch (columnIndex) {
	    case ID:
	        return Integer.class;
	    case NOME:
	        return String.class;
	    case DESCRICAO:
            return String.class;
	    case VALOR:
            return Double.class;
	    case DISPONIBILIDADE:
            return Boolean.class;
	    case TIPO:
            return Integer.class;
	    default:
	        // Não deve ocorrer, pois só existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // apenas o campo "ATIVO" será editável
        //return columnIndex == ATIVO;
		
		//nenhum campo editável
		//return false;
		
		//todos os campos serao editaveis
		return true;
    }
	
	
	//**********************************************************************************
	//MÉTODOS PARA MANIPULAÇÃO DO SÓCIO
	// Retorna o usuario referente a linha especificada
	public Cardapio getCardapio(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o sócio especificado ao modelo
	public void addCardapio(Cardapio cardapio) {
	    // Adiciona o registro.
	    linhas.add(cardapio);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o sócio da linha especificada.
	public void removeCardapio(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de sócios no final da lista.
	public void addListaDeCardapios (List<Cardapio> cardapios) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(cardapios);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + cardapios.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de sócios.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}
}
