package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores.vehiculos.BuscarVehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores.vehiculos.InsertarVehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controladores;

public class MenuVehiculos extends Controlador {

    @FXML
    private Button bBorrarVehiculo;

    @FXML
    private Button bBuscarVehiculo;

    @FXML
    private ImageView bInsertarTrabajo;

    @FXML
    private Button bInsertarVehiculo;

    @FXML
    private Button bListarVehiculo;

    @FXML
    void botonPulsado(ActionEvent event) {
        if(event.getSource() == bInsertarVehiculo) {
            InsertarVehiculo insertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Insertar Vehículo", null);
            insertarVehiculo.getEscenario().show();
            insertarVehiculo.centrar();
        } else if (event.getSource() == bBuscarVehiculo) {
            BuscarVehiculo buscarVehiculo = (BuscarVehiculo) Controladores.get("/vistas/BuscarVehiculo.fxml", "Insertar Vehículo", null);
            buscarVehiculo.getEscenario().show();
            buscarVehiculo.centrar();
        }
    }

    @FXML
    void initialize() {

    }

}
