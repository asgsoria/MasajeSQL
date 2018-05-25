
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasajeBase extends JFrame {

    public static Statement sentencia;
    public static Connection conexion;

    private JPanel contentPane;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;

    DefaultTableModel modelo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MasajeBase frame = new MasajeBase();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Connection ConexionBase() throws SQLException, ClassNotFoundException {
        String controlador = "com.mysql.jdbc.Driver";
        Class.forName(controlador);
        String DSN = "jdbc:mysql://localhost:3306/salonmasajes";
        String user = "alvaro";
        String password = "1234";
        conexion = DriverManager.getConnection(DSN, user, password);
        return conexion;
    }

    public void tableMasajistaRefresh() throws SQLException {
        PreparedStatement agr = this.conexion.prepareStatement(
                "SELECT * FROM masajista");
        ResultSet masajistasResult = agr.executeQuery();
        while (masajistasResult.next()) {
            String nombre = masajistasResult.getString("masajista_pri");
            String apellido = masajistasResult.getString("apellido");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{nombre, apellido});
        }
    }

    /**
     * Create the frame.
     */
    public MasajeBase() {
        try {
            this.conexion = this.ConexionBase();

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 519, 358);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setBounds(12, 0, 493, 289);
            contentPane.add(tabbedPane);

            JPanel panel1 = new JPanel();

            tabbedPane.addTab("Masajista", null, panel1, null);
            panel1.setLayout(null);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(12, 110, 464, 112);
            panel1.add(scrollPane);

            table = new JTable();
            table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Apellido", "Nombre"}));
            scrollPane.setViewportView(table);
            tableMasajistaRefresh();
            textField = new JTextField();
            textField.setBounds(175, 12, 114, 19);
            panel1.add(textField);
            textField.setColumns(10);

            JButton btnAgregar = new JButton("Agregar");
            btnAgregar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement agr = conexion.prepareStatement(
                                "INSERT INTO masajista(masajista_pri,apellido) VALUES (?,?)");

                        agr.setString(1, textField.getText());
                        agr.setString(2, textField_1.getText());

                        agr.executeUpdate();
                        tableMasajistaRefresh();
                    } catch (Exception e) {
                        System.out.print(e);
                    }

                }

            });
            btnAgregar.setBounds(0, 225, 117, 25);
            panel1.add(btnAgregar);

            JButton btnModificar = new JButton("Modificar");
            btnModificar.setBounds(124, 225, 117, 25);
            panel1.add(btnModificar);

            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addMouseListener(new MouseAdapter() {

            });
            btnEliminar.setBounds(242, 225, 117, 25);
            panel1.add(btnEliminar);

            JButton btnLimpiar = new JButton("Limpiar");
            btnLimpiar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText("");
                    textField_1.setText("");
                }
            });
            btnLimpiar.setBounds(371, 225, 117, 25);
            panel1.add(btnLimpiar);

            JLabel lblApellido = new JLabel("Apellido");
            lblApellido.setBounds(80, -4, 77, 50);
            panel1.add(lblApellido);

            JLabel lblNombre = new JLabel("Nombre");
            lblNombre.setBounds(81, 43, 77, 50);
            panel1.add(lblNombre);

            textField_1 = new JTextField();
            textField_1.setBounds(175, 59, 114, 19);
            panel1.add(textField_1);
            textField_1.setColumns(10);

            JPanel panel2 = new JPanel();
            tabbedPane.addTab("Servicio", null, panel2, null);
            panel2.setLayout(null);

            JScrollPane scrollPane_1 = new JScrollPane();
            scrollPane_1.setBounds(12, 110, 464, 112);
            panel2.add(scrollPane_1);

            table_1 = new JTable();
            table_1.setModel(new DefaultTableModel(new Object[][]{},
                    new String[]{"Precio", "Masaje", "Servicio", "Descripcion"}));
            scrollPane_1.setViewportView(table_1);

            JLabel lblPrecio = new JLabel("Precio");
            lblPrecio.setBounds(12, 12, 50, 50);
            panel2.add(lblPrecio);

            textField_2 = new JTextField();
            textField_2.setBounds(72, 28, 114, 19);
            panel2.add(textField_2);
            textField_2.setColumns(10);

            JLabel lblMasaje = new JLabel("Masaje");
            lblMasaje.setBounds(12, 47, 62, 50);
            panel2.add(lblMasaje);

            textField_3 = new JTextField();
            textField_3.setBounds(72, 63, 114, 19);
            panel2.add(textField_3);
            textField_3.setColumns(10);

            JLabel lblServicio = new JLabel("Servicio");
            lblServicio.setBounds(237, 12, 62, 50);
            panel2.add(lblServicio);

            textField_4 = new JTextField();
            textField_4.setBounds(301, 28, 114, 19);
            panel2.add(textField_4);
            textField_4.setColumns(10);

            JLabel lblDescripcion = new JLabel("Descripcion");
            lblDescripcion.setBounds(208, 47, 91, 50);
            panel2.add(lblDescripcion);

            textField_5 = new JTextField();
            textField_5.setBounds(301, 63, 114, 19);
            panel2.add(textField_5);
            textField_5.setColumns(10);

            JButton btnAgregar_1 = new JButton("Agregar");
            btnAgregar_1.setBounds(0, 225, 117, 25);
            panel2.add(btnAgregar_1);

            JButton btnModificar_1 = new JButton("Modificar");
            btnModificar_1.setBounds(124, 225, 117, 25);
            panel2.add(btnModificar_1);

            JButton btnEliminar_1 = new JButton("Eliminar");
            btnEliminar_1.setBounds(242, 225, 117, 25);
            panel2.add(btnEliminar_1);

            JButton btnLimpiar_1 = new JButton("Limpiar");
            btnLimpiar_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_5.setText("");
                }
            });
            btnLimpiar_1.setBounds(371, 225, 117, 25);
            panel2.add(btnLimpiar_1);

            JPanel panel3 = new JPanel();
            tabbedPane.addTab("Sala", null, panel3, null);
            panel3.setLayout(null);

            JScrollPane scrollPane_2 = new JScrollPane();
            scrollPane_2.setBounds(12, 110, 464, 112);
            panel3.add(scrollPane_2);

            table_2 = new JTable();
            table_2.setModel(
                    new DefaultTableModel(new Object[][]{}, new String[]{"Sala", "Color", "Masajista", "Cliente"}));
            scrollPane_2.setViewportView(table_2);

            JLabel lblSala = new JLabel("Sala");
            lblSala.setBounds(33, 10, 40, 50);
            panel3.add(lblSala);

            textField_6 = new JTextField();
            textField_6.setBounds(78, 26, 114, 19);
            panel3.add(textField_6);
            textField_6.setColumns(10);

            JLabel lblColor = new JLabel("Color");
            lblColor.setBounds(43, 48, 46, 50);
            panel3.add(lblColor);

            textField_7 = new JTextField();
            textField_7.setBounds(88, 67, 114, 19);
            panel3.add(textField_7);
            textField_7.setColumns(10);

            JLabel lblMasajista = new JLabel("Masajista");
            lblMasajista.setBounds(210, 10, 76, 50);
            panel3.add(lblMasajista);

            JLabel lblCliente = new JLabel("Cliente");
            lblCliente.setBounds(220, 48, 68, 50);
            panel3.add(lblCliente);

            JComboBox comboBox = new JComboBox();
            comboBox.setBounds(289, 23, 114, 25);
            panel3.add(comboBox);

            JComboBox comboBox_1 = new JComboBox();
            comboBox_1.setBounds(289, 61, 117, 25);
            panel3.add(comboBox_1);

            JButton btnAgregar_2 = new JButton("Agregar");
            btnAgregar_2.setBounds(0, 225, 117, 25);
            panel3.add(btnAgregar_2);

            JButton btnModificar_2 = new JButton("Modificar");
            btnModificar_2.setBounds(124, 225, 117, 25);
            panel3.add(btnModificar_2);

            JButton btnEliminar_2 = new JButton("Eliminar");
            btnEliminar_2.setBounds(242, 225, 117, 25);
            panel3.add(btnEliminar_2);

            JButton btnLimpiar_2 = new JButton("Limpiar");
            btnLimpiar_2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField_6.setText("");
                    textField_7.setText("");
                    comboBox.setSelectedIndex(0);
                    comboBox_1.setSelectedIndex(0);

                }
            });
            btnLimpiar_2.setBounds(371, 225, 117, 25);
            panel3.add(btnLimpiar_2);

            JPanel panel4 = new JPanel();
            tabbedPane.addTab("Factura", null, panel4, null);
            panel4.setLayout(null);

            JScrollPane scrollPane_3 = new JScrollPane();
            scrollPane_3.setBounds(12, 110, 464, 112);
            panel4.add(scrollPane_3);

            table_3 = new JTable();
            table_3.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Factura", "Pais", "Servicio"}));

            JComboBox comboBox_2 = new JComboBox();
            comboBox_2.setBounds(309, 36, 124, 24);
            panel4.add(comboBox_2);

            scrollPane_3.setViewportView(table_3);

            JButton btnAgregar_3 = new JButton("Agregar");
            btnAgregar_3.setBounds(0, 225, 117, 25);
            panel4.add(btnAgregar_3);

            JButton btnModificar_3 = new JButton("Modificar");
            btnModificar_3.setBounds(124, 225, 117, 25);
            panel4.add(btnModificar_3);

            JButton btnEliminar_3 = new JButton("Eliminar");
            btnEliminar_3.setBounds(242, 225, 117, 25);
            panel4.add(btnEliminar_3);

            JButton btnLimpiar_3 = new JButton("Limpiar");
            btnLimpiar_3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField_8.setText("");
                    textField_9.setText("");
                    comboBox_2.setSelectedIndex(0);
                }
            });
            btnLimpiar_3.setBounds(371, 225, 117, 25);
            panel4.add(btnLimpiar_3);

            JLabel lblFactura = new JLabel("Factura");
            lblFactura.setBounds(10, 10, 64, 50);
            panel4.add(lblFactura);

            textField_8 = new JTextField();
            textField_8.setBounds(82, 26, 114, 19);
            panel4.add(textField_8);
            textField_8.setColumns(10);

            JLabel lblPais = new JLabel("Pais");
            lblPais.setBounds(31, 48, 43, 50);
            panel4.add(lblPais);

            textField_9 = new JTextField();
            textField_9.setBounds(82, 64, 114, 19);
            panel4.add(textField_9);
            textField_9.setColumns(10);

            JLabel lblServicio_1 = new JLabel("Servicio");
            lblServicio_1.setBounds(242, 23, 64, 50);
            panel4.add(lblServicio_1);

            JPanel panel5 = new JPanel();
            tabbedPane.addTab("Cliente", null, panel5, null);
            panel5.setLayout(null);

            JScrollPane scrollPane_4 = new JScrollPane();
            scrollPane_4.setBounds(12, 110, 464, 112);
            panel5.add(scrollPane_4);

            table_4 = new JTable();
            table_4.setModel(new DefaultTableModel(new Object[][]{},
                    new String[]{"Dni", "Pais", "Nombre", "Apellido", "Factura"}));

            JComboBox comboBox_3 = new JComboBox();
            comboBox_3.setBounds(209, 61, 123, 25);
            panel5.add(comboBox_3);

            JComboBox comboBox_4 = new JComboBox();
            comboBox_4.setBounds(415, 61, 61, 25);
            panel5.add(comboBox_4);

            scrollPane_4.setViewportView(table_4);

            JButton btnAgregar_4 = new JButton("Agregar");
            btnAgregar_4.setBounds(0, 225, 117, 25);
            panel5.add(btnAgregar_4);

            JButton btnModificar_4 = new JButton("Modificar");
            btnModificar_4.setBounds(124, 225, 117, 25);
            panel5.add(btnModificar_4);

            JButton btnEliminar_4 = new JButton("Eliminar");
            btnEliminar_4.setBounds(242, 225, 117, 25);
            panel5.add(btnEliminar_4);

            JButton btnLimpiar_4 = new JButton("Limpiar");
            btnLimpiar_4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField_10.setText("");
                    textField_11.setText("");
                    textField_12.setText("");
                    comboBox_3.setSelectedIndex(0);
                    comboBox_4.setSelectedIndex(0);
                }
            });
            btnLimpiar_4.setBounds(371, 225, 117, 25);
            panel5.add(btnLimpiar_4);

            JLabel lblDni = new JLabel("Dni");
            lblDni.setBounds(181, 48, 30, 50);
            panel5.add(lblDni);

            JLabel lblPais_1 = new JLabel("Pais");
            lblPais_1.setBounds(12, 48, 44, 50);
            panel5.add(lblPais_1);

            textField_10 = new JTextField();
            textField_10.setBounds(49, 64, 114, 19);
            panel5.add(textField_10);
            textField_10.setColumns(10);

            JLabel lblNombre_1 = new JLabel("Nombre");
            lblNombre_1.setBounds(21, 1, 58, 50);
            panel5.add(lblNombre_1);

            textField_11 = new JTextField();
            textField_11.setBounds(97, 17, 114, 19);
            panel5.add(textField_11);
            textField_11.setColumns(10);

            JLabel lblApellido_1 = new JLabel("Apellido");
            lblApellido_1.setBounds(235, -1, 73, 50);
            panel5.add(lblApellido_1);

            textField_12 = new JTextField();
            textField_12.setBounds(307, 16, 114, 19);
            panel5.add(textField_12);
            textField_12.setColumns(10);

            JLabel lblFactura_1 = new JLabel("Factura");
            lblFactura_1.setBounds(350, 48, 58, 50);
            panel5.add(lblFactura_1);
            ;
        } catch (SQLException ex) {
            Logger.getLogger(MasajeBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasajeBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
