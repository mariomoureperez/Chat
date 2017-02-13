package clientemens;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mmoureperez
 */
public class ClienteMens {

    public static void main(String[] args) throws IOException {

        try {
            System.out.println("Creando socket cliente");
            //creación del socket pasando la dirección IP y el puerto del servidor
            Socket clienteSocket = new Socket("localhost", 5555);
            System.out.println("Estableciendo la conexion");

            PrintStream salida = new PrintStream(clienteSocket.getOutputStream());
            DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());

            String mensajeServ;
            
            String mensaje = JOptionPane.showInputDialog("Introduzca el mensaje del cliente");
            salida.println(mensaje);
            System.out.println("Mensaje del cliente: "+mensaje);
            salida.flush();
            System.out.println("Mensaje enviado");
            
            String entradaServ;
            
            while((entradaServ=entrada.readLine())!=null){
                System.out.println("Mensaje del servidor : "+entradaServ);
        
                mensaje = JOptionPane.showInputDialog("Introduzca el mensaje del Cliente");
                salida.println(mensaje);
                System.out.println("Mensaje del cliente: "+mensaje);
                salida.flush();
                    if(mensaje.equals("adios")){
                        break;
                    }
            }
 
            //leemos el resultado enviado por el servidor
            
           //System.out.println("resultado de la operacion: " + entrada.readLine());
           salida.close();
            entrada.close();
            System.out.println("Cerrando el socket cliente");
            //cerramos conexión
            clienteSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteMens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
