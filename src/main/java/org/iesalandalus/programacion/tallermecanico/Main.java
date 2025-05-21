package org.iesalandalus.programacion.tallermecanico;


import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {
    public static void main(String[] args) {
        IControlador controlador = new Controlador(FabricaModelo.CASCADA, FabricaFuenteDatos.FICHEROS,procesarArgumentosVistas(args));
        controlador.comenzar();
    }

    private static FabricaVista procesarArgumentosVistas(String[] args) {
        FabricaVista fabricaVista = FabricaVista.GRAFICA;
        for (String argumento: args) {
            if(argumento.equalsIgnoreCase("-vgrafica")){
                fabricaVista = FabricaVista.GRAFICA;
            } else if(argumento.equalsIgnoreCase("-vtexto")) {
                fabricaVista = FabricaVista.TEXTO;
            }
        }
        return fabricaVista;
    }

}
