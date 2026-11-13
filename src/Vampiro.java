public class Vampiro extends Monstruo implements Mordedor, Volador
{
    private int velocidadDeVuelo;
    private int resistenciaAlSol;

    public Vampiro(int vida, int velocidad, int fuerza, int resistencia,
                   int velocidadDeVuelo, int resistenciaAlSol)
    {
        super(vida, velocidad, fuerza, resistencia);
        this.velocidadDeVuelo = velocidadDeVuelo;
        this.resistenciaAlSol = resistenciaAlSol;
    }

    public int getVelocidadDeVuelo()
    {
        return velocidadDeVuelo;
    }

    public int getResistenciaAlSol()
    {
        return resistenciaAlSol;
    }
}
