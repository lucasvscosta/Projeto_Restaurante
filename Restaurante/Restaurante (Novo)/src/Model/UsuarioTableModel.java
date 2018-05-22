package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Negocio.Usuario;

public class UsuarioTableModel extends AbstractTableModel{
	// Lista de S�cios a serem exibidos na tabela
	private List<Usuario> linhas;	
	private ResourceBundle	bn=null;
    
	private String[] colunas;
	
    // Constantes representando o �ndice das colunas
    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int LOGIN = 2;
    private static final int SENHA = 3;
    private static final int RG = 4;
    private static final int CPF = 5;
    private static final int TIPO = 6;
	
    // Cria um UsuarioTableModel sem nenhuma linha
    public UsuarioTableModel(ResourceBundle bundle) {
    	bn = bundle;
    	// Array com os nomes das colunas.
        colunas = new String[] { "Id", "Nome", "Login", "Senha", "RG", "CPF", "Tipo" };
        linhas = new ArrayList<Usuario>();
    }
    
    // Cria um UsuarioTableModel contendo a lista recebida por par�metro
    public UsuarioTableModel(List<Usuario> listaDeUsuarios, ResourceBundle bundle) {
    	bn = bundle;
    	colunas = new String[] { "Id", "Nome", "Login", "Senha", "RG", "CPF", "Tipo" };
        linhas = new ArrayList<Usuario>(listaDeUsuarios);
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
	    Usuario usuario= linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
	    	usuario.setId((Integer) aValue);
	        break;
	    case NOME:
	    	usuario.setNome((String) aValue);
	        break;
	    case LOGIN:
	    	usuario.setLogin((String) aValue);
	        break;
	    case SENHA:
	    	usuario.setSenha((Integer) aValue);
	        break;
	    case RG:
	    	usuario.setRg((String) aValue);
            break;
	    case CPF:
	    	usuario.setCpf((String) aValue);
            break;
	    case TIPO:
	    	usuario.setTipo((Integer) aValue);
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
	    Usuario usuario = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
            return usuario.getId();
	    case NOME:
	        return usuario.getNome();
	    case LOGIN:
	        return usuario.getLogin();
	    case SENHA:
            return usuario.getId();
	    case RG:
            return usuario.getRg();
	    case CPF:
            return usuario.getCpf();
	    case TIPO:
            return usuario.getTipo();
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
	    case LOGIN:
            return String.class;
	    case SENHA:
            return Integer.class;
	    case RG:
            return String.class;
	    case CPF:
            return String.class;
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
	public Usuario getUsuario(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o s�cio especificado ao modelo
	public void addUsuario(Usuario usuario) {
	    // Adiciona o registro.
	    linhas.add(usuario);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o �ltimo �ndice. A subtra��o � necess�ria
	    // porque os �ndices come�am em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o s�cio da linha especificada.
	public void removeUsuario(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudan�a.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de s�cios no final da lista.
	public void addListaDeUsuarios (List<Usuario> usuarios) {
	    // Pega o tamanho antigo da tabela, que servir�
	    // como �ndice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(usuarios);
	 
	    // Notifica a mudan�a.
	    fireTableRowsInserted(indice, indice + usuarios.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de s�cios.
	    linhas.clear();
	 
	    // Notifica a mudan�a.
	    fireTableDataChanged();
	}

}
