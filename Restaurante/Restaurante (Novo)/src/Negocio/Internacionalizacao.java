package Negocio;

import java.util.ResourceBundle;

import Interface.CaixaView;
import Interface.CardapioView;
import Interface.ContaView;
import Interface.LoginView;
import Interface.NotaFiscalView;
import Interface.PedidoView;
import Interface.RestauranteView;
import Interface.UsuarioView;

public class Internacionalizacao {
	
	private ResourceBundle	bn = null;
	
	RestauranteView restaurante;
	PedidoView pedido;
	CardapioView cardapio;
	LoginView login;
	CaixaView caixa;
	ContaView conta;
	NotaFiscalView notafiscal;
	UsuarioView usuario;
	
	public Internacionalizacao(){

        bn = ResourceBundle.getBundle("inter"); 
	}
		
	public void refreshIdiom(){
		
		/*tituloRestaurante.setText(bn.getString("tituloRestaurante"));
		tituloincluirCardapioView.setText(bn.getString("tituloIncluirCardapioView"));
		tituloVisualizarCardapioView.setText(bn.getString("tituloVisualizarCardapioView"));
		tituloEditarCardapioView.setText(bn.getString("tituloEditarCardapioView"));
		tituloFecharCaixa.setText(bn.getString("tituloFecharCaixa"));
		tituloCadastrarUsuario.setText(bn.getString("tituloCadastrarUsuario"));
		tituloConsultarUsuario.setText(bn.getString("tituloConsultarUsuario"));
		tituloFecharConta.setText(bn.getString("tituloFecharConta"));
		tituloAcesso.setText(bn.getString("tituloAcesso"));
		tituloEmitirNotaFiscal.setText(bn.getString("tituloEmitirNotaFiscal"));
		tituloFecharNotaFiscal.setText(bn.getString("tituloFecharNotaFiscal"));
		tituloConsultarNotaFiscal.setText(bn.getString("tituloConsultarNotaFiscal"));
		tituloPedidoEditarPedido.setText(bn.getString("tituloPedidoEditarPedido"));
		tituloPedidoSelecionarMesa.setText(bn.getString("tituloPedidoSelecionarMesa"));
		tituloPedidoEscolherProduto.setText(bn.getString("tituloPedidoEscolherProduto"));
		tituloPedidoPriorizarPedido.setText(bn.getString("tituloPedidoPriorizarPedido"));
		tituloPedidoFinalizarPrato.setText(bn.getString("tituloPedidoFinalizarPrato"));
		tituloConsultarHorarioDePico.setText(bn.getString("tituloConsultarHorarioDePico"));
		
		menuIdioma.setText(bn.getString("menuIdioma"));
		menuUsuario.setText(bn.getString("menuUsuario"));
		menuPedido.setText(bn.getString("menuPedido"));
		menuCaixa.setText(bn.getString("menuCaixa"));
		menuConta.setText(bn.getString("menuConta"));
		menuNotaFiscal.setText(bn.getString("menuNotaFiscal"));					
		menuCardapio.setText(bn.getString("menuCardapio"));
		menuPagamento.setText(bn.getString("menuPagamento"));
		menuCozinha.setText(bn.getString("menuCozinha"));

		itemIngles.setText(bn.getString("itemIngles"));
		itemPortugues.setText(bn.getString("itemPortugues"));
		itemEspanhol.setText(bn.getString("itemEspanhol"));
		itemCadastrarUsuario.setText(bn.getString("itemCadastrarUsuario"));
		itemConsultarUsuario.setText(bn.getString("itemConsultarUsuario"));
		itemFinalizarPrato.setText(bn.getString("itemFinalizarPrato"));
		itemListarPratosMaisVendidos.setText(bn.getString("itemListarPratosMaisVendidos"));	
		itemFecharNotaFiscal.setText(bn.getString("itemFecharNotaFiscal"));
		itemFecharConta.setText(bn.getString("itemFecharConta"));
		itemSair.setText(bn.getString("itemSair"));
		itemEmitirNotaFiscal.setText(bn.getString("itemEmitirNotaFiscal"));
		//itemPratosMaisVendidos.setText(bn.getString("itemPratosMaisVendidos"));
		itemPriorizarPedido.setText(bn.getString("itemPriorizarPedido"));
		itemConsultarHorarioDePico.setText(bn.getString("itemConsultarHorarioDePico"));
		itemIncluirPedido.setText(bn.getString("itemIncluirPedido"));
		itemEditarPedido.setText(bn.getString("itemEditarPedido"));
		itemFecharCaixa.setText(bn.getString("itemFecharCaixa"));
		itemConsultarNotaFiscal.setText(bn.getString("itemConsultarNotaFiscal"));
		itemIncluirCardapio.setText(bn.getString("itemIncluirCardapio"));
		itemEditarCardapio.setText(bn.getString("itemEditarCardapio"));
		/*
		lblUsuario.setText(bn.getString("lblUsuario"));
		lblSenha.setText(bn.getString("lblSenha"));
		lblNumeroNotaFiscal.setText(bn.getString("lblNumeroNotaFiscal"));
		lblNumeroPedido.setText(bn.getString("lblNumeroPedido"));
		lblCpfCnpj.setText(bn.getString("lblCpfCnpj"));
		labelNumPedido.setText(bn.getString("labelNumPedido"));
		labelNumProduto.setText(bn.getString("labelNumProduto"));
		labelQuantidade.setText(bn.getString("labelQuantidade"));
		labelDataHora.setText(bn.getString("labelDataHora"));
		labelNumMesa.setText(bn.getString("labelNumMesa"));
		labelNomeGarcom.setText(bn.getString("labelNomeGarcom"));
		labelNovaPosicao.setText(bn.getString("labelNovaPosicao"));
		labelAlterarStatus.setText(bn.getString("labelAlterarStatus"));
		labelNumMesaAtual.setText(bn.getString("labelNumMesaAtual"));
		labelData.setText(bn.getString("labelData"));
		lblIdUsuario.setText(bn.getString("lblIdUsuario"));
		lblNomeUsuario.setText(bn.getString("lblNomeUsuario"));
		lblLoginUsuario.setText(bn.getString("lblLoginUsuario"));
		lblSenhaUsuario.setText(bn.getString("lblSenhaUsuario"));
		lblRgUsuario.setText(bn.getString("lblRgUsuario"));
		lblCpfUsuario.setText(bn.getString("lblCpfUsuario"));
		lblTipoUsuario.setText(bn.getString("lblTipoUsuario"));
		lblData.setText(bn.getString("lblData"));
		lblPedido.setText(bn.getString("lblPedido"));
		lblPagamento.setText(bn.getString("lblPagamento"));
		labelNomePrato.setText(bn.getString("labelNomePrato"));
		labelNomeBebida.setText(bn.getString("labelNomeBebida"));
		labelNomeSobremesa.setText(bn.getString("labelNomeSobremesa"));
		labelNumProduto.setText(bn.getString("labelNumProduto"));
		labelNomeProduto.setText(bn.getString("labelNomeProduto"));
		labelDescProduto.setText(bn.getString("labelDescProduto"));
		labelDispProduto.setText(bn.getString("labelDispProduto"));
		labelValorProduto.setText(bn.getString("labelValorProduto"));
		labelTipoProduto.setText(bn.getString("labelTipoProduto"));
			
		buttonConsultar.setText(bn.getString("buttonConsultar")); 
		buttonIncluir.setText(bn.getString("buttonIncluir"));
		buttonAlterar.setText(bn.getString("buttonAlterar"));
		buttonExcluir.setText(bn.getString("buttonExcluir"));
		buttonCancelar.setText(bn.getString("buttonCancelar"));
		buttonFinalizar.setText(bn.getString("buttonFinalizar"));
		btnOk.setText(bn.getString("btnOk"));
		btnCancelar.setText(bn.getString("btnCancelar"));
		btnExtrato.setText(bn.getString("btnExtrato"));
		btnLogin.setText(bn.getString("btnLogin"));
		btnCadastrar.setText(bn.getString("btnCadastrar"));
		btnConsultar.setText(bn.getString("btnConsultar"));
		btnValidarDados.setText(bn.getString("btnValidarDados"));
		btnEnviar.setText(bn.getString("btnEnviar"));
		btnFechar.setText(bn.getString("btnFechar"));

		rbtCredito.setText(bn.getString("rbtCredito"));
		rbtDebito.setText(bn.getString("rbtDebito"));
		rbtDinheiro.setText(bn.getString("rbtDinheiro"));
			
		comboAlterarStatus.setText(bn.getString("comboAlterarStatus"));
		comboEmAndamento.setText(bn.getString("comboEmAndamento"));
		comboPronto.setText(bn.getString("comboPronto"));
		comboRecusado.setText(bn.getString("comboRecusado"));
		comboTipoProduto.setText(bn.getString("comboTipoProduto"));
		comboPrato.setText(bn.getString("comboPrato"));
		comboSobremesa.setText(bn.getString("comboSobremesa"));
		comboBebida.setText(bn.getString("comboBebida"));
		comboGarcom.setText(bn.getString("comboGarcom"));
		comboSupervisor.setText(bn.getString("comboSupervisor"));
		comboCaixa.setText(bn.getString("comboCaixa"));
		comboCozinheiro.setText(bn.getString("comboCozinheiro"));

		mensagemLogar.setText(bn.getString("mensagemLogar"));
		mensagemDeslogar.setText(bn.getString("mensagemDeslogar"));
		mensagemTrocaIdioma.setText(bn.getString("mensagemTrocaIdioma"));

		mesa.setText(bn.getString("mesa"));
		*/
	}
}