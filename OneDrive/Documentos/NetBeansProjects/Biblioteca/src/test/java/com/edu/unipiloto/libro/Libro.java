package com.edu.unipiloto.libro;

public class Libro {

    private int id_libro;
    private String titulo;
    private String autor;
    private String editorial;
    private boolean disponibilidad;

    //constructor
    public Libro(int id_libro, String titulo, String autor, String editorial, boolean disponibilidad) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.disponibilidad = disponibilidad;
    }

    //getter and setter
    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
