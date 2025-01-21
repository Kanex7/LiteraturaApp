package com.alura.literatura.controller;

import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.model.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Busquedas {
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    ConexionDB conexionDB;
    public Busquedas(LibroRepository repository) {
        this.conexionDB = new ConexionDB(repository);
    }


    public void buscarPorTitulo(String libroABuscar){
        String json = consumoAPI.obtenerDatos(libroABuscar);

        var datosBuscados = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libro = datosBuscados.libroList().stream()
                .sorted(Comparator.comparing(DatosLibro::descargas).reversed()).findFirst();
        if(libro.isPresent()){
            var libroBuscado = libro.get();
            Libro libroCreado = new Libro(libroBuscado);
            //Checkeamos que el libro no exista antes de persistir
            if (!conexionDB.buscarTodos().stream()
                    .map(l->l.getTitulo()).anyMatch(t->t.equalsIgnoreCase(libroCreado.getTitulo()))){
                conexionDB.guardar(libroCreado);
            } else {
                System.out.println("No se guardó porque ya está registrado");
            }
            System.out.println(libroBuscado.toString());
        } else {
            System.out.println("\"LIBRO NO ENCONTRADO!\"");
        }
    }

    public void buscarLibrosRegistrados(){
        var librosRegistrados = conexionDB.buscarTodos();
        librosRegistrados.forEach(System.out::println);
    }

    public void buscarAutoresRegistrados() {
        var autoresRegistrados = conexionDB.buscarTodos().stream()
                .map(l -> l.getAutor()).collect(Collectors.toList());
        autoresRegistrados.forEach(System.out::println);
    }

    public void buscarAutoresRegistradosPorAno(int ano) {
        var autoresRegistradosVivos = conexionDB.buscarTodos().stream()
                .map(l -> l.getAutor())
                .filter(a->(a.getFechaNacimiento() < ano && a.getFechaFallecimiento() > ano))
                .collect(Collectors.toList());
        if (!autoresRegistradosVivos.isEmpty()){
            autoresRegistradosVivos.forEach(System.out::println);
        }else{
            System.out.println("No se encontraron autores vivos en ese año!");
        }
    }

    public void buscarLibroPorIdioma(Integer idiomaABuscar) {
        String codigoIdioma;
        switch (idiomaABuscar) {
            case 1:
                codigoIdioma = "es";
                break;
            case 2:
                codigoIdioma = "en";
                break;
            case 3:
                codigoIdioma = "fr";
                break;
            case 4:
                codigoIdioma = "pr";
                break;
            default:
                codigoIdioma = "";
        }
        var librosRegistradosPorIdioma = conexionDB.buscarTodos().stream()
                .filter(l->l.getIdiomas().contains(codigoIdioma)).collect(Collectors.toList());
        if(!librosRegistradosPorIdioma.isEmpty()){
            librosRegistradosPorIdioma.forEach(System.out::println);
        } else{
            System.out.println("No hay libros registrados en ese idioma!");
        }
    }
}
