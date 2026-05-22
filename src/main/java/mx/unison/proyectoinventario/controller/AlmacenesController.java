package mx.unison.proyectoinventario.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mx.unison.proyectoinventario.dao.AlmacenDAO;
import mx.unison.proyectoinventario.model.Almacen;
import mx.unison.proyectoinventario.util.Session;

import java.util.List;
import java.util.Optional;

public class AlmacenesController {
    @FXML private TextField searchField;
    @FXML private TableView<Almacen> almacenesTable;
    @FXML private HBox buttonBox;
    @FXML private VBox form;
    @FXML private TextField nombreField;
    @FXML private TextField ubicacionField;
    @FXML private Label formTitle;

    private boolean editing = false;
    private Almacen selectedAlmacen;
    private AlmacenDAO almacenDAO;
    private ObservableList<Almacen> almacenes;

    @FXML
    public void initialize() {
        String userRole = Session.getCurrentUser().rol;

        if ("PRODUCTOS".equalsIgnoreCase(userRole)) {
            buttonBox.setVisible(false);
            buttonBox.setManaged(false);
        }
        almacenDAO = new AlmacenDAO();
        loadData();
    }

    private void loadData() {
        List<Almacen> dbList = almacenDAO.listAlmacenes();
        almacenes = FXCollections.observableArrayList(dbList);
        almacenesTable.setItems(almacenes);
    }

    @FXML
    public void openAddModal(ActionEvent event) {
        editing = false;
        formTitle.setText("Crear almacén");
        nombreField.clear();
        ubicacionField.clear();

        toggleForm(true);
    }

    @FXML
    public void openUpdateModal(ActionEvent event) {
        Almacen selected = almacenesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No se ha seleccionado un almacén", "Por favor seleccione un almacén de la tabla para modificar.");
            return;
        }

        editing = true;
        selectedAlmacen = selected;

        formTitle.setText("Modificar almacén");
        nombreField.setText(selected.getNombre());
        ubicacionField.setText(selected.getUbicacion());

        toggleForm(true);
    }

    @FXML
    public void cancelForm(ActionEvent event) {
        toggleForm(false);
    }

    @FXML
    public void saveAlmacen(ActionEvent event) {
        String nombre = nombreField.getText();
        String ubicacion = ubicacionField.getText();

        if (nombre.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Campos incompletos", "Por favor, llena el nombre del almacén.");
            return;
        }

        String username = Session.getCurrentUser().nombre;

        if (editing) {
            almacenDAO.updateAlmacen(selectedAlmacen.getId(), nombre, ubicacion, username);
        } else {
            almacenDAO.insertAlmacen(nombre, ubicacion, username);
        }

        loadData();
        toggleForm(false);
    }

    @FXML
    public void deleteAlmacen(ActionEvent event) {
        Almacen selectedAlmacen = almacenesTable.getSelectionModel().getSelectedItem();
        if (selectedAlmacen == null) {
            showAlert(Alert.AlertType.WARNING, "No se ha seleccionado un almacén", "Por favor seleccione un almacén de la tabla para eliminar.");
            return;
        }

        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Eliminar almacén");
        confirmDelete.setHeaderText("Se eliminará el almacén: " + selectedAlmacen.getNombre());
        confirmDelete.setContentText("¿Está seguro de que desea continuar? Esta acción no se puede deshacer.");

        Optional<ButtonType> result = confirmDelete.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            almacenDAO.deleteAlmacen(selectedAlmacen.getId());
            loadData();
        }
    }

    private void toggleForm(boolean show) {
        form.setVisible(show);
        form.setManaged(show);
        buttonBox.setVisible(!show);
        buttonBox.setManaged(!show);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
