package mx.unison.proyectoinventario.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.unison.proyectoinventario.Main;
import mx.unison.proyectoinventario.dao.UsuarioDAO;
import mx.unison.proyectoinventario.model.Usuario;

public class LoginController {
    @FXML private TextField usuarioField;
    @FXML private PasswordField passwordField;
    @FXML private Label error;

    private UsuarioDAO usuarioDAO;

    @FXML
    public void initialize() {
        usuarioDAO = new UsuarioDAO();
    }

    @FXML
    public void login(ActionEvent event) {
        String user = usuarioField.getText();
        String password = passwordField.getText();

        if (user.isEmpty() || password.isEmpty()) {
            error.setText("Por favor ingrese su usuario y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.authenticate(user, password);

        if (usuario != null) {
            error.setText("");
            try {
                Main.setRoot("/view/MainLayout.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                error.setText("Error al cargar la ventana principal.");
            }
        } else {
            error.setText("Credenciales inválidas.");
        }
    }
}
