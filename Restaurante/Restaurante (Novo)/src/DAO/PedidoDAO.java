package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Negocio.Pedido;
import Negocio.Usuario;

public class PedidoDAO {
	private int idPedido, idUsuario, numMesa;
	private String horaEntrada, horaSaida, dataPedido;
	private Usuario usuario;
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getNumMesa() {
		return numMesa;
	}
	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}	
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public PedidoDAO(){
				
	}
	
	//********************MANTER PEDIDO*********************************//
	//CADASTRAR
	public boolean cadastrar(String horaEntrada, int numMesa, int idGarcom) {
		String sqlInsert = "INSERT INTO PEDIDO(horaEntrada, numMesa, id_usuario) VALUES (?, ?, ?)";
		PreparedStatement stm = null;
		Connection conn = null;
		        
		try {			
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlInsert);
			stm.setString	(1, horaEntrada);
			stm.setInt		(2, numMesa);
			stm.setInt		(3, idGarcom);
						
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
	public boolean excluir(int idPedido) {
		String sqlDelete = "DELETE FROM PEDIDO WHERE idPedido = " + idPedido;
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
	public boolean alterar(int idPedido) {		
		String sqlAlter = "UPDATE PEDIDO SET "
						+ "horaEntrada = ?, horaSaida= ?, dataPedido = ?, numMesa = ? id_usuario = ? WHERE idPedido = " + idPedido;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);						
			stm = conn.prepareStatement(sqlAlter);
			stm.setString (1, getHoraEntrada());
			stm.setString (2, getHoraSaida());
			stm.setString (3, getDataPedido());
			stm.setInt	  (4, getNumMesa());
			stm.setInt	  (5, getIdUsuario());
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
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String sqlSelect = "SELECT idPedido, horaEntrada, horaSaida, dataPedido, numMesa, id_usuario, us.nome " 
							+ " FROM PEDIDO AS pe " 
							+ " INNER JOIN USUARIO AS US ON US.idUsuario = pe.id_Usuario " 
							+ " WHERE idPedido = "+ idPedido;
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
	
	//CONSULTAR COM numMesa
	public List<Pedido> consultarNumMesa(int numMesa) {		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String sqlSelect = "SELECT idPedido, horaEntrada, horaSaida, dataPedido, numMesa, id_usuario, us.nome " 
							+ " FROM PEDIDO AS pe " 
							+ " INNER JOIN USUARIO AS US ON US.idUsuario = pe.id_Usuario " 
							+ " WHERE numMesa = " + numMesa;
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
	
	public boolean checarDispMesa(int numMesa) {
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
	
	
	
	
	/*public static void main(String args[]){
		PedidoDAO pe = new PedidoDAO();
		Date hora = Calendar.getInstance().getTime();  
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String horaFormatada = sdf.format(hora);
		
		pe.cadastrar( horaFormatada, 5, 1);		
	}*/
	
}
