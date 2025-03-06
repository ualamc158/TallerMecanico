package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar un cliente."),
    BUSCAR_CLIENTE(2, "Buscar un cliente."),
    BORRAR_CLIENTE(3,"Borrar un cliente."),
    LISTAR_CLIENTES(4,"Listar todos los clientes."),
    MODIFICAR_CLIENTE(5,"Modificar un cliente."),
    INSERTAR_VEHICULO(6,"Insertar un vehículo."),
    BUSCAR_VEHICULO(7,"Buscar un vehículo."),
    BORRAR_VEHICULO(8,"Borrar un vehículo."),
    LISTAR_VEHICULOS(9,"Listar todos los vehículos."),
    INSERTAR_REVISION(10,"Insertar una revisión."),
    BUSCAR_REVISION(11,"Buscar una revisión."),
    BORRAR_REVISION(12,"Borrar una revisión."),
    LISTAR_REVISIONES(13,"Listar todas las revisiones."),
    LISTAR_REVISIONES_CLIENTE(14,"Listar todas las revisiones de un cliente."),
    LISTAR_REVISIONES_VEHICULO(15,"Listar todas las revisiones de un vehículo."),
    ANADIR_HORAS_REVISION(16,"Añadir horas a una revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(17,"Añadir precio de material a una revisión."),
    CERRAR_REVISION(18,"Cerrar una revisión."),
    SALIR(19,"Salir del programa.");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    private Opcion(int numeroOpcion, String mensaje) {
        this.mensaje = mensaje;
        this.numeroOpcion = numeroOpcion;
    }

    public static boolean esValida(int numeroOpcion){
        for(Opcion opcion : values()){
            opciones.put(opcion.numeroOpcion,opcion);
        }
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion){
        return get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s. %s", numeroOpcion, mensaje);
    }
}
