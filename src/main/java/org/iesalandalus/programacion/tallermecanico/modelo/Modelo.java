package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Revisiones revisiones;
    private Vehiculos vehiculos;

    public Modelo() {
    }

    public void comenzar() {
        clientes = new Clientes();
        revisiones = new Revisiones();
        vehiculos = new Vehiculos();
    }

    public void terminar() {
        System.out.print("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.insertar(new Revision(clientes.buscar(revision.getCliente()), vehiculos.buscar(revision.getVehiculo()), revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return new Revision(revisiones.buscar(revision));
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        return revisiones.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaClientes.add(new Cliente(cliente));
        }
        return listaClientes;
    }

    public List<Revision> getRevisiones() {
        List<Revision> listaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            listaRevisiones.add(new Revision(revision));
        }
        return listaRevisiones;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> listaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            listaRevisiones.add(new Revision(revision));
        }
        return listaRevisiones;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> listaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            listaRevisiones.add(new Revision(revision));
        }
        return listaRevisiones;
    }
}
