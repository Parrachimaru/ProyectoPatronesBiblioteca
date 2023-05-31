package com.edu.unipiloto.prestamo;

abstract class SolicitudPrestamo {
    
    public final void solicitarPrestamo() {
        validarUsuario();
        seleccionarLibros();
        if (esUsuarioStaff()) {
            seleccionarRevistas();
        }
        realizarPrestamo();
    }

    private void validarUsuario() {
        System.out.println("Validando usuario...");
        // Lógica de validación de usuario
    }

    private void realizarPrestamo() {
        System.out.println("Realizando préstamo...");
        // Lógica de realización del préstamo
    }

    protected abstract void seleccionarLibros();

    protected boolean esUsuarioStaff() {
        return false;
    }

    protected void seleccionarRevistas() {
        throw new UnsupportedOperationException("Solicitud de revistas no permitida para este tipo de usuario");
    }

}
