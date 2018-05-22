package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Negocio.Pedido;


public class PedidoTableModel extends AbstractTableModel{
	// Lista de S�cios a serem exibidos na tabela
	private List<Pedido> linhas;
	private ResourceBundle	bn=null;
	
    private String[] colunas = new String[] { "ID Pedido", "Hora Entrada", "Hora S�ida",
    											"Data Pedido", "Mesa", "ID Usu�rio"};
    
    // Constantes representando o �ndice das colunas
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
    
    // Cria um PEDIDO contendo a lista recebida por par�metro
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
	    // Pega o s�cio referente a linha especificada.
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
	        // N�o deve ocorrer, pois s� existem 2 colunas
	        throw new IndexOutOfBoundsException("columnIndex out of bounds");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o s�cio referente a linha especificada.
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
	//M�TODOS PARA MANIPULA��O DO PEDIDO
	// Retorna o usuario referente a linha especificada
	public Pedido getPedido(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o PEDIDO especificado ao modelo
	public void addNota(Pedido pedido) {
	    // Adiciona o registro.
	    linhas.add(pedido);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	
	// Remove o pedido da linha especificada.
	public void removePedido(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 		 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de PEDIDO.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}

}

