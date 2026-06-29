package com.salud.view;

import com.salud.model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

public class PacienteFormFrame extends JFrame {

    private final JTextField txtDni = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtFechaNacimiento = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final JTextField txtDireccion = new JTextField();
    private final JComboBox<String> cmbSeguro = new JComboBox<>(new String[] { "SIS", "PARTICULAR" });
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public PacienteFormFrame(String titulo, Paciente paciente) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 320);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5));
        panel.add(new JLabel("DNI:"));
        panel.add(txtDni);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Fecha nac. (yyyy-MM-dd):"));
        panel.add(txtFechaNacimiento);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Seguro:"));
        panel.add(cmbSeguro);
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        add(panel);

        if (paciente != null) {
            txtDni.setText(paciente.getDni());
            txtNombre.setText(paciente.getNombre());
            txtApellido.setText(paciente.getApellido());
            if (paciente.getFechaNacimiento() != null) {
                txtFechaNacimiento.setText(paciente.getFechaNacimiento().toLocalDate()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
            txtTelefono.setText(paciente.getTelefono() != null ? paciente.getTelefono() : "");
            txtDireccion.setText(paciente.getDireccion() != null ? paciente.getDireccion() : "");
            cmbSeguro.setSelectedItem(paciente.getTipoSeguro());
        }
    }

    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtFechaNacimiento() { return txtFechaNacimiento; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtDireccion() { return txtDireccion; }
    public JComboBox<String> getCmbSeguro() { return cmbSeguro; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
