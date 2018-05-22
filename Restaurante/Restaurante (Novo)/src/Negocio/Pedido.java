package Negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.PedidoDAO;
import DAO.UsuarioDAO;

public class Pedido {
	private int idPedido, idGarcom, numMesa;
	private String horaEntrada, horaSaida, dataPedido;
	private PedidoDAO pedido;
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdGarcom() {
		return idGarcom;
	}
	public void setIdGarcom(int idGarcom) {
		this.idGarcom = idGarcom;
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
	
	//CADASTRAR
	public boolean cadastrarPedido(){
		pedido = new PedidoDAO();			
        return pedido.cadastrar(getHoraEntrada(), getNumMesa(), getIdGarcom());	
	}	
	
	//CONSULTAR COM ID TABLE
	public List<Pedido> consultarPedido(int idPedido) {
		pedido = new PedidoDAO();	
		return pedido.consultar(idPedido);
	}
	
	//CONSULTAR COM MESA TABLE
	public List<Pedido> consultarMesaPedido(int numMesa) {
		pedido = new PedidoDAO();		
		return pedido.consultarNumMesa(numMesa);
	}
	
	//CONSULTAR TODOS TABLE
	public List<Pedido> consultarPedido() {
		pedido = new PedidoDAO();		        
        /*for(List<Pedido> pedido : pedido.consultar()) {
			  System.out.println(pedido.getIdPedido() + " " + pedido.getNumMesa());
		}*/
        return pedido.consultar();
	}
	
	//EXCLUIR
	public boolean excluirPedido(int idPedido){
		pedido = new PedidoDAO();			  
        return pedido.excluir(idPedido);	
	}
	
	//ALTERAR
	public boolean alterarPedido(int idPedido){
		pedido = new PedidoDAO();			
		pedido.setHoraEntrada(getHoraEntrada());
		pedido.setHoraSaida(getHoraSaida());
		pedido.setDataPedido(getDataPedido());
		pedido.setNumMesa(getNumMesa());
		pedido.setIdUsuario(getIdGarcom());
        return pedido.alterar(idPedido);	
	}
	
	//CHECAR DISPONIBILIDADE DA MESA
	public boolean checarDispMesa(int numMesa){
		pedido = new PedidoDAO();			
        return pedido.checarDispMesa(numMesa);	
	}
}
