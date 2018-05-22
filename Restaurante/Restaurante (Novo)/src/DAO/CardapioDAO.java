package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Cardapio;
import Negocio.Usuario;

public class CardapioDAO {
	private String nomeProduto, descricaoProduto;
	private int tipoProduto;
	private int idProduto;
	private double valorUnitarioProduto;
	private boolean disponibilidadeProduto;
	
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
	
	//********************MANTER CARDAPIO*********************************//
	//CADASTRAR
	public boolean cadastrar(int id, String nome, String descricao, double valor, boolean disponibilidade, int tipo) {
		
		String sqlInsert = 
				"INSERT INTO CARDAPIO(idProduto, nomeProduto, descricaoProduto, valorUnitarioProduto, disponibilidadeProduto, tipoProduto) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stm = null;
		Connection conn = null;
		        
		try {			
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlInsert);
			stm.setInt		(1, id);
			stm.setString	(2, nome);
			stm.setString	(3, descricao);
			stm.setDouble	(4, valor);
			stm.setBoolean	(5, disponibilidade);
			stm.setInt		(6, tipo);
						
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
		String sqlDelete = "DELETE FROM Cardapio WHERE idProduto = " + idProduto;
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
	public boolean alterar(int idProduto) {		
		String sqlAlter = "UPDATE CARDAPIO SET "
						+ "nomeProduto = ?, descricaoProduto = ?, valorUnitarioProduto = ?, disponibilidadeProduto = ?, tipoProduto = ? WHERE idProduto = " + idProduto;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
			// obtem conexao com o banco			
			AcessoBD bd = new AcessoBD();
			conn = bd.obtemConexao();
			// *** IMPORTANTE ***: Força o uso de transação.
			conn.setAutoCommit(false);						
			stm = conn.prepareStatement(sqlAlter);
			stm.setString (1, getNomeProduto());
			stm.setString (2, getDescricaoProduto());
			stm.setDouble (3, getValorUnitarioProduto());
			stm.setBoolean(4, isDisponibilidadeProduto());
			stm.setInt	  (5, getTipoProduto());
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
	public List<Cardapio> consultar(int idProduto) {		
		List<Cardapio> produtos = new ArrayList<Cardapio>();
		String sqlSelect = "SELECT * FROM CARDAPIO WHERE idProduto = " + idProduto;
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
				 Cardapio cardapio = new Cardapio();
				 cardapio.setIdProduto(rs.getInt("idProduto"));
				 cardapio.setNomeProduto(rs.getString("nomeProduto"));
				 cardapio.setDescricaoProduto(rs.getString("descricaoProduto"));
				 cardapio.setValorUnitarioProduto(rs.getInt("valorUnitarioProduto"));
				 cardapio.setDisponibilidadeProduto(rs.getBoolean("disponibilidadeProduto"));
				 cardapio.setTipoProduto(rs.getInt("tipoProduto"));
				 produtos.add(cardapio);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 return produtos;
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
		return produtos;
	}
	
	//CONSULTAR COM NOME
	public List<Cardapio> consultar(String nomeProduto) {		
		List<Cardapio> produtos = new ArrayList<Cardapio>();
		String sqlSelect = "SELECT * FROM CARDAPIO WHERE nomeProduto = '" + nomeProduto + "'";
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
				 Cardapio cardapio = new Cardapio();
				 cardapio.setIdProduto(rs.getInt("idProduto"));
				 cardapio.setNomeProduto(rs.getString("nomeProduto"));
				 cardapio.setDescricaoProduto(rs.getString("descricaoProduto"));
				 cardapio.setValorUnitarioProduto(rs.getInt("valorUnitarioProduto"));
				 cardapio.setDisponibilidadeProduto(rs.getBoolean("disponibilidadeProduto"));
				 cardapio.setTipoProduto(rs.getInt("tipoProduto"));
				 produtos.add(cardapio);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 return produtos;
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
		return produtos;
	}
	
	//CONSULTAR TODOS
	public List<Cardapio> consultar() {		
		List<Cardapio> produtos = new ArrayList<Cardapio>();

		String sqlSelect = "SELECT * FROM CARDAPIO";
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
				 Cardapio cardapio = new Cardapio();
				 cardapio.setIdProduto(rs.getInt("idProduto"));
				 cardapio.setNomeProduto(rs.getString("nomeProduto"));
				 cardapio.setDescricaoProduto(rs.getString("descricaoProduto"));
				 cardapio.setValorUnitarioProduto(rs.getDouble("valorUnitarioProduto"));
				 cardapio.setDisponibilidadeProduto(rs.getBoolean("disponibilidadeProduto"));
				 cardapio.setTipoProduto(rs.getInt("tipoProduto"));
				 
				 produtos.add(cardapio);				 	   
			 } 
			 rs.close();
			 conn.commit();
			 
			 return produtos;
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
		return produtos;
	}	
	public static void main(String args[]){
		CardapioDAO ca = new CardapioDAO();
		ca.cadastrar(20, "Testeasd", "testedesc", 50.00, true, 2);		
	}
}
