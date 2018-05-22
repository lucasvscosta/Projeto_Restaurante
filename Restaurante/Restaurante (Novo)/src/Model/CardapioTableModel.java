package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Negocio.Cardapio;

public class CardapioTableModel extends AbstractTableModel{
	// Lista de S�cios a serem exibidos na tabela
	private List<Cardapio> linhas;
	private ResourceBundle	bn=null;
	
	// Array com os nomes das colunas.
    private String[] colunas;
    
    // Constantes representando o �ndice das colunas
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
    
    // Cria um UsuarioTableModel contendo a lista recebida por par�metro
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
	    // Pega o s�cio referente a linha especificada.
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
	        // N�o deve ocorrer, pois s� existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o s�cio referente a linha especificada.
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
	        // N�o deve ocorrer, pois s� existem 2 colunas
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
	        // N�o deve ocorrer, pois s� existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // apenas o campo "ATIVO" ser� edit�vel
        //return columnIndex == ATIVO;
		
		//nenhum campo edit�vel
		//return false;
		
		//todos os campos serao editaveis
		return true;
    }
	
	
	//**********************************************************************************
	//M�TODOS PARA MANIPULA��O DO S�CIO
	// Retorna o usuario referente a linha especificada
	public Cardapio getCardapio(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o s�cio especificado ao modelo
	public void addCardapio(Cardapio cardapio) {
	    // Adiciona o registro.
	    linhas.add(cardapio);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o s�cio da linha especificada.
	public void removeCardapio(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de s�cios no final da lista.
	public void addListaDeCardapios (List<Cardapio> cardapios) {
	    // Pega o tamanho antigo da tabela, que servir�
	    // como �ndice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(cardapios);
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(indice, indice + cardapios.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de s�cios.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}
}
