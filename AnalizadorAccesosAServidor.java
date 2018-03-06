import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Metodo que devuelve la url con mas accesos
     * 
     * @return devuelve un string con la url con mas accesos o null si no se ha ejecutado antes el metodo anterior
     */
    public String paginaWebMasSolicitada() 
    {
        // Creamos un HashMap  con la url como key y el contador de accesos como el valor
        HashMap<String, Integer> listadoDeUrlYSusAccesos = new HashMap<>();
        String urlConMasAccesos = null;
        int numeroDeAccesosAUnaUrl = 0;

        if (accesos.size() > 0){
            for(Acceso url : accesos){
                String urlActual = url.getUrl();

                if(listadoDeUrlYSusAccesos.get(urlActual) == null){
                    listadoDeUrlYSusAccesos.put(urlActual, 1); 
                }
                else{
                    listadoDeUrlYSusAccesos.replace(urlActual, listadoDeUrlYSusAccesos.get(urlActual) + 1);
                }

                if(listadoDeUrlYSusAccesos.get(urlActual) > numeroDeAccesosAUnaUrl){
                    urlConMasAccesos = urlActual;
                    numeroDeAccesosAUnaUrl =  listadoDeUrlYSusAccesos.get(urlActual);
                }
            }
        }
        else{
            System.out.println("No Mames!! guey,ocurrio algun error al leer el archivo.");
        }
        return urlConMasAccesos;
    }

    /**
     * Metodo que devuelve la ip con  el mayor número de accesos exitosos al servidor. 
     * 
     * @return devuelve un string con la ip que tiene mayor número de accesos exitosos al servidor o null si no se ha ejecutado antes el metodo anterior
     */
    public String clienteConMasAccesosExitosos()
    {
        String direccionIpFumataBlanca = null;
        ArrayList<Acceso> accesosFumataBlanca = new ArrayList<Acceso>();
        HashMap<String, Integer> listaDeIpsConFumataBlanca = new HashMap<>();
        int cantidadDeVecesQueMasSeRepiteLaIp = 0;
        int ipAComparar = 0;
        for(Acceso conexionesBuenas : accesos){
            // los codigos de error menores de 400 en los accesos de esa ip al servidor son accesos exitosos
            if(conexionesBuenas.getCodigoDeError() < 400){
                accesosFumataBlanca.add(conexionesBuenas); 
            }
        }
        if (accesos.size() > 0){
            for(Acceso conexionesBuenas : accesosFumataBlanca){
                String ipActual = conexionesBuenas.getIp();

                if(listaDeIpsConFumataBlanca.get(ipActual) == null){
                    listaDeIpsConFumataBlanca.put(ipActual, 1);
                }
                else{
                    listaDeIpsConFumataBlanca.replace(ipActual, listaDeIpsConFumataBlanca.get(ipActual) + 1);
                }
                //lo primero que hacemos es separar la ip por sus puntos y quedarlos con el cuarto octeto
                int ipNueva = Integer.parseInt(ipActual.split("\\.")[3]);
                //contador del numero de conexiones exitosas
                int numeroDeConexionesExitosas = listaDeIpsConFumataBlanca.get(ipActual);
                if(numeroDeConexionesExitosas > cantidadDeVecesQueMasSeRepiteLaIp || (numeroDeConexionesExitosas == cantidadDeVecesQueMasSeRepiteLaIp && ipNueva > ipAComparar)){
                    cantidadDeVecesQueMasSeRepiteLaIp = numeroDeConexionesExitosas;
                    ipAComparar = ipNueva;
                    direccionIpFumataBlanca = ipActual;
                }
            }
        }
        else{
            System.out.println("No Mames!! guey,ocurrio algun error al leer el archivo.");
        }

        return direccionIpFumataBlanca;
    }

}
