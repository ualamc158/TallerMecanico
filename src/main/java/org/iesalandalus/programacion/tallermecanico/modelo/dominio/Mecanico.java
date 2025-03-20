package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Mecanico extends Trabajo{
    private static float FACTOR_HORA = 30;
    private static float FACTOR_PRECIO_MATERIAL = 0;
    private float prcioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);
    }

    public Mecanico
}
