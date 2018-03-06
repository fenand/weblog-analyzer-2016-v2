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

    /**
     * Constructor de la clase acceso
     * 
     * @param pide un parametro de tipo string con la fecha
     */
    public Acceso(String fecha)
    {
        String[] arrayDeFechas = fecha.split(" ");
      
        ano = Integer.parseInt(arrayDeFechas[0]);
        mes = Integer.parseInt(arrayDeFechas[1]);
        dia = Integer.parseInt(arrayDeFechas[2]);
        hora = Integer.parseInt(arrayDeFechas[3]);
        minutos = Integer.parseInt(arrayDeFechas[4]);
        
      

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