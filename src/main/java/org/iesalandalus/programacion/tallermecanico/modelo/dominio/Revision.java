package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision){

    }

    public float getPrecioEspecifico() {
        return getDias() * FACTOR_HORA;
    }

    @Override
    public String toString() {
        String resultado = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material",
                getCliente(), getVehiculo(), getFechaInicio(), getFechaFin() != null ? getFechaFin() : "", getHoras(), getPrecioEspecifico());
        if (getPrecio() > 0) {
            resultado += String.format(", %.2f € total", getPrecio());
        }
        return resultado;
    }
}
