
package Laboratorio_Dsoo;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Escritura {

    /**
     * Escribe una línea de texto en un archivo.
     * @param fileName La ruta del archivo (ej. "logs.txt").
     * @param content La línea o bloque de contenido a escribir.
     * @param append Si es 'true', agrega el texto al final; si es 'false', sobrescribe.
     */
    public static void escribirLinea(String fileName, String content, boolean append) {
        try (FileWriter fw = new FileWriter(fileName, append);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(content);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * Método para registrar un log con marca de tiempo.

     * @param logMessage El mensaje del log a registrar.
     */
    public static void log(String logMessage) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String fullLog = "[" + timestamp + "] " + logMessage;
        escribirLinea("logs.txt", fullLog, true); 
    }
}