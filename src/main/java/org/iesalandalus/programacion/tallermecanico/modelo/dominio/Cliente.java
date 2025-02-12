package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "([A-Z][a-z]+)( [A-Z][a-z]+)*";
    private static final String ER_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
    private static final String ER_TELEFONO = "\\d{9}";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) throws TallerMecanicoExcepcion {
    setNombre(nombre);

        Objects.requireNonNull(dni, "El dni no puede ser nulo");

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if(!nombre.matches(ER_NOMBRE)){
            throw new TallerMecanicoExcepcion("El nombre es incorrecto");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El dni no puede ser nulo");


        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni){
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numDni = Integer.parseInt(dni.substring(0,8)) ;
        return letrasDni.charAt(numDni % 23) == dni.charAt(9);
    }
}
