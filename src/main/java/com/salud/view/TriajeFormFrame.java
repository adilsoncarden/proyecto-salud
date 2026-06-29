package com.salud.view;

import com.salud.model.Cita;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class TriajeFormFrame extends JFrame {

    private final Cita cita;
    private final JTextField txtPeso = new JTextField();
    private final JTextField txtTalla = new JTextField();
    private final JTextField txtPresion = new JTextField();
    private final JTextField txtTemperatura = new JTextField();
    private final JTextField txtFc = new JTextField();
    private final JTextField txtFr = new JTextField();
    private final JTextField txtSpo2 = new JTextField();
    private final JTextArea txtMotivo = new JTextArea(3, 20);
    private final JButton btnGuardar = new JButton("Guardar Triaje");
    private final JButton btnCancelar = new JButton("Cancelar");

    public TriajeFormFrame(Cita cita) {
        this.cita = cita;
        setTitle("Triaje - " + cita.getPacienteNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 380);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(10, 2, 5, 5));
        panel.add(new JLabel("Peso (kg):"));
        panel.add(txtPeso);
        panel.add(new JLabel("Talla (m):"));
        panel.add(txtTalla);
        panel.add(new JLabel("Presión (120/80):"));
        panel.add(txtPresion);
        panel.add(new JLabel("Temperatura (°C):"));
        panel.add(txtTemperatura);
        panel.add(new JLabel("FC (opcional):"));
        panel.add(txtFc);
        panel.add(new JLabel("FR (opcional):"));
        panel.add(txtFr);
        panel.add(new JLabel("SpO2 (opcional):"));
        panel.add(txtSpo2);
        panel.add(new JLabel("Motivo consulta:"));
        panel.add(new JScrollPane(txtMotivo));
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        add(panel, BorderLayout.CENTER);
    }

    public Cita getCita() { return cita; }
    public JTextField getTxtPeso() { return txtPeso; }
    public JTextField getTxtTalla() { return txtTalla; }
    public JTextField getTxtPresion() { return txtPresion; }
    public JTextField getTxtTemperatura() { return txtTemperatura; }
    public JTextField getTxtFc() { return txtFc; }
    public JTextField getTxtFr() { return txtFr; }
    public JTextField getTxtSpo2() { return txtSpo2; }
    public JTextArea getTxtMotivo() { return txtMotivo; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
