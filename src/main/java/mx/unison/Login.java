package mx.unison;

import mx.unison.Database;
import mx.unison.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Login extends JPanel{
    public Login(Consumer<String> onLogin) {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(320, 380));
        card.setBackground(new Color(224, 240, 255));
        card.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Inicio de sesión");
        title.setFont(new Font("Consolas", Font.PLAIN, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(4, 1, 10, 10));
        fields.setBackground(new Color(224, 240, 255)); // mismo color tarjeta
        fields.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        fields.add(new JLabel("Usuario:"));
        fields.add(user);
        fields.add(new JLabel("Contraseña:"));
        fields.add(pass);

        JButton loginBtn = new JButton("Iniciar sesión");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setFocusPainted(false);
        loginBtn.setBackground(new Color(198, 243, 213)); // verde pastel
        loginBtn.setBorder(BorderFactory.createLineBorder(new Color(63, 185, 80), 2, true));
        loginBtn.setPreferredSize(new Dimension(160, 40));

        loginBtn.addActionListener(e -> {
            Database db = new Database();
            var usr = db.authenticate(user.getText(), new String(pass.getPassword()));

            if (usr != null) {
                onLogin.accept(usr.nombre);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        card.add(title);
        card.add(fields);
        card.add(Box.createRigidArea(new Dimension(0, 25)));
        card.add(loginBtn);

        add(card, gbc);
    }
}
