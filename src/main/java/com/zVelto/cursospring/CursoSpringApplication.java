package com.zVelto.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zVelto.cursospring.domain.Categoria;
import com.zVelto.cursospring.domain.Cidade;
import com.zVelto.cursospring.domain.Cliente;
import com.zVelto.cursospring.domain.Endereco;
import com.zVelto.cursospring.domain.Estado;
import com.zVelto.cursospring.domain.Produto;
import com.zVelto.cursospring.domain.enums.TipoCliente;
import com.zVelto.cursospring.domain.repositories.CategoriaRepository;
import com.zVelto.cursospring.domain.repositories.CidadeRepository;
import com.zVelto.cursospring.domain.repositories.ClienteRepository;
import com.zVelto.cursospring.domain.repositories.EnderecoRepository;
import com.zVelto.cursospring.domain.repositories.EstadoRepository;
import com.zVelto.cursospring.domain.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository pRepo;
	@Autowired
	private CidadeRepository cRepo;
	@Autowired 
	private EstadoRepository estRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository eRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		pRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estRepo.saveAll(Arrays.asList(est1, est2));
		cRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cliRepo.saveAll(Arrays.asList(cli1));
		eRepo.saveAll(Arrays.asList(e1, e2));
		
	}

}
