package proyecto;

import javax.swing.JFrame;

public class Proyecto {

    public static void main(String[] args) {
       ventanas cuadro = new ventanas();
       cuadro.setVisible(true);
       cuadro.setTitle("Sistema administrativo de clientes y recursos");
       cuadro.setSize(450, 350);
       cuadro.setLocationRelativeTo(null);
       cuadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
