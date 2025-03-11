package org.iesalandalus.programacion.tallermecanico.vista;

public class Consola {
    private static String CADENA_FORMATO_FECHA = "";

    private Consola(){
    }

    public void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        for (int i = 0; i < mensaje.length(); i++){
            System.out.print("-");
        }
    }

    public void mostrarMenu(){

    }
}
