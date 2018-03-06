import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Clase que se encargada de analizar el archivo log que esta añadido desde un archivo externo
 * a la clase.  
 * 
 * @author (Fernando) 
 * @version (Version 1.0)
 */
public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    
    /**
     * Constructor de la clase AnalizadorAccesosAServidor
     */
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }
    
    /**
     * 
     * Método llamado analizarArchivoDeLog que requiere de un parámetro de tipo String que informe del nombre del archivo de log a leer.  
     * Este método debe leer el archivo de log y analizarlo para que luego podamos invocar el siguiente método.
     * 
     * @param te pide un string con el nombre del archivo mas .log
     * 
     */
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                String lineaLeida = sc.nextLine();               
                
                Acceso accesoActual = new Acceso(lineaLeida); 
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }
    
    /**
     * Metodo Tener un método llamado obtenerHoraMasAccesos que, a partir de los procesos hechos por el método anterior, 
     * encuentra la hora (solo la hora, sin tener en cuenta los minutos) a la que se producen más accesos al servidor. 
     * El método muestra por pantalla dicha hora y la devuelve. Si hay empate, devuelve la hora más alta. 
     * En caso de que se invoque este método sin haberse invocado el anterior el método informa por pantalla de que no tiene datos, devuelve -1 y no hace nada más.
     * 
     * @return devuelve un int con la fecha con mas accesos o - 1 si no tiene datos
     */
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;
        
        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];
    
            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }
            
            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }
            
            valorADevolver = horaDeAccesosMasAlto;                      
        }
        
        return valorADevolver;
    }

    
    
    public String paginaWebMasSolicitada() 
    {
        return "";
    }
    
    public String clienteConMasAccesosExitosos()
    {
        return "";
    }


}
