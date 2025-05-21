package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controladores;

public class VentanaPrincipal extends Controlador {

    @FXML
    private Button bClientes;

    @FXML
    private Button bTrabajos;

    @FXML
    private Button bVehiculos;

    @FXML
    void botonPulsado(ActionEvent event) {
        if (event.getSource() == bClientes) {
            MenuClientes menuClientes = (MenuClientes) Controladores.get("/vistas/MenuClientes.fxml", "Menú Clientes", null);
            menuClientes.getEscenario().show();
            menuClientes.centrar();
        } else if (event.getSource() == bVehiculos) {
            MenuVehiculos menuVehiculos = (MenuVehiculos) Controladores.get("/vistas/MenuVehiculos.fxml", "Menú Vehículos", null);
            menuVehiculos.getEscenario().show();
            menuVehiculos.centrar();
        } else if (event.getSource() == bTrabajos) {
            MenuTrabajos menuTrabajos = (MenuTrabajos) Controladores.get("/vistas/MenuTrabajos.fxml", "Menú Trabajos", null);
            menuTrabajos.getEscenario().show();
            menuTrabajos.centrar();
        }
    }

    @FXML
    void initialize() {

    }

}
