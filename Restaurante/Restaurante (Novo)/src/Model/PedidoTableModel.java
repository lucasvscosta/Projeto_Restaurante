package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Negocio.Pedido;


public class PedidoTableModel extends AbstractTableModel{
	// Lista de Sócios a serem exibidos na tabela
	private List<Pedido> linhas;
	private ResourceBundle	bn=null;
	
    private String[] colunas = new String[] { "ID Pedido", "Hora Entrada", "Hora Sáida",
    											"Data Pedido", "Mesa", "ID Usuário"};
    
    // Constantes representando o índice das colunas
    private static final int IDPEDIDO = 0;
    private static final int HORAENTRADA = 1;
    private static final int HORASAIDA = 2;
    private static final int DATAPEDIDO = 3;
    private static final int NUMMESA = 4;
    private static final int IDUSUARIO = 5;
	
    // Cria um PEDIDO sem nenhuma linha
    public PedidoTableModel(ResourceBundle bundle) {
    	bn = bundle;
        linhas = new ArrayList<Pedido>();
    }
    
    // Cria um PEDIDO contendo a lista recebida por parâmetro
    public PedidoTableModel(List<Pedido> listaDePedidos, ResourceBundle bundle) {
    	bn = bundle;
        linhas = new ArrayList<Pedido>(listaDePedidos);
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
		Pedido pedido = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    //case PRODUTO:
	    	//conta.setValor((Double) aValue);
	        //break;
	    case IDPEDIDO:
	    	pedido.setIdPedido((Integer) aValue);
	        break;
	    case HORAENTRADA:
	    	pedido.setHoraEntrada((String) aValue);
	        break;
	    case HORASAIDA:
    		pedido.setHoraSaida((String) aValue);
    		break;
	    case DATAPEDIDO:
    		pedido.setDataPedido((String) aValue);
    		break;
	    case NUMMESA:
    		pedido.setNumMesa((Integer) aValue);
    		break;
	    case IDUSUARIO:
    		pedido.setIdGarcom((Integer) aValue);
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
		Pedido pedido = linhas.get(rowIndex);    
	    
	    switch (columnIndex) {
	    //case PRODUTO:
	    	//return conta.getProduto();
	    case IDPEDIDO:
            return pedido.getIdPedido();
	    case HORAENTRADA:
	        return pedido.getHoraEntrada();
	    case HORASAIDA:
	        return pedido.getHoraSaida();
	    case DATAPEDIDO:
	        return pedido.getDataPedido();
	    case NUMMESA:
	        return pedido.getNumMesa();
	    case IDUSUARIO:
	        return pedido.getIdGarcom();
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
	    
	    //case PRODUTO:
	        //return Integer.class;
	    case IDPEDIDO:
	        return Integer.class;
	    case HORAENTRADA:
            return String.class;
	    case HORASAIDA:
            return String.class;
	    case DATAPEDIDO:
            return String.class;
	    case NUMMESA:
	        return Integer.class;
	    case IDUSUARIO:
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
	//MÉTODOS PARA MANIPULAÇÃO DO PEDIDO
	// Retorna o usuario referente a linha especificada
	public Pedido getPedido(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o PEDIDO especificado ao modelo
	public void addNota(Pedido pedido) {
	    // Adiciona o registro.
	    linhas.add(pedido);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	
	// Remove o pedido da linha especificada.
	public void removePedido(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 		 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de PEDIDO.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}

}

