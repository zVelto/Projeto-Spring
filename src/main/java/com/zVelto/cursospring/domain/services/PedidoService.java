package com.zVelto.cursospring.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zVelto.cursospring.domain.Pedido;
import com.zVelto.cursospring.domain.repositories.PedidoRepository;
import com.zVelto.cursospring.domain.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Pedido.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Pedido.class.getName()));
	}
}
