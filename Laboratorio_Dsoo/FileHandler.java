
package Laboratorio_Dsoo;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FileHandler {

    public static void escribirLinea(String fileName, String content, boolean append) {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(fileName, append); 
            pw = new PrintWriter(fw); 

            pw.println(content);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + fileName);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar FileWriter.");
                }
            }
        }
    }

    public static void log(String logMessage) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String fullLog = "[" + timestamp + "] " + logMessage;
        escribirLinea("logs.txt", fullLog, true); 
    }
}