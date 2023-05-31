package com.edu.unipiloto.prestamo;

public class SolicitudPrestamoSocio extends SolicitudPrestamo {

    private static final int MAX_LIBROS = 6;

    @Override
    protected void seleccionarLibros() {
        System.out.println("Seleccionando hasta " + MAX_LIBROS + " libros para préstamo como socio");
        // Lógica de selección de libros para socio
    }

}
