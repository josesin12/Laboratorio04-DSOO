package Laboratorio_Dsoo;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivo extends JFrame {

    private JTextArea textArea;

    public Archivo(String filePath, String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(600, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        cargarArchivo(filePath); 

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void cargarArchivo(String ruta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ruta));
            
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            
            textArea.setText(contenido.toString());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo cargar el archivo: " + ruta,
                    "Error de Carga",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Ignorar
                }
            }
        }
    }
}