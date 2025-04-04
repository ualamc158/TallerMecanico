package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {
    private final List<Trabajo> coleccionTrabajos;
    private static Trabajos instancia;
    private static final String FICHERO_TRABAJOS = "";
    private static final DateTimeFormatter FORMATO_FECHA = null;
    private static final String RAIZ = "";
    private static final String TRABAJO = "";
    private static final String CLIENTE = "";
    private static final String VEHICULO = "";
    private static final String FECHA_INICIO = "";
    private static final String FECHA_FIN = "";
    private static final String HORAS = "";
    private static final String PRECIO_MATERIAL = "";
    private static final String TIPO = "";
    private static final String REVISION = "";
    private static final String MECANICO = "";

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    static Trabajos getInstancia(){
        if(instancia == null) {
            instancia = new Trabajos();
        }
        return instancia;
    }

    public void comenzar(){

    }

    private void procesarDocumentoXml(Document documentoXml){

    }



    @Override
    public List<Trabajo> get(){
        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente){
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionesCliente.add(trabajo);
            }
        }
        return revisionesCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }

    public Map<TipoTrabajo, Integer> getEstadisticasMensuales (LocalDate mes){
        Objects.requireNonNull(mes, "El mes no puede ser nulo.");
        Map<TipoTrabajo, Integer> estadisticas = inicializarEstadisticas();

        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getFechaInicio().getMonth() == mes.getMonth() && trabajo.getFechaInicio().getYear() == mes.getYear()) {
                TipoTrabajo tipo = TipoTrabajo.get(trabajo);
                estadisticas.put(tipo, estadisticas.getOrDefault(tipo, 0) + 1);
            }
        }

        return estadisticas;
    }

    private Map<TipoTrabajo, Integer> inicializarEstadisticas(){
        Map<TipoTrabajo, Integer> estadisticas = new HashMap<>();
        for (TipoTrabajo tipo : TipoTrabajo.values()) {
            estadisticas.put(tipo, 0);
        }
        return estadisticas;

    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Objects.requireNonNull(fechaTrabajo, "La fecha de trabajo no puede ser nula.");

        for (Trabajo trabajo : coleccionTrabajos) {
            if (!trabajo.estaCerrado()) {
                if (trabajo.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo en curso.");
                }
                if (trabajo.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en el taller.");
                }
            } else if (!fechaTrabajo.isAfter(trabajo.getFechaFin())) {
                if (trabajo.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo posterior.");
                }
                if (trabajo.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene otro trabajo posterior.");
                }
            }
        }
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo) && !trabajo.estaCerrado()) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        } else if (trabajo instanceof Revision revision) {
            revision.anadirHoras(horas);
        } else if (trabajo instanceof Mecanico mecanico) {
            mecanico.anadirHoras(horas);
        }
        return trabajo;
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No puedo operar sobre un trabajo nulo.");
        Trabajo trabajoEncontrado = null;
        Iterator<Trabajo> iteradorTrabajos = coleccionTrabajos.iterator();
        while (iteradorTrabajos.hasNext() && trabajoEncontrado == null){
            Trabajo trabajo = iteradorTrabajos.next();
            if(trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrado()){
                trabajoEncontrado = trabajo;
            }
        }

        if(trabajoEncontrado == null){
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoEncontrado;
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        if(getTrabajoAbierto(trabajo.getVehiculo()) instanceof Mecanico mecanico){
            mecanico.anadirPrecioMaterial(precioMaterial);
        } else {
            throw new TallerMecanicoExcepcion("No se puede añadir precio al material para este tipo de trabajos.");
        }
        return trabajo;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (!coleccionTrabajos.contains(trabajo) && !trabajo.estaCerrado()) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajo;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        Trabajo encontrado;
        if(!coleccionTrabajos.contains(trabajo)){
            encontrado = null;
        } else{
            encontrado = trabajo;
        }
        return encontrado;
    }

    @Override
    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede borrar un trabajo nulo.");
        if (!coleccionTrabajos.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(revision);
    }


}
