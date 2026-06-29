package com.salud.view;

import com.salud.model.Cita;
import com.salud.model.HistoriaClinica;
import com.salud.model.Triaje;
import com.salud.model.DiagnosticoCie10;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class AtencionFormFrame extends JFrame {

    private final Cita cita;
    private final HistoriaClinica historia;
    private int atencionId = -1;

    private final JComboBox<DiagnosticoCie10> cmbCie10 = new JComboBox<>();
    private final JTextField txtDiagnostico = new JTextField();
    private final JTextArea txtAnamnesis = new JTextArea(3, 20);
    private final JTextArea txtExamen = new JTextArea(3, 20);
    private final JTextArea txtPlan = new JTextArea(3, 20);
    private final JButton btnGuardar = new JButton("Registrar Atención");
    private final JButton btnReceta = new JButton("Emitir Receta");
    private final JButton btnCancelar = new JButton("Cancelar");

    public AtencionFormFrame(Cita cita, Triaje triaje, HistoriaClinica historia) {
        this.cita = cita;
        this.historia = historia;
        setTitle("Atención - " + cita.getPacienteNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel north = new JPanel(new GridLayout(4, 1));
        north.add(new JLabel("Paciente: " + cita.getPacienteNombre()));
        north.add(new JLabel(String.format("Triaje: Peso=%.1fkg Talla=%.2fm PA=%s Temp=%.1f°C IMC=%.2f",
                triaje.getPesoKg(), triaje.getTallaM(), triaje.getPresionArterial(),
                triaje.getTemperaturaC(), triaje.getImc())));
        north.add(new JLabel("Motivo: " + triaje.getMotivoConsulta()));
        if (historia != null) {
            north.add(new JLabel("HC: Sangre=" + historia.getTipoSangre() + " Alergias=" + historia.getAlergias()));
        }

        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.add(new JLabel("CIE-10:"));
        panel.add(cmbCie10);
        panel.add(new JLabel("Diagnóstico detallado:"));
        panel.add(txtDiagnostico);
        panel.add(new JLabel("Anamnesis:"));
        panel.add(new javax.swing.JScrollPane(txtAnamnesis));
        panel.add(new JLabel("Examen físico:"));
        panel.add(new javax.swing.JScrollPane(txtExamen));
        panel.add(new JLabel("Plan tratamiento:"));
        panel.add(new javax.swing.JScrollPane(txtPlan));

        JPanel south = new JPanel();
        south.add(btnGuardar);
        south.add(btnReceta);
        south.add(btnCancelar);

        add(north, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    public Cita getCita() { return cita; }
    public HistoriaClinica getHistoria() { return historia; }
    public JComboBox<DiagnosticoCie10> getCmbCie10() { return cmbCie10; }
    public JTextField getTxtDiagnostico() { return txtDiagnostico; }
    public JTextArea getTxtAnamnesis() { return txtAnamnesis; }
    public JTextArea getTxtExamen() { return txtExamen; }
    public JTextArea getTxtPlan() { return txtPlan; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnReceta() { return btnReceta; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public int getAtencionId() { return atencionId; }
    public void setAtencionId(int atencionId) { this.atencionId = atencionId; }
}
