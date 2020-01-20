package com.daniel.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.biblioteca.modelo.sql.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>  {

	Categoria findByNome(String nome);

}
