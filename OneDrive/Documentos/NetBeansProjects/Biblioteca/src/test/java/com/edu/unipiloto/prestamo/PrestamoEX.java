package com.edu.unipiloto.prestamo;

public class PrestamoEX {

    public static void main(String[] args) {
        SolicitudPrestamo solicitudSocio = new SolicitudPrestamoSocio();
        solicitudSocio.solicitarPrestamo();
        System.out.println();

        SolicitudPrestamo solicitudStaff = new SolicitudPrestamoStaff();
        solicitudStaff.solicitarPrestamo();
    }

}
