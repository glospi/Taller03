public class Zombi extends Monstruo implements Mordedor, Regenerable
{
    private int fuerzaDeMordida;

    public Zombi(int vida, int velocidad, int fuerza, int resistencia,
                 int fuerzaDeMordida)
    {
        super(vida, velocidad, fuerza, resistencia);
        this.fuerzaDeMordida = fuerzaDeMordida;
    }

    public int getFuerzaDeMordida()
    {
        return fuerzaDeMordida;
    }

    @Override
    public String morder()
    {
        return "El zombi muerde con una fuerza de "
            + fuerzaDeMordida + ".";
    }

    @Override
    public String regenerarse()
    {
        return "El zombi regenera su cuerpo y recupera resistencia.";
    }

    @Override
    public double aceptar(VisitorMinijuego visitor)
    {
        return visitor.visitar(this);
    }

    @Override
    public String getTipo()
    {
        return "Zombi";
    }

    @Override
    public String toString()
    {
        return "Zombi | " + super.toString() + " | Mordida "
            + fuerzaDeMordida;
    }
}
