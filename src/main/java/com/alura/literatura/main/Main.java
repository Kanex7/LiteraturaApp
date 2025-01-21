package com.alura.literatura.main;

import com.alura.literatura.controller.Busquedas;
import com.alura.literatura.repository.LibroRepository;

import java.util.Scanner;

public class Main {

    Scanner inputUser = new Scanner(System.in);
    private Busquedas busqueda;
    private int optionUser = 1;

    public Main(LibroRepository repository) {
        this.busqueda = new Busquedas(repository);
    }

    public void muestraMenu(){
        while(optionUser != 0){

            System.out.println("""
                    =============================
                    ¡BIENVENIDO A LA BIBLIOTECA!
                    Elija una opción ingresando su número:
                    1) Buscar por título
                    2) Buscar Libros Registrados
                    3) Buscar Autores Registrados
                    4) Buscar Autores Vivos en un Determinado Año
                    5) Buscar Libros por Idioma
                    0) Salir
                    """);
            try{
                var opcionUser = Integer.parseInt(inputUser.nextLine());

                switch (opcionUser) {
                    case 1:
                        buscarPorTitulo();
                        break;
                    case 2:
                        buscarLibrosRegistrados();
                        break;
                    case 3:
                        buscarAutoresRegistrados();
                        break;
                    case 4:
                        buscarAutoresRegistradosPorAno();
                        break;
                    case 5:
                        buscarLibroPorIdioma();
                        break;
                    case 0:
                        System.out.println("Adios!...");
                        optionUser = 0;
                        break;
                    default:
                        System.out.println("Opcion Invalida");
                }

            } catch (NumberFormatException e){
                System.out.println("Solo ingresar nros enteros");
            }
        }
    }

    public void buscarPorTitulo(){
        System.out.println("Ingrese el nombre del Libro que desea buscar: ");
        var libroABuscar = inputUser.nextLine();
        busqueda.buscarPorTitulo(libroABuscar);
    }

    private void buscarLibrosRegistrados() {
        busqueda.buscarLibrosRegistrados();
    }

    private void buscarAutoresRegistrados() {
        busqueda.buscarAutoresRegistrados();
    }

    private void buscarAutoresRegistradosPorAno() {
        System.out.println("Ingrese el año en el que desea buscar:");
        try{
            Integer anoABuscar = Integer.parseInt(inputUser.nextLine());
            busqueda.buscarAutoresRegistradosPorAno(anoABuscar);
        } catch (NumberFormatException e){
            System.out.println("Solo ingresar nros enteros positivos!!");
        }
    }

    private void buscarLibroPorIdioma() {
        System.out.println("""
                ¿Qué idioma desea buscar?
                Ingrese el numero correspondiente al idioma:
                1)Español
                2)Inglés
                3)Frances
                4)Portugues
                """);
        try{
            Integer idiomaABuscar = Integer.parseInt(inputUser.nextLine());
            busqueda.buscarLibroPorIdioma(idiomaABuscar);
        } catch (NumberFormatException e){
            System.out.println("Solo ingresar nros enteros 1-4!!!");
        }

    }
}
