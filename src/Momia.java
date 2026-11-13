public class Momia extends Monstruo implements Regenerable, Furioso
{
    private int longitudVendajes;

    public Momia(int vida, int velocidad, int fuerza, int resistencia,
                 int longitudVendajes)
    {
        super(vida, velocidad, fuerza, resistencia);
        this.longitudVendajes = longitudVendajes;
    }

    public int getLongitudVendajes()
    {
        return longitudVendajes;
    }
}
