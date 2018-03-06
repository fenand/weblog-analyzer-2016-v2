/**
 * Clase que se encarga de crear onjetos tipo acceso.
 * 
 * @author (Fernando) 
 * @version (Version 1.0)
 */
public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private String ip;
    private String url;
    private int codigoDeError;

    /**
     * Constructor de la clase acceso
     * 
     * @param pide un parametro de tipo string con la fecha
     */
    public Acceso(String fecha)
    {
        String[] arrayDeDatos = fecha.split(" ");
        
        //"91.244.73.61 [2016 01 01 10 56] instituto/normativa.html 403"
        
        //posicion 0 del arrayconfechas 
        ip = arrayDeDatos[0];
        
        //se inicializan los atributos  con los datos de la fecha que estan en la posicion 1 del array        
        ano = Integer.parseInt(arrayDeDatos[1].substring(1,arrayDeDatos[1].length()));
        mes = Integer.parseInt(arrayDeDatos[2]);
        dia = Integer.parseInt(arrayDeDatos[3]);
        hora = Integer.parseInt(arrayDeDatos[4]);
        minutos = Integer.parseInt(arrayDeDatos[5].substring(0,arrayDeDatos[5].length() -1 ));
        
        //posicion 3 del array con la url
        url = arrayDeDatos[2];
        
        //posicion 4 del array con el codigo de error
        codigoDeError = Integer.parseInt(arrayDeDatos[3]);

    }

    /**
     * metodo para devolver el año
     * 
     * @return un int con el año
     */
    public int getAno() 
    {
        return ano;
    }

    /**
     * Metodo para devolver el mes
     * 
     * @return un int con el mes
     */
    public int getMes()
    {
        return mes;
    }

    /**
     * Metodo para devolver el dia
     * 
     * @return un int con el dia
     */
    public int getDia()
    {
        return dia;
    }

    /**
     * Metodo para devolver la hora
     * 
     * @return un int con la hora
     */
    public int getHora()
    {
        return hora;
    }

    /**
     * Metodo para devolver los minutos
     * 
     * @return un int con los minutos
     */
    public int getMinutos()
    {
        return minutos;
    }

}