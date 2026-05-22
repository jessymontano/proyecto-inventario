package mx.unison.proyectoinventario.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import mx.unison.proyectoinventario.dao.AlmacenDAO;
import mx.unison.proyectoinventario.dao.ProductoDAO;
import mx.unison.proyectoinventario.model.Almacen;
import mx.unison.proyectoinventario.model.Producto;

import java.util.List;
import java.util.Optional;


public class ProductosController {
    @FXML private TextField searchField;
    @FXML private TableView<Producto> productoTable;
    @FXML private VBox form;
    @FXML private HBox buttonBox;
    @FXML private Label formTitle;
    @FXML private TextField nombreField;
    @FXML private TextField precioField;
    @FXML private TextField cantidadField;
    @FXML private TextField descripcionField;
    @FXML private ComboBox<Almacen> almacenCombo;

    private ProductoDAO productoDAO;
    private AlmacenDAO almacenDAO;
    private boolean editing = false;
    private Producto selectedProducto;
    private ObservableList<Producto> productos;

    @FXML
    public void initialize() {
        productoDAO = new ProductoDAO();
        almacenDAO = new AlmacenDAO();

        loadAlmacenesComboBox();
        loadData();
    }

    private void loadAlmacenesComboBox() {
        List<Almacen> almacenes = almacenDAO.listAlmacenes();
        almacenCombo.setItems(FXCollections.observableArrayList(almacenes));
        almacenCombo.setConverter(new StringConverter<Almacen>() {
            @Override
            public String toString(Almacen almacen) {
                return (almacen == null) ? "" : almacen.getNombre();
            }

            @Override
            public Almacen fromString(String s) {
                return null;
            }
        });
    }

    private void loadData() {
        List<Producto> dbList = productoDAO.listProductos();
        productos = FXCollections.observableArrayList(dbList);
        productoTable.setItems(productos);
    }

    @FXML
    public void openAddModal(ActionEvent event) {
        editing = false;
        formTitle.setText("Crear producto");
        nombreField.clear();
        precioField.clear();
        cantidadField.clear();
        descripcionField.clear();
        almacenCombo.getSelectionModel().clearSelection();

        toggleForm(true);
    }

    @FXML
    public void openUpdateModal(ActionEvent event) {
        Producto selected = productoTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No se ha seleccionado un producto", "Por favor seleccione un producto de la tabla para modificar.");
            return;
        }

        editing = true;
        selectedProducto = selected;
        formTitle.setText("Modificar producto");

        nombreField.setText(selected.getNombre());
        precioField.setText(String.valueOf(selected.getPrecio()));
        cantidadField.setText(String.valueOf(selected.getCantidad()));
        descripcionField.setText(selected.getDescripcion());

        for (Almacen a : almacenCombo.getItems()) {
            if (a.getId() == selected.getAlmacenId()) {
                almacenCombo.getSelectionModel().select(a);
                break;
            }
        }
        toggleForm(true);
    }

    @FXML
    public void saveProducto(ActionEvent event) {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        Almacen selected = almacenCombo.getValue();

        if (nombre.isEmpty() || descripcion.isEmpty() || selected == null) {
            showAlert(Alert.AlertType.ERROR, "Campos incompletos", "Por favor, llena el nombre, la descripción y selecciona un almacén de destino.");
            return;
        }
        try {
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());

            if (precio < 0 || cantidad < 0) {
                showAlert(Alert.AlertType.ERROR, "Datos inválidos", "El precio y la cantidad no pueden ser números negativos.");
                return;
            }

            if (editing) {
                productoDAO.updateProducto(new Producto(selectedProducto.getId(),nombre, descripcion, cantidad, precio, selected.getId()), "ADMIN");
            } else {
                productoDAO.insertProducto(new Producto(nombre, descripcion, cantidad, precio, selected.getId()), "ADMIN");
            }

            loadData();
            toggleForm(false);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Formato incorrecto", "El precio y la cantidad deben ser valores numéricos (Ej. 150.50 y 10).");
        }
    }

    @FXML
    public void deleteProducto(ActionEvent event) {
        Producto selected = productoTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No se ha seleccionado un producto", "Por favor seleccione un producto de la tabla para eliminar.");
            return;
        }

        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Eliminar producto");
        confirmDelete.setHeaderText("Se eliminará el producto: " + selected.getNombre());
        confirmDelete.setContentText("¿Está seguro de que desea continuar? Esta acción no se puede deshacer.");

        Optional<ButtonType> result = confirmDelete.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            productoDAO.deleteProducto(selected.getId());
            loadData();
        }
    }

    @FXML
    public void cancelForm(ActionEvent event) {
        toggleForm(false);
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
