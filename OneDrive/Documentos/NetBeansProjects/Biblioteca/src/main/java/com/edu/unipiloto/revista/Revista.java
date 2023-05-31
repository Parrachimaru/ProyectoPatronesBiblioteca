package com.edu.unipiloto.revista;

public class Revista {
    
    private int id_revista;
    private String titulo;
    private String editor;
    private String fecha;
    private boolean disponibilidad;

    public Revista(int id_revista, String titulo, String editor, String fecha, boolean disponibilidad) {
        this.id_revista = id_revista;
        this.titulo = titulo;
        this.editor = editor;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
    }

    public int getId_revista() {
        return id_revista;
    }

    public void setId_revista(int id_revista) {
        this.id_revista = id_revista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    

}
