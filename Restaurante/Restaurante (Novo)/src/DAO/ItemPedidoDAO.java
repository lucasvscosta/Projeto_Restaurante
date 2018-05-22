package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Pedido;

public class ItemPedidoDAO {
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//********************MANTER PEDIDO*********************************//
	public int pegarIdPedido(int numMesa){
		String sqlSelect = 
				"SELECT idPedido FROM PEDIDO WHERE (horaEntrada IS NOT NULL AND horaSaida IS NULL) AND numMesa = " + numMesa;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  				 
				 return rs.getInt("idPedido");
			 } 
			 rs.close();
			 conn.commit();
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return 0;
	}
	//CADASTRAR
	public boolean cadastrar(int numMesa, int quantidade, int idProduto) {
		
		String sqlInsert = "INSERT INTO ITEMPEDIDO(quantidadeItem, id_pedido, id_produto) VALUES (?, ?, ?)";
		PreparedStatement stm = null;
		Connection conn = null;
		        
		try {			
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlInsert);
			stm.setInt	(1, quantidade);
			stm.setInt	(2, pegarIdPedido(numMesa));
			stm.setInt	(3, idProduto);
						
			stm.execute();
			conn.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
		finally {
			if (stm != null) {
				try {
					stm.close();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//EXCLUIR
	public boolean excluir(int idProduto) {
		String sqlDelete = "DELETE FROM ITEMPEDIDO WHERE idPedido = " + idProduto;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);
			
			stm = conn.prepareStatement(sqlDelete);
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		
		return false;
	}
	
	//ALTERAR
	public boolean alterar(int numMesa, int idProduto, int idPedido) {		
		String sqlAlter = "UPDATE ITEMPEDIDO SET "
						+ "quantidadeItem = ?, idPedido= ?, idProduto = ? WHERE idPedido = " + pegarIdPedido(numMesa);
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);						
			stm = conn.prepareStatement(sqlAlter);
			stm.setInt (1, getQuantidade());
			stm.setInt (2, idPedido);
			stm.setInt (3, idProduto);
			stm.execute();
			conn.commit();
			return true;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return false;
	}
	
	//CONSULTAR COM CÓDIGO
	public List<Pedido> consultar(int idPedido) {		
		List itens = new ArrayList<>();
		String sqlSelect = "SELECT * FROM ITEMPEDIDO WHERE idPedido = " + idPedido;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 ArrayList list = new ArrayList();
				 list.add(rs.getInt("idItemPedido"));
				 list.add(rs.getInt("quantidadeItem"));
				 list.add(rs.getInt("id_Pedido"));
				 list.add(rs.getInt("id_Produto"));
				 itens.add(list);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 return itens;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return itens;
	}
	
	//CONSULTAR COM NOME
	public List<Pedido> consultarNumMesa(int numMesa) {		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String sqlSelect = "SELECT * FROM PEDIDO WHERE numMesa = '" + numMesa + "'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Pedido pedido = new Pedido();
				 pedido.setIdPedido(rs.getInt("idPedido"));
				 pedido.setHoraEntrada(rs.getString("horaEntrada"));
				 pedido.setHoraSaida(rs.getString("horaSaida"));
				 pedido.setDataPedido(rs.getString("dataPedido"));
				 pedido.setNumMesa(rs.getInt("numMesa"));
				 pedido.setIdGarcom(rs.getInt("id_usuario"));
				 pedidos.add(pedido);			 	   
			 } 
			 rs.close();
			 conn.commit();
			 return pedidos;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return pedidos;
	}
	
	//CONSULTAR TODOS
	public List<Pedido> consultar() {		
		List<Pedido> pedidos = new ArrayList<Pedido>();

		String sqlSelect = "SELECT * FROM PEDIDO";
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Pedido pedido = new Pedido();
				 pedido.setIdPedido(rs.getInt("idPedido"));
				 pedido.setHoraEntrada(rs.getString("horaEntrada"));
				 pedido.setHoraSaida(rs.getString("horaSaida"));
				 pedido.setDataPedido(rs.getString("dataPedido"));
				 pedido.setNumMesa(rs.getInt("numMesa"));
				 pedido.setIdGarcom(rs.getInt("id_usuario"));
				 pedidos.add(pedido);			 	   
			 } 
			 rs.close();
			 conn.commit();
			 /*for(Usuario usuario : usuarios) {
				  System.out.println(usuario.getNome() + " " + usuario.getLogin());
				}*/
			 return pedidos;
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return pedidos;
	}
	
	/*public boolean checarDispMesa(int numMesa) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String sqlSelect = 
				"SELECT max(idPedido), horaEntrada, horaSaida, dataPedido, numMesa, id_usuario FROM PEDIDO WHERE numMesa = " + numMesa;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
						
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery(sqlSelect); 		     
			    
			 while(rs.next()) {  
				 Pedido pedido = new Pedido();
				 pedido.setIdPedido(rs.getInt("max(idPedido)"));
				 pedido.setHoraEntrada(rs.getString("horaEntrada"));
				 pedido.setHoraSaida(rs.getString("horaSaida"));
				 pedido.setDataPedido(rs.getString("dataPedido"));
				 pedido.setNumMesa(rs.getInt("numMesa"));
				 pedido.setIdGarcom(rs.getInt("id_usuario"));
				 pedidos.add(pedido);			 	   
			 } 
			 rs.close();
			 conn.commit();
			 for(Pedido pedido : pedidos) {
				 if(pedido.getHoraEntrada() != null && (pedido.getHoraSaida() == null)){
					 return false;
				 } else {
					 return true;
				 }
			 }
		}
			catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		finally {
			if (stm != null) {
				try {
					stm.close();
			  }
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return true;
	}
	*/
	
	
	
	/*public static void main(String args[]){
		PedidoDAO pe = new PedidoDAO();
		Date hora = Calendar.getInstance().getTime();  
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String horaFormatada = sdf.format(hora);
		
		pe.cadastrar( horaFormatada, 5, 1);		
	}*/
}
