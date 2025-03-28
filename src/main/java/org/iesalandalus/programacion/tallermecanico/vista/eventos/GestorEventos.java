package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores = new HashMap<>();

    public GestorEventos(Evento... eventos){
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor, "Los receptores no pueden ser nulos.");
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.get(evento).add(receptor);
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor, "Los receptores no pueden ser nulos.");
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.get(evento).remove(receptor);
        }
    }

    public void notificar(Evento evento){
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");

    }

}
