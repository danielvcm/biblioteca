package com.daniel.biblioteca.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
public class ControladorCategoria {

	@PostMapping("adicionar-categoria")
	public void adiconarCategoria() {
		
	}
}
