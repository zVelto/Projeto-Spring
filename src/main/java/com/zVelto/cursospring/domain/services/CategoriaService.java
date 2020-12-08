package com.zVelto.cursospring.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zVelto.cursospring.domain.Categoria;
import com.zVelto.cursospring.domain.repositories.CategoriaRepository;
import com.zVelto.cursospring.domain.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Categoria.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo:" + Categoria.class.getName()));
	}
}
