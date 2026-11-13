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

    @Override
    public String regenerarse()
    {
        return "La momia se regenera con sus " + longitudVendajes
            + " metros de vendajes.";
    }

    @Override
    public String enfurecerse()
    {
        return "La momia se enfurece y ataca con fuerza "
            + getFuerza() + ".";
    }
}
