import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("JUEGO DE LOS SOLDADOS");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        JButton boton = new JButton("has clic aqui");
        boton.addActionListener( e -> JOptionPane.showMessageDialog(this, "holaaaa"));
        add(boton);
        setVisible(true);
        


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
    
}
