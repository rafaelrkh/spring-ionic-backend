package com.rafael.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Categorias;
import com.rafael.cursomc.domain.Cidades;
import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.domain.Enderecos;
import com.rafael.cursomc.domain.Estados;
import com.rafael.cursomc.domain.ItensPedido;
import com.rafael.cursomc.domain.PagamentoComBoleto;
import com.rafael.cursomc.domain.PagamentoComCartao;
import com.rafael.cursomc.domain.Pagamentos;
import com.rafael.cursomc.domain.Pedidos;
import com.rafael.cursomc.domain.Produtos;
import com.rafael.cursomc.domain.enums.EstadoPagamento;
import com.rafael.cursomc.domain.enums.Perfil;
import com.rafael.cursomc.domain.enums.TipoCliente;
import com.rafael.cursomc.repositories.CategoriaRepository;
import com.rafael.cursomc.repositories.CidadeRepository;
import com.rafael.cursomc.repositories.ClienteRepository;
import com.rafael.cursomc.repositories.EnderecoRepository;
import com.rafael.cursomc.repositories.EstadoRepository;
import com.rafael.cursomc.repositories.ItensPedidoRepository;
import com.rafael.cursomc.repositories.PagamentoRepository;
import com.rafael.cursomc.repositories.PedidoRepository;
import com.rafael.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		Categorias c1 = new Categorias(null, "Informática");
		Categorias c2 = new Categorias(null, "Escritório");
		Categorias c3 = new Categorias(null, "Cama, mesa e banho");
		Categorias c4 = new Categorias(null, "Eletrônicos");
		Categorias c5 = new Categorias(null, "Jardinagem");
		Categorias c6 = new Categorias(null, "Decoração");
		Categorias c7 = new Categorias(null, "Pefumaria");
		//Categorias c8 = new Categorias(null, "Teste");

		Produtos p1 = new Produtos(null, "Computador", 1000.00,34.2);
		Produtos p2 = new Produtos(null, "Impressora", 323.54,434.2);
		Produtos p3 = new Produtos(null, "Mouse", 50.5,32.1);
		Produtos p4 = new Produtos(null, "Mesa de escritório", 43.5,43.6);
		Produtos p5 = new Produtos(null, "Toalha", 55.00,65.3);
		Produtos p6 = new Produtos(null, "Colcha", 76.78,98.2);
		Produtos p7 = new Produtos(null, "TV true color", 23.5,24.00);
		Produtos p8 = new Produtos(null, "Roçadeira", 1000.00,553.00);
		Produtos p9 = new Produtos(null, "Abajour", 44.25,234.43);
		Produtos p10 = new Produtos(null, "Pendente", 127.5,43.2);
		Produtos p11 = new Produtos(null, "Shampoo", 100.00,982.3);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		c3.getProdutos().addAll(Arrays.asList(p5,p6));
		c4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9,p10));
		c7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(c1,c4));
		p2.getCategorias().addAll(Arrays.asList(c1, c2,c4));
		p3.getCategorias().addAll(Arrays.asList(c1,c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		/*
		 Testes
		 */
		
		Produtos p12 = new Produtos(null, "Produto 12", 10.00,1.50);
		Produtos p13 = new Produtos(null, "Produto 13", 10.00,1.50);
		Produtos p14 = new Produtos(null, "Produto 14", 10.00,1.50);
		Produtos p15 = new Produtos(null, "Produto 15", 10.00,1.50);
		Produtos p16 = new Produtos(null, "Produto 16", 10.00,1.50);
		Produtos p17 = new Produtos(null, "Produto 17", 10.00,1.50);
		Produtos p18 = new Produtos(null, "Produto 18", 10.00,1.50);
		Produtos p19 = new Produtos(null, "Produto 19", 10.00,1.50);
		Produtos p20 = new Produtos(null, "Produto 20", 10.00,1.50);
		Produtos p21 = new Produtos(null, "Produto 21", 10.00,1.50);
		Produtos p22 = new Produtos(null, "Produto 22", 10.00,1.50);
		Produtos p23 = new Produtos(null, "Produto 23", 10.00,1.50);
		Produtos p24 = new Produtos(null, "Produto 24", 10.00,1.50);
		Produtos p25 = new Produtos(null, "Produto 25", 10.00,1.50);
		Produtos p26 = new Produtos(null, "Produto 26", 10.00,1.50);
		Produtos p27 = new Produtos(null, "Produto 27", 10.00,1.50);
		Produtos p28 = new Produtos(null, "Produto 28", 10.00,1.50);
		Produtos p29 = new Produtos(null, "Produto 29", 10.00,1.50);
		Produtos p30 = new Produtos(null, "Produto 30", 10.00,1.50);
		Produtos p31 = new Produtos(null, "Produto 31", 10.00,1.50);
		Produtos p32 = new Produtos(null, "Produto 32", 10.00,1.50);
		Produtos p33 = new Produtos(null, "Produto 33", 10.00,1.50);
		Produtos p34 = new Produtos(null, "Produto 34", 10.00,1.50);
		Produtos p35 = new Produtos(null, "Produto 35", 10.00,1.50);
		Produtos p36 = new Produtos(null, "Produto 36", 10.00,1.50);
		Produtos p37 = new Produtos(null, "Produto 37", 10.00,1.50);
		Produtos p38 = new Produtos(null, "Produto 38", 10.00,1.50);
		Produtos p39 = new Produtos(null, "Produto 39", 10.00,1.50);
		Produtos p40 = new Produtos(null, "Produto 40", 10.00,1.50);
		Produtos p41 = new Produtos(null, "Produto 41", 10.00,1.50);
		Produtos p42 = new Produtos(null, "Produto 42", 10.00,1.50);
		Produtos p43 = new Produtos(null, "Produto 43", 10.00,1.50);
		Produtos p44 = new Produtos(null, "Produto 44", 10.00,1.50);
		Produtos p45 = new Produtos(null, "Produto 45", 10.00,1.50);
		Produtos p46 = new Produtos(null, "Produto 46", 10.00,1.50);
		Produtos p47 = new Produtos(null, "Produto 47", 10.00,1.50);
		Produtos p48 = new Produtos(null, "Produto 48", 10.00,1.50);
		Produtos p49 = new Produtos(null, "Produto 49", 10.00,1.50);
		Produtos p50 = new Produtos(null, "Produto 50", 10.00,1.50);
		c1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		p12.getCategorias().add(c1);
		p13.getCategorias().add(c1);
		p14.getCategorias().add(c1);
		p15.getCategorias().add(c1);
		p16.getCategorias().add(c1);
		p17.getCategorias().add(c1);
		p18.getCategorias().add(c1);
		p19.getCategorias().add(c1);
		p20.getCategorias().add(c1);
		p21.getCategorias().add(c1);
		p22.getCategorias().add(c1);
		p23.getCategorias().add(c1);
		p24.getCategorias().add(c1);
		p25.getCategorias().add(c1);
		p26.getCategorias().add(c1);
		p27.getCategorias().add(c1);
		p28.getCategorias().add(c1);
		p29.getCategorias().add(c1);
		p30.getCategorias().add(c1);
		p31.getCategorias().add(c1);
		p32.getCategorias().add(c1);
		p33.getCategorias().add(c1);
		p34.getCategorias().add(c1);
		p35.getCategorias().add(c1);
		p36.getCategorias().add(c1);
		p37.getCategorias().add(c1);
		p38.getCategorias().add(c1);
		p39.getCategorias().add(c1);
		p40.getCategorias().add(c1);
		p41.getCategorias().add(c1);
		p42.getCategorias().add(c1);
		p43.getCategorias().add(c1);
		p44.getCategorias().add(c1);
		p45.getCategorias().add(c1);
		p46.getCategorias().add(c1);
		p47.getCategorias().add(c1);
		p48.getCategorias().add(c1);
		p49.getCategorias().add(c1);
		p50.getCategorias().add(c1);

		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4,p5,p6,p7,p8,p9,p10,p11));
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		Estados e1 = new Estados(null, "Minas Gerais");
		Estados e2 = new Estados(null, "São Paulo");

		Cidades ci1 = new Cidades(null, "Uberlândia", e1);
		Cidades ci2 = new Cidades(null, "São Paulo", e2);
		Cidades ci3 = new Cidades(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(ci1));
		e2.getCidades().addAll(Arrays.asList(ci2, ci3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3));

		Clientes cli1 = new Clientes(null, "Maria Silva", "rafael__ptc@hotmail.com", "07003999684", pe.encode("123"), TipoCliente.PESSOAFISICA);
		Clientes cli2 = new Clientes(null, "Geraldo Costa", "rafaelrkh@outlook.com", "41892744023", pe.encode("123456"), TipoCliente.PESSOAFISICA);
		
		cli2.getTelefones().addAll(Arrays.asList("(34)3832333","(34)3432455"));
		cli2.addPerfil(Perfil.ADMIN);
		
		cli1.getTelefones().addAll(Arrays.asList("(34)3831-2278","(34)9984-2006"));
		
		Enderecos end1 = new Enderecos(null,"Rua Artur Botelho", "356", "Centro", "CASA", "38740512", cli1, ci1);
		Enderecos end2 = new Enderecos(null, "Rua Inácio de Oliveira Campos", "134", "São Vicente", "AP 02", "3874512", cli1, ci2);
		Enderecos end3 = new Enderecos(null, "Rua Inácio de Oliveira Campos", "134", "São Vicente", "AP 02", "3874512", cli2,ci2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedidos ped1 = new Pedidos(null, sdf.parse("15/11/2018 10:32"), cli1, end1);
		Pedidos ped2 = new Pedidos(null, sdf.parse("30/11/2018 22:45"), cli1, end2);
		
		Pagamentos pgt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, "432423532666234", 6);
		ped1.setPagamento(pgt1);
		
		Pagamentos pgt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgt1, pgt2));
		
		ItensPedido i1 = new ItensPedido(ped1, p1, 99, 65.35);
		ItensPedido i2 = new ItensPedido(ped1, p3, 105, 25.63);
		ItensPedido i3 = new ItensPedido(ped2, p2, 4, 2.65);
		
		ped1.getItens().addAll(Arrays.asList(i1, i2));
		ped2.getItens().addAll(Arrays.asList(i3));
		
		p1.getItens().addAll(Arrays.asList(i1));
		p2.getItens().addAll(Arrays.asList(i3));
		p3.getItens().addAll(Arrays.asList(i2));
		
		itensPedidoRepository.saveAll(Arrays.asList(i1, i2, i3));
	}
}
