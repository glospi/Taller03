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

    public abstract double aceptar(VisitorMinijuego visitor);
}
