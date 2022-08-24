package proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ventanas extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion = new JPanel();
    JPanel panelControl = new JPanel();
    JPanel panelCrearUsuario = new JPanel();
    int control = 1;
    cliente clientes[] = new cliente[100];
    int controlCli = 0;
    JPanel panelcontrolCli = new JPanel();
    
    //metodo constructor
    public ventanas() {
        objetos();
        crearAdmin();
        crearClientes();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].Nombre = "administrador";
        usuSistema[0].NombreUsuario = "admin";
        usuSistema[0].contra = "3333";
    }
    
    public void crearClientes(){
        clientes[0] = new cliente();
        clientes[0].nombre = "cliente 1";
        clientes[0].edad = 25;
        clientes[0].genero = 'F';
        clientes[0].nit = 450;
        
        clientes[1] = new cliente();
        clientes[1].nombre = "cliente 2";
        clientes[1].edad = 45;
        clientes[1].genero = 'M';
        clientes[1].nit = 850;
    }
    

    public void objetos() {
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(30, 10, 100, 20);
        panelInicioSesion.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 50, 100, 20);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(60, 100, 100, 20);
        panelInicioSesion.add(lblContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 100, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(140, 155, 170, 30);
        panelInicioSesion.add(btnIngresar);
        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
                } else {
                    recorrido(txtUsuario.getText(), txtContra.getText());
                }

            }

        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsu = new JButton("Registrarse");
        btnCrearUsu.setBounds(140, 200, 170, 30);
        panelInicioSesion.add(btnCrearUsu);
        ActionListener crearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CrearUsuario();
                panelCrearUsuario.setVisible(true);
            }
        };
        btnCrearUsu.addActionListener(crearUsuario);
    }

    public void recorrido(String NombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].NombreUsuario.equals(NombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bienvenidos al sistema usuario " + NombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }

        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void panelControl() {
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(500, 400);
        this.setTitle("Control principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminCli = new JButton("Administración de clientes");
        btnAdminCli.setBounds(120, 20, 250, 40);
        panelControl.add(btnAdminCli);
        ActionListener AdministrarCli = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelcontrolClientes();
                panelcontrolCli.setVisible(true);
            }
        };
        btnAdminCli.addActionListener(AdministrarCli);

        JButton btnAdminPro = new JButton("Administración de productos");
        btnAdminPro.setBounds(120, 90, 250, 40);
        panelControl.add(btnAdminPro);

        JButton btnReportes = new JButton("Administración de clientes");
        btnReportes.setBounds(120, 160, 250, 40);
        panelControl.add(btnReportes);
    }

    public void CrearUsuario() {
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(500, 430);
        this.setTitle("Registro de usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblRegistro = new JLabel("Registro de usuario");
        lblRegistro.setBounds(30, 10, 150, 20);
        panelCrearUsuario.add(lblRegistro);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 50, 100, 20);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(60, 100, 100, 20);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(60, 150, 100, 20);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfirmar = new JLabel("Confirmar contraseña");
        lblConfirmar.setBounds(60, 200, 130, 20);
        panelCrearUsuario.add(lblConfirmar);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 50, 135, 25);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(200, 100, 135, 25);
        panelCrearUsuario.add(txtNombre);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(200, 150, 135, 25);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfirmar = new JTextField();
        txtConfirmar.setBounds(200, 200, 135, 25);
        panelCrearUsuario.add(txtConfirmar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 260, 175, 35);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtNombre.getText().equals("") || txtContra.getText().equals("") || txtConfirmar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
                } else {
                    if (txtContra.getText().equals(txtConfirmar.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombre.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombre.setText("");
                        txtContra.setText("");
                        txtConfirmar.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "las contraseñas no coinciden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(150, 320, 175, 35);
        panelCrearUsuario.add(btnRegresar);
        ActionListener regresarInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelInicioSesion.setVisible(true);
                panelCrearUsuario.setVisible(false);
                volverInicio();
            }
        };
        btnRegresar.addActionListener(regresarInicio);
    }

    public void volverInicio() {
        this.setTitle("Sistema administrativo de clientes y recursos");
        this.setSize(450, 350);
    }

    public void guardarUsuario(String NombreUsuario, String Nombre, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            //System.out.println("la posicion libre es: " + posicion);
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].NombreUsuario = NombreUsuario;
            usuSistema[posicion].Nombre = Nombre;
            usuSistema[posicion].contra = contra;
            control++;
            JOptionPane.showMessageDialog(null, "usurio registrado");
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más usuarios");
        }
    }
    
    public void panelcontrolClientes(){
        this.getContentPane().add(panelcontrolCli);
        panelcontrolCli.setLayout(null);
        this.setSize(500, 400);
        this.setTitle("Administración de clientes");
        panelControl.setVisible(false);
        
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Género");
        datosTabla.addColumn("Nit");
        
        String fila [] = {"Felix", "20", "M", "174"};
        datosTabla.addRow(fila);
        
        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(15, 15, 250, 250);
        panelcontrolCli.add(barraClientes);  
    }
}
