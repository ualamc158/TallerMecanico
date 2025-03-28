package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private ITrabajos trabajos;
    private IVehiculos vehiculos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos, "La factoría d ela fuente de datos no puede ser nula");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {
        clientes = new Clientes();
        trabajos = new Trabajos();
        vehiculos = new Vehiculos();
    }

    @Override
    public void terminar() {
        System.out.print("El modelo ha terminado.");
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        trabajos.insertar(new Revision(clientes.buscar(revision.getCliente()), vehiculos.buscar(revision.getVehiculo()), revision.getFechaInicio()));
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        return Trabajo.copiar(trabajo);
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        return trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        return trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajosCliente = trabajos.get(cliente);
        for (Trabajo trabajo : trabajosCliente) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajosVehiculo = trabajos.get(vehiculo);
        for (Trabajo trabajo : trabajosVehiculo) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaClientes.add(new Cliente(cliente));
        }
        return listaClientes;
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> listaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            listaTrabajos.add(Trabajo.copiar(trabajo));
        }
        return listaTrabajos;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> listaTrabajos(Cliente cliente) {
        List<Trabajo> listaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            listaTrabajos.add(Trabajo.copiar(trabajo));
        }
        return listaTrabajos;
    }

    @Override
    public List<Trabajo> getRevisiones(Vehiculo vehiculo) {
        List<Trabajo> listaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            listaTrabajos.add(Trabajo.copiar(trabajo));
        }
        return listaTrabajos;
    }
}
