package clientemens;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author mmoureperez
 */
public class ClienteMens {

    public static void main(String[] args) throws IOException {
        

        System.setProperty("javax.net.ssl.trustStore", "clientTrustedCerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

        try {
            System.out.println("Creando socket cliente");
            SSLSocketFactory SOFactory=(SSLSocketFactory)SSLSocketFactory.getDefault();
            //creación del socket pasando la dirección IP y el puerto del servidor
           // Socket clienteSocket = new Socket("localhost", 5555);
                       
            Socket clienteSocket = SOFactory.createSocket("localhost", 5555);
            System.out.println("Estableciendo la conexion");
//agadfafas
            /*cambiamos los flujos originales por estes dos porque los originales daban problemas al leer 
            y estes si lo hacen a la perfección */
            
            //Creamos flujo de salida cara el server
            PrintStream salida = new PrintStream(clienteSocket.getOutputStream());
            //Creamos flujo de entrada desde el server
            DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());

            
            //enviamos mensaje de cliente a servidor
            String mensaje = JOptionPane.showInputDialog("Introduzca el mensaje del cliente");
            salida.println(mensaje);
            System.out.println("Mensaje del cliente: "+mensaje);
            salida.flush();
            System.out.println("Mensaje enviado");
            
            String entradaServ;
            //Bucle que leera lo enviado desde servidor mientras tenga algo qeu leer
            while((entradaServ=entrada.readLine())!=null){
                
                System.out.println("Mensaje del servidor : "+entradaServ);
                mensaje = JOptionPane.showInputDialog("Introduzca el mensaje del Cliente");
                salida.println(mensaje);
                System.out.println("Mensaje del cliente: "+mensaje);
                salida.flush();
                
                //cuando utilizamos la palabra clave adios el cliente se cerrará
                    if(mensaje.equals("adios")){
                        break;
                    }
            }
 
          
            
            //Cerramos flujos de entra y salida
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
