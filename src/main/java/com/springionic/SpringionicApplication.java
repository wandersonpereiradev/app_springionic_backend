package com.springionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springionic.domain.Categoria;
import com.springionic.domain.Cidade;
import com.springionic.domain.Cliente;
import com.springionic.domain.Endereco;
import com.springionic.domain.Estado;
import com.springionic.domain.Produto;
import com.springionic.domain.enums.TipoCliente;
import com.springionic.repositories.CategoriaRepository;
import com.springionic.repositories.CidadeRepository;
import com.springionic.repositories.ClienteRepository;
import com.springionic.repositories.EnderecoRepository;
import com.springionic.repositories.EstadoRepository;
import com.springionic.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringionicApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringionicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//criando a instância de categoria
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//criando a instância de produto
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//associando as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associando os produtos com as categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//salvando as categorias e os produtos no BD
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//######## ESTADOS E CIDADES ######## 
		
		//criando a instância de estado
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//criando a instância de cidade
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//associando as cidades aos estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		//salvando os estados e as cidades no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//######## CLIENTE, TELEFONES E ENDERECOS ######## 
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		//adicionando os telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		//instanciando os endereços
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 08", "Centro", "38777012", cli1, c2);
		
		//atribuindo os endereços ao cliente		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//salvando cliente e endereços
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
