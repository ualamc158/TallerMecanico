package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class Consola {
    private static String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    public void mostrarCabecera(String mensaje){
        System.out.printf(mensaje);
        System.out.printf("-".repeat(mensaje.length()).concat("%n%n"));
    }

    public void mostrarMenu(){
    mostrarCabecera("Gestión de un taller mecánico.");
        for(Opcion opcion : Opcion.values()){
            System.out.print(opcion);
        }
    }

    public static Opcion elegirOpcion(){
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("\nElige una opción: "));
            } catch(IllegalArgumentException e){
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        } while(opcion == null);
        return opcion;
    }

    private static int leerEntero(String mensaje){
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje){
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje){
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje){
    LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s (%s): ", mensaje, CADENA_FORMATO_FECHA);
        try{
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        } catch (DateTimeParseException e){
            fecha = null;
        }
        return fecha;
    }

    public static Cliente leerCliente(){
        String nombre = leerCadena("Introduzca el nombre: ");
        String dni = leerCadena("Introduzca el DNI: ");
        String telefono = leerCadena("Introduzca el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni(){
        return Cliente.get(leerCadena("Introduzca el DNI: "));
    }

    public static String leerNuevoNombre(){
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce la marca: ");
        String modelo = leerCadena("Introduce el modelo: ");
        String matricula = leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula(){
        return Vehiculo.get(leerCadena("Introduzca la matrícula: "));
    }

    public static Revision leerRevision(){
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }
}
