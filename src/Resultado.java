/**
 * Resultado de una jugada: minijuego, monstruo, habilidad usada y puntaje.
 */
public class Resultado
{
    private Minijuego minijuego;
    private Monstruo monstruo;
    private String habilidad;
    private int puntaje;

    public Resultado(Minijuego minijuego, Monstruo monstruo,
                     String habilidad, int puntaje)
    {
        this.minijuego = minijuego;
        this.monstruo = monstruo;
        this.habilidad = habilidad;
        this.puntaje = puntaje;
    }

    public Minijuego getMinijuego()
    {
        return minijuego;
    }

    public Monstruo getMonstruo()
    {
        return monstruo;
    }

    public String getHabilidad()
    {
        return habilidad;
    }

    public int getPuntaje()
    {
        return puntaje;
    }
}
