package com.daniel.biblioteca.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca.json.RequisicaoCategoria;
import com.daniel.biblioteca.modelo.sql.Categoria;
import com.daniel.biblioteca.repositorio.RepositorioCategoria;
import com.daniel.biblioteca.servico.ServicoCategoria;

@Service
public class ServicoCategoriaImpl implements ServicoCategoria {

	@Autowired
	private RepositorioCategoria repositorioCategoria;

	public void adicionaCategoria(RequisicaoCategoria requisicaoCategoria) {
		Categoria novaCategoria = new Categoria();
		if(requisicaoCategoria.getNome()==null || requisicaoCategoria.getNome().isBlank()) {
			throw new RuntimeException("Nova categoria deve ter nome");
		}
		Categoria categoria = repositorioCategoria.findByNome(requisicaoCategoria.getNome());
		if(categoria !=null) {
			throw new RuntimeException("Já existe uma categoria com esse nome");
		}
		novaCategoria.setNome(requisicaoCategoria.getNome().toUpperCase());
		if(requisicaoCategoria.getDescricao()!=null && !requisicaoCategoria.getDescricao().isBlank()){
			novaCategoria.setDescricao(requisicaoCategoria.getDescricao());
		}
		if(requisicaoCategoria.getCategoriaMae()!=null && !requisicaoCategoria.getCategoriaMae().isBlank()) {
			Categoria categoriaMae = repositorioCategoria.findByNome(requisicaoCategoria.getCategoriaMae().toUpperCase());
			if(categoriaMae == null) {
				throw new RuntimeException("Categoria mãe não existe");
			}
			novaCategoria.setIdCategoriaMae(categoriaMae.getId());	
		}
		
		repositorioCategoria.save(novaCategoria);
	}

}
