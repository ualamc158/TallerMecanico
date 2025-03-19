package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 30;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision){
        return new Revision();
    }

    public float getPrecioEspecifico() {
        return 0;
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
