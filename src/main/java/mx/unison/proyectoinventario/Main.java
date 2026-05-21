package mx.unison.proyectoinventario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.unison.proyectoinventario.dao.UsuarioDAO;
import mx.unison.proyectoinventario.util.DatabaseConnection;

public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        // inicializar base de datos
        DatabaseConnection.initDatabase();

        // crear usuarios default
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insertDefaultUser("ADMIN", "admin23", "ADMIN");
        usuarioDAO.insertDefaultUser("PRODUCTOS", "productos19", "PRODUCTOS");
        usuarioDAO.insertDefaultUser("ALMACENES", "almacenes11", "ALMACENES");

        // cargar vista de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        mainStage.setTitle("Sistema de gestión de inventario");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void setRoot(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        mainStage.getScene().setRoot(root);
    }

    public static void main(String [] args) {
        launch(args);
    }
}
