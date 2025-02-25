package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ISO_DATE;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision){
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        setVehiculo(revision.getVehiculo());
        setCliente(revision.getCliente());
        setFechaInicio(revision.getFechaInicio());
    }

    public Cliente getCliente(){
        return cliente;
    }

    private void setCliente(Cliente cliente){
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede se rnula");
        if(fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser aposterior a la fecha de actual.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if(!fechaInicio.isBefore(fechaFin)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if(fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if(fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        } else if(horas <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(precioMaterial, "El precio del material no puede ser nulo");
        if(fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        } else if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada(){
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin){
        Objects.requireNonNull(fechaFin, "La fecha de finalización no puede ser nula.");
        setFechaFin(fechaFin);
    }

    public float getPrecio(){
        return getHoras() * PRECIO_HORA + getDias() * PRECIO_DIA + getPrecioMaterial() * PRECIO_MATERIAL;
    }

    public float getDias(){
        float dias;
        if(fechaFin == null){
            dias = 0;
        } else{
            dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        }
        return dias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return String.format("[fechaInicio=%s, fechaFin=%s, horas=%s, precioMaterial=%s, cliente=%s, vehiculo=%s]", fechaInicio, fechaFin, horas, precioMaterial, cliente, vehiculo);
    }
}
