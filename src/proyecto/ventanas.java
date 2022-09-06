package proyecto;

import java.awt.Color;
import java.awt.Font;
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
    producto productos[] = new producto[100];
    int controlPro = 2;
    JPanel panelControlProducto;

    //metodo constructor
    public ventanas() {
        objetos();
        crearAdmin();
        crearClientes();
        crearPro();
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

    public void crearPro() {
        productos[0] = new producto();
        productos[0].nombrePro = "zapato casual";
        productos[0].precio = 250;
        productos[0].cantidad = 50;

        productos[1] = new producto();
        productos[1].nombrePro = "zapato deportivo";
        productos[1].precio = 230;
        productos[1].cantidad = 25;
    }

    public void objetos() {
        panelInicioSesion = new JPanel();
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Constantia", Font.BOLD, 15));
        lblLogin.setBounds(30, 10, 100, 20);
        panelInicioSesion.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Constantia", Font.BOLD, 13));
        lblUsuario.setBounds(60, 50, 100, 20);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setFont(new Font("Constantia", Font.BOLD, 13));
        lblContra.setBounds(60, 100, 100, 20);
        panelInicioSesion.add(lblContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 100, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(116, 236, 202));
        btnIngresar.setFont(new Font("Garamond", Font.BOLD, 15));
        btnIngresar.setBounds(150, 150, 200, 30);
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
        btnCrearUsu.setBackground(new Color(116, 236, 202));
        btnCrearUsu.setFont(new Font("Garamond", Font.BOLD, 15));
        btnCrearUsu.setBounds(150, 200, 200, 30);
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
        this.setSize(410, 300);
        this.setTitle("Control principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminCli = new JButton("Administración de clientes");
        btnAdminCli.setBackground(new Color(116, 236, 155));
        btnAdminCli.setFont(new Font("Garamond", Font.BOLD, 18));
        btnAdminCli.setBounds(50, 50, 300, 40);
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
        btnAdminPro.setBackground(new Color(243, 245, 96));
        btnAdminPro.setFont(new Font("Garamond", Font.BOLD, 18));
        btnAdminPro.setBounds(50, 140, 300, 40);
        panelControl.add(btnAdminPro);
        ActionListener AdministrarPro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelcontrolPro();
                panelControlProducto.setVisible(true);
            }
        };
        btnAdminPro.addActionListener(AdministrarPro);
    }

    public void CrearUsuario() {
        panelCrearUsuario = new JPanel();
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(500, 430);
        this.setTitle("Registro de usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblRegistro = new JLabel("Registro de usuario");
        lblRegistro.setFont(new Font("Constantia", Font.BOLD, 15));
        lblRegistro.setBounds(30, 10, 150, 20);
        panelCrearUsuario.add(lblRegistro);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Constantia", Font.BOLD, 13));
        lblUsuario.setBounds(60, 50, 100, 20);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Constantia", Font.BOLD, 13));
        lblNombre.setBounds(60, 100, 100, 20);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setFont(new Font("Constantia", Font.BOLD, 13));
        lblContra.setBounds(60, 150, 100, 20);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfirmar = new JLabel("Confirmar contraseña");
        lblConfirmar.setFont(new Font("Constantia", Font.BOLD, 13));
        lblConfirmar.setBounds(60, 200, 140, 20);
        panelCrearUsuario.add(lblConfirmar);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(220, 50, 160, 25);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(220, 100, 160, 25);
        panelCrearUsuario.add(txtNombre);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(220, 150, 160, 25);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfirmar = new JTextField();
        txtConfirmar.setBounds(220, 200, 160, 25);
        panelCrearUsuario.add(txtConfirmar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(116, 236, 202));
        btnRegistrar.setFont(new Font("Garamond", Font.BOLD, 18));
        btnRegistrar.setBounds(60, 270, 165, 30);
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
        btnRegresar.setBackground(new Color(116, 236, 202));
        btnRegresar.setFont(new Font("Garamond", Font.BOLD, 18));
        btnRegresar.setBounds(230, 270, 165, 30);
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

        //grafico de columnas
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
        btnCargarArchivo.setBackground(new Color(116, 236, 155));
        btnCargarArchivo.setFont(new Font("Garamond", Font.BOLD, 15));
        btnCargarArchivo.setBounds(400, 15, 200, 30);
        panelcontrolCli.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser abrirVentana = new JFileChooser();
                abrirVentana.showOpenDialog(null);
                archivoSeleccionado = abrirVentana.getSelectedFile();
                //System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                if (archivoSeleccionado == null) {
                    JOptionPane.showMessageDialog(null, "No se selecciono ningun archivo");
                } else {
                    leerArchivoCSV(archivoSeleccionado.getPath());
                    panelcontrolCli.setVisible(false);
                    panelcontrolClientes();
                }
            }
        };

        btnCargarArchivo.addActionListener(buscarArchivo);

        //boton de crear reporte 
        JButton btnReporte = new JButton("Generar reporte HTML");
        btnReporte.setBackground(new Color(116, 236, 155));
        btnReporte.setFont(new Font("Garamond", Font.BOLD, 15));
        btnReporte.setBounds(400, 70, 200, 30);
        panelcontrolCli.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ordenar();
                crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);

        //boton de regresar
        JButton btnRegresar = new JButton("Regresar a control principal");
        btnRegresar.setBackground(new Color(116, 236, 155));
        btnRegresar.setFont(new Font("Garamond", Font.BOLD, 15));
        btnRegresar.setBounds(400, 125, 200, 30);
        panelcontrolCli.add(btnRegresar);
        ActionListener regresarInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelcontrolCli.setVisible(false);
                volverInicio();
            }
        };
        btnRegresar.addActionListener(regresarInicio);

    }

    public void ordenar() {
        cliente auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int a = 0; a < 99; a++) {
                if (clientes[a + 1] == null) {
                    break;
                } else {
                    if (clientes[a].edad > clientes[a + 1].edad) {
                        auxiliar = clientes[a + 1];
                        clientes[a + 1] = clientes[a];
                        clientes[a] = auxiliar;
                    }
                }
            }
        }
    }

    public void crearReporte() {
        try {
            PrintWriter escribirCss = new PrintWriter("reportes/estilo.css", "UTF-8");
            escribirCss.print("html {   font-size: 20px; font-family: 'Antiqua', sans-serif; }");
	    escribirCss.print("h1 { font-size: 60px; text-align: center; }");
	    escribirCss.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
	    escribirCss.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px;  word-wrap: break-word}");
	    escribirCss.print("html { background-color: #B8FF83; }");
	    escribirCss.print("body { width: 970px; margin: 0 auto; background-color: #83ACFF; padding: 0 20px 20px 20px; border: 5px solid black; }");
	    escribirCss.print("h1 { margin: 0; padding: 20px 0; color: #00539F; text-shadow: 3px 3px 1px black; }");

            escribirCss.close();

            PrintWriter escribir = new PrintWriter("reportes/reporte.html", "UTF-8");
            escribir.println("<!doctype html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte de clientes</title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de clientes</h1>");
            escribir.println("<br>");

            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Genero</td>");
            escribir.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (clientes[i] != null) {
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
        } catch (IOException error) {
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

    public void panelcontrolPro() {
        panelControlProducto = new JPanel();
        this.getContentPane().add(panelControlProducto);
        panelControlProducto.setLayout(null);
        this.setSize(725, 450);
        this.setTitle("Administración Productos");
        panelControl.setVisible(false);

        DefaultTableModel datosPro = new DefaultTableModel();
        datosPro.addColumn("Nombre Producto");
        datosPro.addColumn("Precio");
        datosPro.addColumn("Cantidad");

        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                String filaPro[] = {productos[i].nombrePro, Double.toString(productos[i].precio), String.valueOf(productos[i].cantidad)};
                datosPro.addRow(filaPro);
            }
        }

        JTable tablaProductos = new JTable(datosPro);
        JScrollPane barraTablaPro = new JScrollPane(tablaProductos);
        barraTablaPro.setBounds(10, 10, 300, 250);
        panelControlProducto.add(barraTablaPro);
        
        //creacion de grafica de columnas productos 
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango50a100(), "50-100", "Precio");
        datos2.addValue(rango101a500(), "101-500", "Precio");
        datos2.addValue(rango500(), "Mayor a 500", "Precio");

        JFreeChart graficoColumnas2 = ChartFactory.createBarChart("Rango de precios", "Precio", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas2 = new ChartPanel(graficoColumnas2);
        panelColumnas2.setBounds(350, 10, 330, 250);
        panelControlProducto.add(panelColumnas2);

        JButton btnCargarArchivo2 = new JButton("Buscar archivo CSV");
        btnCargarArchivo2.setBackground(new Color(243, 245, 96));
        btnCargarArchivo2.setFont(new Font("Garamond", Font.BOLD, 15));
        btnCargarArchivo2.setBounds(10, 300, 200, 30);
        panelControlProducto.add(btnCargarArchivo2);
        ActionListener buscarArchivo2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado2;
                JFileChooser abrirVentana2 = new JFileChooser();
                abrirVentana2.showOpenDialog(null);
                archivoSeleccionado2 = abrirVentana2.getSelectedFile();
                if (archivoSeleccionado2 == null) {
                    JOptionPane.showMessageDialog(null, "No se selecciono ningun archivo");
                } else {
                    leerArchivo2CSV(archivoSeleccionado2.getPath());
                    panelControlProducto.setVisible(false);
                    panelcontrolPro();
                }
            }
        };
        btnCargarArchivo2.addActionListener(buscarArchivo2);
        
        JButton btnReporte2 = new JButton("Generar reporte HTML");
        btnReporte2.setBackground(new Color(243, 245, 96));
        btnReporte2.setFont(new Font("Garamond", Font.BOLD, 15));
        btnReporte2.setBounds(250, 300, 200, 30);
        panelControlProducto.add(btnReporte2);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ordenar2();
                crearReporte2();
            }
        };
        btnReporte2.addActionListener(crearHTML);
        
        JButton btnRegresar2 = new JButton("Regresar a control principal");
        btnRegresar2.setBackground(new Color(243, 245, 96));
        btnRegresar2.setFont(new Font("Garamond", Font.BOLD, 15));
        btnRegresar2.setBounds(490, 300, 210, 30);
        panelControlProducto.add(btnRegresar2);
        ActionListener regresarInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlProducto.setVisible(false);
                volverInicio();
            }
        };
        btnRegresar2.addActionListener(regresarInicio);
        
    }

    public void ordenar2() {
        producto auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int a = 0; a < 99; a++) {
                if (productos[a + 1] == null) {
                    break;
                } else {
                    if (productos[a].precio < productos[a + 1].precio) {
                        auxiliar = productos[a + 1];
                        productos[a + 1] = productos[a];
                        productos[a] = auxiliar;
                    }
                }
            }
        }
    }
    
    public void crearReporte2() {
        try {
            PrintWriter escribirCss = new PrintWriter("reportes2/estilo.css", "UTF-8");
            escribirCss.print("html {   font-size: 20px; font-family: 'Antiqua', sans-serif; }");
	    escribirCss.print("h1 { font-size: 60px; text-align: center; }");
	    escribirCss.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
	    escribirCss.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px;  word-wrap: break-word}");
	    escribirCss.print("html { background-color: #B8FF83; }");
	    escribirCss.print("body { width: 970px; margin: 0 auto; background-color: #83ACFF; padding: 0 20px 20px 20px; border: 5px solid black; }");
	    escribirCss.print("h1 { margin: 0; padding: 20px 0; color: #00539F; text-shadow: 3px 3px 1px black; }");

            escribirCss.close();

            PrintWriter escribir = new PrintWriter("reportes2/reporte2.html", "UTF-8");
            escribir.println("<!doctype html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte de productos</title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de productos</h1>");
            escribir.println("<br>");

            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>Cantidad</td> <td>Nombre Producto</td> <td>Precio</td>");
            escribir.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (productos[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + productos[i].cantidad + "</td><td>" + productos[i].nombrePro + "</td><td>" + productos[i].precio);
                    escribir.println("</tr>");
                }
            }
            escribir.println("</table>");
            escribir.println("</body>");
            escribir.println("</html>");
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, se encuentre en carpeta Reportes2");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }
    
    
    public int rango50a100() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >= 50 && productos[i].precio <= 100) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango101a500() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >= 101 && productos[i].precio <= 500) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango500() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio > 500) {
                    total++;
                }
            }
        }
        return total;
    }
    
    public void leerArchivo2CSV(String ruta2) {
        try {
            BufferedReader archivoTemporal2 = new BufferedReader(new FileReader(ruta2));
            String lineaLeida2 = "";
            while (lineaLeida2 != null) {
                lineaLeida2 = archivoTemporal2.readLine();
                if (lineaLeida2 != null) {
                    String datosSeparados2[] = lineaLeida2.split(",");
                    
                    int posicion = 0;
                    if (controlPro < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (productos[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        productos[posicion] = new producto();
                        productos[posicion].nombrePro = datosSeparados2[0];
                        productos[posicion].precio = Float.parseFloat(datosSeparados2[1]);
                        productos[posicion].cantidad = Integer.parseInt(datosSeparados2[2]);
                        controlPro++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden registrar más productos");
                    }

                }
            }
            JOptionPane.showMessageDialog(null, "productos registrados, total de productos " + controlPro);
            archivoTemporal2.close();
        } catch (IOException error2) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }
    
}
