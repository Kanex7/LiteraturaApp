package com.alura.literatura.controller;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

public class ConexionDB {
    private LibroRepository repositoryLibros;
    public ConexionDB(LibroRepository repository) {
        this.repositoryLibros = repository;
    }
    public void guardar(Libro libro){
        repositoryLibros.save(libro);
    }

    public List<Libro> buscarTodos() {
        return repositoryLibros.findAll();
    }


}
