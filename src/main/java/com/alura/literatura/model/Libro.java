package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idiomas;
    private int descargas;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idiomas = datosLibro.idiomasList().stream()
                .collect(Collectors.joining(" , "));
        this.descargas = datosLibro.descargas();
        this.autor = datosLibro.autorList().stream().
                map(a -> new Autor(a)).findFirst().get();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {

        return """
                =======LIBRO=======
                Titulo:%s
                Autor:%s
                Idioma/s:%s
                Descargas:%d
                ===================
                """.formatted(getTitulo(),
                getAutor().getNombreAutor(),
                getIdiomas(),
                getDescargas());
    }


}
