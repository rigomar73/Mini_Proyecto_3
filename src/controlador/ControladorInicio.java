package controlador;

import modelo.Entrenador;
import modelo.Pokemon;
import modelo.element.ElementPokemon;
import modelo.batalla.Batalla;
import vista.VistaInicio;
import vista.VistaEquipos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ControladorInicio {
    private VistaInicio vistaInicio;

    public ControladorInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;

        // Escucha el evento del botón para iniciar la batalla
        this.vistaInicio.agregarListenerBotonInicio(_ -> iniciarBatalla());
    }

    private void iniciarBatalla() {
        // Obtener los nombres de los entrenadores desde la vista
        String name1 = vistaInicio.getNombreEntrenador1();
        String name2 = vistaInicio.getNombreEntrenador2();

        // Validar que los nombres no estén vacíos
        if (name1.isEmpty() || name2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese los nombres de ambos entrenadores.");
            return;
        }

        // Crear los entrenadores
        Entrenador entrenador1 = new Entrenador();
        Entrenador entrenador2 = new Entrenador();
        entrenador1.setNameTrainer(name1);
        entrenador2.setNameTrainer(name2);

        // Inicializar los datos de Pokémon
        ElementPokemon.initializeData();
        Pokemon[] pokemonArray = ElementPokemon.getPokemons();
        ArrayList<Pokemon> availablePokemons = new ArrayList<>(Arrays.asList(pokemonArray));

        // Asignar equipos aleatorios a los entrenadores
        entrenador1.setPokemons(Pokemon.randomPokemon(availablePokemons));
        entrenador2.setPokemons(Pokemon.randomPokemon(availablePokemons));

        // Ocultar la ventana de inicio
        vistaInicio.ocultar();

        // Mostrar la pantalla de equipos antes de la batalla
        VistaEquipos vistaEquipos = new VistaEquipos(entrenador1, entrenador2);
        vistaEquipos.agregarListenerIniciarBatalla(e -> {
            vistaEquipos.ocultar();
            Batalla batalla = new Batalla();
            new vista.VistaBatalla(entrenador1, entrenador2, batalla); // <--- Cambia esto
        });
        vistaEquipos.mostrar();
    }
}