package com.edu.unipiloto.prestamo;

public class SolicitudPrestamoStaff extends SolicitudPrestamo{
    
    private static final int MAX_LIBROS = 12;

    @Override
    protected void seleccionarLibros() {
        System.out.println("Seleccionando hasta " + MAX_LIBROS + " libros para préstamo como staff");
        // Lógica de selección de libros para staff
    }

    @Override
    protected boolean esUsuarioStaff() {
        return true;
    }

    @Override
    protected void seleccionarRevistas() {
        System.out.println("Seleccionando revistas para préstamo como staff");
        // Lógica de selección de revistas para staff
    }

}
