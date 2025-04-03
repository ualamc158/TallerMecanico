package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public enum TipoTrabajo {
    MECANICO("Mecánico."),
    REVISION("Revisión.");

    private final String nombre;

    TipoTrabajo(String nombre) {
        Objects.requireNonNull(nombre, "El nombre del tipo de trabajo no puede ser nulo.");
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo){
        TipoTrabajo trabajoTipo;
        if(trabajo instanceof Mecanico){
            trabajoTipo = MECANICO;
        } else {
            trabajoTipo = REVISION;
        }
        return trabajoTipo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
