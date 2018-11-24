package com.rafael.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.domain.ItensPedido;
import com.rafael.cursomc.domain.PagamentoComBoleto;
import com.rafael.cursomc.domain.Pedidos;
import com.rafael.cursomc.domain.enums.EstadoPagamento;
import com.rafael.cursomc.repositories.ItensPedidoRepository;
import com.rafael.cursomc.repositories.PagamentoRepository;
import com.rafael.cursomc.repositories.PedidoRepository;
import com.rafael.cursomc.security.UserSS;
import com.rafael.cursomc.services.exceptions.AuthorizationException;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItensPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@Autowired
	private EmailService emailService;
	
	public Pedidos find(Integer cdPedido){
		  Optional<Pedidos> obj  = rep.findById(cdPedido);
		  
		  if(obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Código: " + cdPedido +
					 ", Tipo: " + Pedidos.class.getName());  
		  }
		  
		  return obj.orElse(null);
	}
	
	public Pedidos insert(Pedidos obj) {
		
		obj.setCdPedido(null);
		obj.setDtPedido(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getCdCliente()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		//Data de vencimento apenas para o Pagamento com Boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getDtPedido());
		}
		
		obj = rep.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItensPedido ip : obj.getItens()) {
			//seta desconto (não coloquei nesse projeto)
			ip.setVlrUnitario(produtoService.find(ip.getProduto().getCdProduto()).getPreco());		
			ip.setProduto(produtoService.find(ip.getProduto().getCdProduto()));
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;
	}

	public Page<Pedidos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Clientes cliente = clienteService.find(user.getCdUsuario());
		
		return rep.findByCliente(cliente, pageRequest);
	}

}
