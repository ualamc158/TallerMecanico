package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores.vehiculos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.VistaVentanas;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

public class InsertarVehiculo extends Controlador {

    @FXML
    private Button bAceptar;

    @FXML
    private Button bCancelar;

    @FXML
    private TextField ctMarca;

    @FXML
    private TextField ctMatricula;

    @FXML
    private TextField ctModelo;

    @FXML
    void cerrarVentana() {
        getEscenario().close();
    }

    public Vehiculo getVehiculo() {
        String marca = ctMarca.getText();
        String modelo = ctModelo.getText();
        String matricula = ctMatricula.getText();
        return new Vehiculo(marca, modelo, matricula);
    }

    @FXML
    void insertarVehiculo() {
        VistaVentanas.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_VEHICULO);
        getEscenario().close();
    }

    @FXML
    void initialize() {
        bAceptar.setOnAction(event -> insertarVehiculo());
        bCancelar.setOnAction(event -> cerrarVentana());
    }

}
