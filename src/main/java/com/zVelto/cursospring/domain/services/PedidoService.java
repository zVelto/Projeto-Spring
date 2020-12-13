package com.zVelto.cursospring.domain.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zVelto.cursospring.domain.ItemPedido;
import com.zVelto.cursospring.domain.PagamentoComBoleto;
import com.zVelto.cursospring.domain.Pedido;
import com.zVelto.cursospring.domain.enums.EstadoPagamento;
import com.zVelto.cursospring.domain.repositories.ItemPedidoRepository;
import com.zVelto.cursospring.domain.repositories.PagamentoRepository;
import com.zVelto.cursospring.domain.repositories.PedidoRepository;
import com.zVelto.cursospring.domain.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PagamentoRepository pagtoRepo;
	
	@Autowired
	private ItemPedidoRepository iPRepo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Pedido.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagtoRepo.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		iPRepo.saveAll(obj.getItens());
		return obj;
	}
}
