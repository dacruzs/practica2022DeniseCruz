package proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ventanas extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion;
    JPanel panelControl;
    JPanel panelCrearUsuario;
    int control = 1;
    cliente clientes[] = new cliente[100];
    int controlCli = 2;
    JPanel panelcontrolCli;

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

    public void crearClientes() {
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
        panelInicioSesion = new JPanel();
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
        panelControl = new JPanel();
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
        panelCrearUsuario = new JPanel();
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

    //panel clientes
    public void panelcontrolClientes() {
        panelcontrolCli = new JPanel();
        this.getContentPane().add(panelcontrolCli);
        panelcontrolCli.setLayout(null);
        this.setSize(700, 600);
        this.setTitle("Administración de clientes");
        panelControl.setVisible(false);

        //tabla clientes
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Género");
        datosTabla.addColumn("Nit");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)};
                datosTabla.addRow(fila);
            }
        }

        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(15, 15, 300, 150);
        panelcontrolCli.add(barraClientes);

        //grafico circular
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", totalHombres());
        datos.setValue("Femenino", totalMujeres());

        JFreeChart graficaCircular = ChartFactory.createPieChart("Generos", datos);
        ChartPanel panelCircular = new ChartPanel(graficaCircular);
        panelCircular.setBounds(15, 200, 300, 300);
        panelcontrolCli.add(panelCircular);

        //grafico de barras
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18-30", "Edad");
        datos2.addValue(rango31a45(), "31-45", "Edad");
        datos2.addValue(rango45(), "Mayor a 45", "Edad");

        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de edad", "Edad", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(350, 200, 300, 300);
        panelcontrolCli.add(panelColumnas);

        //boton de cargar archivo csv
        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBounds(400, 15, 200, 30);
        panelcontrolCli.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser abrirVentana = new JFileChooser();
                abrirVentana.showOpenDialog(null);
                archivoSeleccionado = abrirVentana.getSelectedFile();
                System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
                panelcontrolCli.setVisible(false);
                panelcontrolClientes();
            }
        };

        btnCargarArchivo.addActionListener(buscarArchivo);

        //boton de crear reporte 
        JButton btnReporte = new JButton("Generar reporte HTML");
        btnReporte.setBounds(400, 70, 200, 30);
        panelcontrolCli.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);

        //Regresar al control
//            JButton btnRegresarControl = new JButton("Regresar");
//        btnRegresarControl.setBounds(400, 25, 200, 30);
//        panelcontrolCli.add(btnRegresarControl);
//        ActionListener regresarControl = new ActionListener() {
//            @Override
//           public void actionPerformed(ActionEvent ae) {
//                panelControl.setVisible(true);
//                panelcontrolCli.setVisible(false);
//                volverPanelControl();
//            }
//        };
//        btnRegresarControl.addActionListener(regresarControl);
//    }
//
//    public void volverPanelControl() {
//        this.setTitle("Control principal");
//        this.setSize(500, 400);
//    }
    }
    
    public void crearReporte(){
        try{
            PrintWriter escribir = new PrintWriter("reportes/reporte.html", "UTF-8");
            escribir.println("<!doctype html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte de clientes</title>");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de clientes</h1>");
            escribir.println("<br>");
            
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Genero</td>");
            escribir.println("</tr>");
                
            for(int i = 0; i<99; i++){
                if(clientes[0] != null){
                    escribir.println("<tr>");
                    escribir.println("<td>" + clientes[i].nit + "</td><td>" + clientes[i].nombre + "</td><td>" + clientes[i].edad + "</td><td>" + clientes[i].genero);
                    escribir.println("</tr>");
                } 
            }
            escribir.println("</table>");
            escribir.println("</body>");
            escribir.println("</html>");
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, se encuentre en carpeta Reportes");
        }catch(IOException error){
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }

    public int totalHombres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'M') {
                    total++;
                }
            }
        }
        return total;
    }

    public int totalMujeres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'F') {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango18a30() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 18 && clientes[i].edad <= 30) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango31a45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 31 && clientes[i].edad <= 45) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad > 45) {
                    total++;
                }
            }
        }
        return total;
    }

    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (controlCli < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].nombre = datosSeparados[0];
                        clientes[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        clientes[posicion].genero = datosSeparados[2].charAt(0);
                        clientes[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlCli++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden registrar más clientes");
                    }

                }
            }
            JOptionPane.showMessageDialog(null, "clientes registrados, total de clientes " + controlCli);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }
}
