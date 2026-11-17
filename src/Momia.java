/**
 * Momia. Implementa las habilidades de regenerarse y enfurecerse.
 */
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

    @Override
    public double aceptar(VisitorMinijuego visitor)
    {
        return visitor.visitar(this);
    }

    @Override
    public String getTipo()
    {
        return "Momia";
    }

    @Override
    public String toString()
    {
        return "Momia | " + super.toString() + " | Vendajes "
            + longitudVendajes;
    }
}
