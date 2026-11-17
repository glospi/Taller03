/**
 * Monstruo genérico del juego. Guarda los atributos comunes y delega en un
 * visitante el cálculo del potencial de cada minijuego.
 */
public abstract class Monstruo
{
    private int vida;
    private int velocidad;
    private int fuerza;
    private int resistencia;

    public Monstruo(int vida, int velocidad, int fuerza, int resistencia)
    {
        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
    }

    public int getVida()
    {
        return vida;
    }

    public int getVelocidad()
    {
        return velocidad;
    }

    public int getFuerza()
    {
        return fuerza;
    }

    public int getResistencia()
    {
        return resistencia;
    }

    /**
     * Acepta un visitante para que calcule el potencial del monstruo.
     *
     * @param visitor minijuego que visita al monstruo
     * @return potencial del monstruo en ese minijuego
     */
    public abstract double aceptar(VisitorMinijuego visitor);

    /**
     * Entrega el nombre del tipo de monstruo.
     *
     * @return nombre usado en el catálogo y en los archivos
     */
    public abstract String getTipo();

    @Override
    public String toString()
    {
        return "Vida " + vida + " | Vel " + velocidad + " | Fue "
            + fuerza + " | Res " + resistencia;
    }
}
