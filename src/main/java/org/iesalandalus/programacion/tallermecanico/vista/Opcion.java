package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(11, "Insertar un cliente."),
    BUSCAR_CLIENTE(12, "Buscar un cliente."),
    BORRAR_CLIENTE(13,"Borrar un cliente."),
    LISTAR_CLIENTES(14,"Listar todos los clientes."),
    MODIFICAR_CLIENTE(15,"Modificar un cliente."),
    INSERTAR_VEHICULO(21,"Insertar un vehículo."),
    BUSCAR_VEHICULO(22,"Buscar un vehículo."),
    BORRAR_VEHICULO(23,"Borrar un vehículo."),
    LISTAR_VEHICULOS(24,"Listar todos los vehículos."),
    INSERTAR_REVISION(31,"Insertar una revisión."),
    BUSCAR_REVISION(32,"Buscar una revisión."),
    BORRAR_REVISION(33,"Borrar una revisión."),
    LISTAR_REVISIONES(34,"Listar todas las revisiones."),
    LISTAR_REVISIONES_CLIENTE(35,"Listar todas las revisiones de un cliente."),
    LISTAR_REVISIONES_VEHICULO(36,"Listar todas las revisiones de un vehículo."),
    ANADIR_HORAS_REVISION(37,"Añadir horas a una revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(38,"Añadir precio de material a una revisión."),
    CERRAR_REVISION(39,"Cerrar una revisión."),
    SALIR(0,"Salir del programa.");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for(Opcion opcion : values()){
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    private Opcion(int numeroOpcion, String mensaje) {
        this.mensaje = mensaje;
        this.numeroOpcion = numeroOpcion;
    }

    public static boolean esValida(int numeroOpcion){
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion){
        if(!esValida(numeroOpcion)){
            throw new IllegalArgumentException("El número de la opción no es correcto.");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%d.- %s%n", numeroOpcion, mensaje);
    }
}
