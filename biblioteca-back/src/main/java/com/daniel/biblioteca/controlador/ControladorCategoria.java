package com.daniel.biblioteca.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.biblioteca.json.requisicao.RequisicaoCategoria;
import com.daniel.biblioteca.json.resposta.RecuperaCategoriasResposta;
import com.daniel.biblioteca.servico.ServicoCategoria;

@RestController
@RequestMapping("categoria")
public class ControladorCategoria {

	@Autowired
	private ServicoCategoria servicoCategoria;

	@PostMapping("adicionar-categoria")
	public ResponseEntity<String> adiconarCategoria(@Valid @RequestBody RequisicaoCategoria requisicaoCategoria) {
		servicoCategoria.adicionaCategoria(requisicaoCategoria);
		return ResponseEntity.ok("Sucesso em salvar nova categoria!");
	}

	@GetMapping("recupera-categorias")
	public ResponseEntity<RecuperaCategoriasResposta> recuperaCategorias() {
		RecuperaCategoriasResposta resposta = servicoCategoria.recuperaCategorias();
		return ResponseEntity.ok(resposta);
	}
}
