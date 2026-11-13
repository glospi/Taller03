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
}
