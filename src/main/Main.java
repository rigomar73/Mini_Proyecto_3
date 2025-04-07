package main;

import vista.VistaInicio;
import controlador.ControladorInicio;

public class Main {
    public static void main(String[] args) {
        // Crear la vista de inicio
        VistaInicio vistaInicio = new VistaInicio();

        // Crear el controlador y conectar la vista
        new ControladorInicio(vistaInicio);

        // Mostrar la ventana de inicio
        vistaInicio.mostrar();
    }
}