package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

public class Modelo {
    private Clientes clientes;
    private Revisiones revisiones;
    private Vehiculos vehiculos;

    public Modelo() {
    }

    public void comenzar(){
        clientes = new Clientes();
        revisiones = new Revisiones();
        vehiculos = new Vehiculos();
    }

    public void terminar(){
        System.out.print("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(new Vehiculo(vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula()));
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.insertar(new Revision(revision));
    }

    public Cliente buscar(Cliente cliente){
        return new Cliente(clientes.buscar(cliente));
    }

}
