package com.daniel.biblioteca.servico.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca.json.requisicao.RequisicaoCategoria;
import com.daniel.biblioteca.json.resposta.CategoriaResposta;
import com.daniel.biblioteca.json.resposta.RecuperaCategoriasResposta;
import com.daniel.biblioteca.modelo.sql.Categoria;
import com.daniel.biblioteca.repositorio.RepositorioCategoria;
import com.daniel.biblioteca.servico.ServicoCategoria;

@Service
public class ServicoCategoriaImpl implements ServicoCategoria {

	@Autowired
	private RepositorioCategoria repositorioCategoria;

	public void adicionaCategoria(RequisicaoCategoria requisicaoCategoria) {
		Categoria novaCategoria = new Categoria();

		validaEAtribuiNome(requisicaoCategoria, novaCategoria);
		atribuiDescricao(requisicaoCategoria, novaCategoria);
		validaEAtribuiCategoriaMae(requisicaoCategoria, novaCategoria);

		repositorioCategoria.save(novaCategoria);
	}

	public RecuperaCategoriasResposta recuperaCategorias() {
		List<Categoria> categorias = repositorioCategoria.findAll();
		RecuperaCategoriasResposta resposta = new RecuperaCategoriasResposta();
		resposta.setCategorias(new ArrayList<>());
		for(Categoria categoria : categorias) {
			CategoriaResposta categoriaResposta = new CategoriaResposta();
			constroiResposta(categoria, categoriaResposta);
			if(categoria.getIdCategoriaMae() == null) {
				resposta.getCategorias().add(categoriaResposta);
			}
			else {
				Boolean found =false;
				encontraMaeEAdicionaFilhaNaLista(resposta.getCategorias(), categoria, categoriaResposta, found);
			}
		}
		return resposta;
	}

	private void validaEAtribuiNome(RequisicaoCategoria requisicaoCategoria, Categoria novaCategoria) {
		if(requisicaoCategoria.getNome()==null || requisicaoCategoria.getNome().isBlank()) {
			throw new RuntimeException("Nova categoria deve ter nome");
		}
		Categoria categoria = repositorioCategoria.findByNome(requisicaoCategoria.getNome());
		if(categoria !=null) {
			throw new RuntimeException("Já existe uma categoria com esse nome");
		}
		novaCategoria.setNome(requisicaoCategoria.getNome().toUpperCase());
	}

	private void atribuiDescricao(RequisicaoCategoria requisicaoCategoria, Categoria novaCategoria) {
		if(requisicaoCategoria.getDescricao()!=null && !requisicaoCategoria.getDescricao().isBlank()){
			novaCategoria.setDescricao(requisicaoCategoria.getDescricao());
		}
	}

	private void validaEAtribuiCategoriaMae(RequisicaoCategoria requisicaoCategoria, Categoria novaCategoria) {
		if(requisicaoCategoria.getCategoriaMae()!=null && !requisicaoCategoria.getCategoriaMae().isBlank()) {
			Categoria categoriaMae = repositorioCategoria.findByNome(requisicaoCategoria.getCategoriaMae().toUpperCase());
			if(categoriaMae == null) {
				throw new RuntimeException("Categoria mãe não existe");
			}
			novaCategoria.setIdCategoriaMae(categoriaMae.getId());	
		}
	}

	private void constroiResposta(Categoria categoria, CategoriaResposta categoriaResposta) {
		categoriaResposta.setId(categoria.getId());
		categoriaResposta.setNome(categoria.getNome());
		categoriaResposta.setDescricao(categoria.getDescricao());
	}

	private void encontraMaeEAdicionaFilhaNaLista(List<CategoriaResposta> listaCategorias, Categoria categoria,CategoriaResposta categoriaResposta, Boolean found) {
		for(CategoriaResposta categoriaAtual : listaCategorias) {
			if(categoriaAtual.getId().equals(categoria.getIdCategoriaMae())) {
				found = true;
				if(categoriaAtual.getCategoriasFilhas() == null) {
					categoriaAtual.setCategoriasFilhas(new ArrayList<>());
				}
				categoriaAtual.getCategoriasFilhas().add(categoriaResposta);
			}
		}
		if(!found) {
			for(CategoriaResposta categoriaAtual : listaCategorias) {
				if(found) {
					return;
				}
				if(categoriaAtual.getCategoriasFilhas() != null || !categoriaAtual.getCategoriasFilhas().isEmpty()) {
					encontraMaeEAdicionaFilhaNaLista(categoriaAtual.getCategoriasFilhas(), categoria, categoriaResposta, found);
				}
			}
		}
	}

}
