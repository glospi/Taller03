/**
 * Lanzamiento de Lápidas. Premia la habilidad de enfurecerse.
 */
public class LanzamientoDeLapidas implements Minijuego, VisitorMinijuego
{
    @Override
    public String nombre()
    {
        return "Lanzamiento de Lápidas";
    }

    @Override
    public String descripcion()
    {
        return "Lanza una lápida lo más lejos posible.";
    }

    @Override
    public String habilidadPremiada()
    {
        return "Enfurecerse";
    }

    @Override
    public double calcularPotencial(Monstruo monstruo)
    {
        return monstruo.aceptar(this);
    }

    @Override
    public double visitar(Zombi zombi)
    {
        return (double) zombi.getFuerza() * zombi.getResistencia() / 100;
    }

    @Override
    public double visitar(Vampiro vampiro)
    {
        return vampiro.getFuerza() + vampiro.getVelocidadDeVuelo() / 4.0;
    }

    @Override
    public double visitar(Momia momia)
    {
        return momia.getFuerza() + momia.getLongitudVendajes() / 10.0;
    }

    @Override
    public double visitar(HombreLobo hombreLobo)
    {
        return hombreLobo.getFuerza()
            + hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8.0;
    }
}
