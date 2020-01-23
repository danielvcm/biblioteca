package com.daniel.biblioteca.servico;

import com.daniel.biblioteca.json.requisicao.RequisicaoCategoria;
import com.daniel.biblioteca.json.resposta.RecuperaCategoriasResposta;

public interface ServicoCategoria {

	public void adicionaCategoria(RequisicaoCategoria requisicaoCategoria);
	
	public RecuperaCategoriasResposta recuperaCategorias();

}
