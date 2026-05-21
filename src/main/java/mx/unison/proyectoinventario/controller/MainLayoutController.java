package mx.unison.proyectoinventario.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mx.unison.proyectoinventario.Main;

public class MainLayoutController {
    @FXML private StackPane contentArea;
    @FXML private Button productosBtn;
    @FXML private Button almacenesBtn;

    private VBox homeView;

    @FXML
    public void initialize() {
        if (!contentArea.getChildren().isEmpty()) {
            homeView = (VBox) contentArea.getChildren().get(0);
        }
    }

    @FXML
    public void navigateToHome(ActionEvent event) {
        contentArea.getChildren().clear();
        if (homeView != null) {
            contentArea.getChildren().add(homeView);
        }
    }

    @FXML
    public void navigateToProductos(ActionEvent event) {
        loadView("/view/Productos.fxml");
    }

    @FXML
    public void navigateToAlmacenes(ActionEvent event) {
        loadView("/view/Almacenes.fxml");
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            Main.setRoot("/view/Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
