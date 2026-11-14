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

    @Override
    public String morder()
    {
        return "El vampiro muerde con una fuerza de " + getFuerza() + ".";
    }

    @Override
    public String volar()
    {
        return "El vampiro vuela a una velocidad de "
            + velocidadDeVuelo + ".";
    }

    @Override
    public void aceptar(VisitorMinijuego visitor)
    {
        visitor.visitar(this);
    }
}
