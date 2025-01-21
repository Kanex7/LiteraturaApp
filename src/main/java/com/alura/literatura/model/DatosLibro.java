package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autorList,
        @JsonAlias("languages") List<String> idiomasList,
        @JsonAlias("download_count") int descargas){
    @Override
    public String toString() {

        return """
                =======LIBRO=======
                Titulo:%s
                Autor:%s
                Idioma/s:%s
                Descargas:%d
                ===================
                """.formatted(titulo(),
                autorList().stream().map(e->e.nombreAutor()).findFirst().get(),
                idiomasList().stream().collect(Collectors.joining(" , ")),
                descargas());
    }
}