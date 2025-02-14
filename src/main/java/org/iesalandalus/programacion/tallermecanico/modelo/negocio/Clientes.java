package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private List<Cliente> coleccionClientes;

    public Clientes(){
         coleccionClientes = new ArrayList<>();
    }

    public ArrayList<Clientes> get(){
        return new ArrayList<Clientes>();
    }

    public void insertar(Cliente cliente){
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        coleccionClientes.add(cliente);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono){
        if(!nombre.isBlank() && !telefono.isBlank()){
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
        } else{
            throw new IllegalArgumentException("El nombre o el teléfono no pueden estar en blanco.");
        }
        return null;
    }

}
