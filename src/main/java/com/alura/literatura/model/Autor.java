package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAutor;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libroList;

    public Autor() {
    }
    public Autor(DatosAutor datosAutor) {
        this.nombreAutor = datosAutor.nombreAutor();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getFechaNacimiento() {
        if (fechaNacimiento == null){
            return 9999;
        } else {
            return fechaNacimiento;
        }
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        if (fechaFallecimiento == null){
            return 9999;
        } else {
            return fechaFallecimiento;
        }
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibroList() {
        return libroList;
    }


    public void setLibroList(List<Libro> libroList) {
        libroList.forEach(l->l.setAutor(this));
        this.libroList = libroList;
    }

    @Override
    public String toString() {
        return """
                =======AUTOR=======
                Nombre del Autor: %s
                Fecha de Nacimiento: %d
                Fecha de Fallecimiento: %d
                Libros: %s
                ===================
                """.formatted(getNombreAutor(),
                getFechaNacimiento(),
                getFechaFallecimiento(),
                getLibroList().stream()
                        .map(l->l.getTitulo()).collect(Collectors.joining(",")));
    }


}
