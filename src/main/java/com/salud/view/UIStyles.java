package com.salud.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Sistema de diseño visual centralizado para toda la aplicación.
 */
public final class UIStyles {

    /* Paleta profesional */
    public static final Color COLOR_PRIMARIO = new Color(0x1E, 0x3A, 0x8A);
    public static final Color COLOR_SECUNDARIO = new Color(0x25, 0x63, 0xEB);
    public static final Color COLOR_PRIMARIO_HOVER = new Color(0x1D, 0x4E, 0xD8);
    public static final Color COLOR_PRIMARIO_OSCURO = new Color(0x17, 0x25, 0x54);
    public static final Color COLOR_FONDO = new Color(0xF3, 0xF4, 0xF6);
    public static final Color COLOR_BLANCO = Color.WHITE;
    public static final Color COLOR_TEXTO = new Color(0x37, 0x41, 0x51);
    public static final Color COLOR_TEXTO_SUAVE = new Color(0x6B, 0x72, 0x80);
    public static final Color COLOR_BORDE = new Color(0xE5, 0xE7, 0xEB);
    public static final Color COLOR_FILA_ALTERNA = new Color(0xF9, 0xFA, 0xFB);
    public static final Color COLOR_PLACEHOLDER = new Color(0x9C, 0xA3, 0xAF);
    public static final Color COLOR_DESHABILITADO = new Color(0xD1, 0xD5, 0xDB);

    public static final Font FUENTE_NORMAL = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FUENTE_ETIQUETA = new Font("SansSerif", Font.BOLD, 14);
    public static final Font FUENTE_TITULO = new Font("SansSerif", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FUENTE_ENCABEZADO = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FUENTE_BOTON = new Font("SansSerif", Font.BOLD, 14);

    public static final Dimension TAM_CAMPO = new Dimension(280, 40);
    public static final Dimension TAM_BOTON = new Dimension(240, 42);
    public static final Dimension TAM_BOTON_DASHBOARD = new Dimension(240, 88);

    private UIStyles() {
    }

    /* ── Configuración de ventanas ── */

    public static void configurarVentana(javax.swing.JFrame frame, int ancho, int alto) {
        frame.setSize(ancho, alto);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(COLOR_FONDO);
    }

    public static JPanel crearRootPanel() {
        JPanel root = new JPanel(new java.awt.BorderLayout(0, 0));
        root.setBackground(COLOR_FONDO);
        return root;
    }

    public static JPanel crearEncabezado(String titulo, String subtitulo) {
        JPanel header = new JPanel(new java.awt.BorderLayout(0, 6));
        header.setBackground(COLOR_PRIMARIO);
        header.setBorder(new EmptyBorder(28, 36, 28, 36));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(FUENTE_ENCABEZADO);
        lblTitulo.setForeground(COLOR_BLANCO);

        JLabel lblSubtitulo = new JLabel(subtitulo);
        lblSubtitulo.setFont(FUENTE_SUBTITULO);
        lblSubtitulo.setForeground(new Color(219, 234, 254));

        header.add(lblTitulo, java.awt.BorderLayout.NORTH);
        header.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);
        return header;
    }

    public static JPanel centrarContenido(JComponent contenido) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(COLOR_FONDO);
        wrapper.setBorder(new EmptyBorder(28, 32, 32, 32));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        wrapper.add(contenido, gbc);
        return wrapper;
    }

    public static JPanel crearTarjeta() {
        JPanel tarjeta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_BLANCO);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.setColor(COLOR_BORDE);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        tarjeta.setOpaque(false);
        tarjeta.setBackground(COLOR_BLANCO);
        tarjeta.setBorder(new EmptyBorder(32, 36, 32, 36));
        tarjeta.setLayout(new GridBagLayout());
        return tarjeta;
    }

    public static JLabel crearTituloSeccion(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.LEFT);
        label.setFont(FUENTE_TITULO);
        label.setForeground(COLOR_PRIMARIO);
        label.setBorder(new EmptyBorder(0, 0, 4, 0));
        return label;
    }

    public static JLabel crearSubtituloSeccion(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.LEFT);
        label.setFont(FUENTE_NORMAL);
        label.setForeground(COLOR_TEXTO_SUAVE);
        label.setBorder(new EmptyBorder(0, 0, 20, 0));
        return label;
    }

    public static JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_ETIQUETA);
        label.setForeground(COLOR_TEXTO);
        return label;
    }

    public static JPanel crearSeparador() {
        JPanel sep = new JPanel();
        sep.setBackground(COLOR_BORDE);
        sep.setPreferredSize(new Dimension(10, 1));
        sep.setBorder(new EmptyBorder(8, 0, 8, 0));
        return sep;
    }

    public static JPanel crearPanelAcciones(JComponent... botones) {
        JPanel panel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 0));
        panel.setOpaque(false);
        for (JComponent boton : botones) {
            panel.add(boton);
        }
        return panel;
    }

    /* ── Campos de formulario ── */

    public static JTextField crearCampoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        aplicarEstiloCampo(campo);
        return campo;
    }

    public static JPasswordField crearCampoPassword(int columnas) {
        JPasswordField campo = new JPasswordField(columnas);
        aplicarEstiloCampo(campo);
        return campo;
    }

    public static <T> JComboBox<T> crearComboBox() {
        JComboBox<T> combo = new JComboBox<>();
        combo.setFont(FUENTE_NORMAL);
        combo.setBackground(COLOR_BLANCO);
        combo.setForeground(COLOR_TEXTO);
        combo.setPreferredSize(TAM_CAMPO);
        aplicarEstiloCampo(combo);
        return combo;
    }

    private static void aplicarEstiloCampo(JComponent campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setForeground(COLOR_TEXTO);
        campo.setBackground(COLOR_BLANCO);
        campo.setPreferredSize(TAM_CAMPO);
        aplicarBordeEnfoque(campo);
    }

    private static void aplicarBordeEnfoque(JComponent campo) {
        Border bordeNormal = crearBordeCampo(COLOR_BORDE, 1);
        Border bordeFocus = crearBordeCampo(COLOR_SECUNDARIO, 2);

        campo.setBorder(bordeNormal);
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.setBorder(bordeFocus);
            }

            @Override
            public void focusLost(FocusEvent e) {
                campo.setBorder(bordeNormal);
            }
        });
    }

    private static Border crearBordeCampo(Color color, int grosor) {
        int padding = grosor == 2 ? 9 : 10;
        return BorderFactory.createCompoundBorder(
                new RoundedLineBorder(color, grosor, 8),
                new EmptyBorder(padding, 12, padding, 12));
    }

    /* ── Botones ── */

    public static JButton crearBotonPrimario(String texto) {
        return crearBotonRedondeado(texto, COLOR_SECUNDARIO, COLOR_PRIMARIO_HOVER, COLOR_BLANCO, TAM_BOTON);
    }

    public static JButton crearBotonSecundario(String texto) {
        return crearBotonRedondeado(texto, COLOR_TEXTO_SUAVE, COLOR_TEXTO, COLOR_BLANCO, TAM_BOTON);
    }

    public static JButton crearBotonOutline(String texto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isEnabled() ? COLOR_BLANCO : COLOR_FILA_ALTERNA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(isEnabled() ? COLOR_SECUNDARIO : COLOR_DESHABILITADO);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(COLOR_SECUNDARIO);
        boton.setPreferredSize(TAM_BOTON);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(new EmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        agregarHoverOutline(boton);
        return boton;
    }

    public static JButton crearBotonDashboard(String titulo, String descripcion) {
        JButton boton = construirBotonDashboard(titulo, descripcion);
        boton.setBackground(COLOR_SECUNDARIO);
        agregarHoverBoton(boton, COLOR_SECUNDARIO, COLOR_PRIMARIO_HOVER);
        return boton;
    }

    public static JButton crearBotonDashboardSecundario(String titulo, String descripcion) {
        JButton boton = construirBotonDashboard(titulo, descripcion);
        boton.setBackground(COLOR_TEXTO_SUAVE);
        agregarHoverBoton(boton, COLOR_TEXTO_SUAVE, COLOR_TEXTO);
        return boton;
    }

    private static JButton construirBotonDashboard(String titulo, String descripcion) {
        JButton boton = new JButton("<html><center><b>" + titulo + "</b><br>"
                + "<span style='font-size:11px;font-weight:normal;color:#DBEAFE;'>" + descripcion
                + "</span></center></html>") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color fondo = isEnabled() ? getBackground() : COLOR_DESHABILITADO;
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(COLOR_BLANCO);
        boton.setPreferredSize(TAM_BOTON_DASHBOARD);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(new EmptyBorder(12, 16, 12, 16));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        return boton;
    }

    private static JButton crearBotonRedondeado(String texto, Color fondo, Color hover,
            Color textoColor, Dimension tamano) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = isEnabled() ? getBackground() : COLOR_DESHABILITADO;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setFont(FUENTE_BOTON);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setPreferredSize(tamano);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(new EmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        agregarHoverBoton(boton, fondo, hover);
        return boton;
    }

    private static void agregarHoverBoton(JButton boton, Color normal, Color hover) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setBackground(hover);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setBackground(normal);
                } else {
                    boton.setBackground(COLOR_DESHABILITADO);
                }
            }
        });
    }

    private static void agregarHoverOutline(JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setForeground(COLOR_PRIMARIO);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setForeground(COLOR_SECUNDARIO);
                }
            }
        });
    }

    /* ── Placeholders y filtros ── */

    public static void setPlaceholder(JTextField campo, String placeholder) {
        campo.setForeground(COLOR_PLACEHOLDER);
        campo.setText(placeholder);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (placeholder.equals(campo.getText())) {
                    campo.setText("");
                    campo.setForeground(COLOR_TEXTO);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(COLOR_PLACEHOLDER);
                    campo.setText(placeholder);
                }
            }
        });
    }

    public static String obtenerTexto(JTextField campo, String placeholder) {
        String texto = campo.getText().trim();
        return placeholder.equals(texto) ? "" : texto;
    }

    public static void limitarSoloNumeros(JTextField campo, int maxDigitos) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null && string.matches("\\d*")
                        && fb.getDocument().getLength() + string.length() <= maxDigitos) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null || text.matches("\\d*")) {
                    int nuevaLongitud = fb.getDocument().getLength() - length + (text == null ? 0 : text.length());
                    if (nuevaLongitud <= maxDigitos) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            }
        });
    }

    public static void limitarSoloLetras(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null && string.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null || text.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    /* ── Tablas ── */

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setRowHeight(36);
        tabla.setGridColor(COLOR_BORDE);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));
        tabla.setBackground(COLOR_BLANCO);
        tabla.setSelectionBackground(new Color(219, 234, 254));
        tabla.setSelectionForeground(COLOR_PRIMARIO);
        tabla.setForeground(COLOR_TEXTO);

        tabla.getTableHeader().setFont(FUENTE_ETIQUETA);
        tabla.getTableHeader().setBackground(COLOR_PRIMARIO);
        tabla.getTableHeader().setForeground(COLOR_BLANCO);
        tabla.getTableHeader().setPreferredSize(new Dimension(10, 40));
        tabla.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? COLOR_BLANCO : COLOR_FILA_ALTERNA);
                }
                c.setForeground(COLOR_TEXTO);
                setBorder(new EmptyBorder(0, 12, 0, 12));
                return c;
            }
        };
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        tabla.setDefaultRenderer(Object.class, renderer);
        tabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(COLOR_PRIMARIO);
                c.setForeground(COLOR_BLANCO);
                c.setFont(FUENTE_ETIQUETA);
                setBorder(new EmptyBorder(0, 12, 0, 12));
                return c;
            }
        });
    }

    public static void estilizarScrollTabla(JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(COLOR_BORDE, 1, 12),
                new EmptyBorder(0, 0, 0, 0)));
        scroll.getViewport().setBackground(COLOR_BLANCO);
        scroll.setOpaque(false);
    }

    /* ── Utilidades de layout ── */

    public static void agregarCampoFormulario(JPanel panel, GridBagConstraints gbc,
            int fila, String etiqueta, JComponent campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0.35;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 8, 16);
        panel.add(crearEtiqueta(etiqueta), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        panel.add(campo, gbc);
    }

    public static GridBagConstraints crearConstraintsFormulario() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        return gbc;
    }

    /* Compatibilidad con código anterior */
    public static void aplicarEstiloVentana(JComponent contentPane) {
        contentPane.setBackground(COLOR_FONDO);
    }

    public static JLabel crearTitulo(String texto) {
        return crearTituloSeccion(texto);
    }

    public static JPanel crearPanelFormulario() {
        return crearTarjeta();
    }

    /* Borde redondeado reutilizable */
    private static class RoundedLineBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;
        private final int radius;

        RoundedLineBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness + 2, thickness + 2, thickness + 2, thickness + 2);
        }
    }
}
