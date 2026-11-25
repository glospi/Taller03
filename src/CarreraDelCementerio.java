/**
 * Carrera del Cementerio. Premia la habilidad de volar.
 */
public class CarreraDelCementerio implements Minijuego, VisitorMinijuego
{
    @Override
    public String nombre()
    {
        return "Carrera del Cementerio";
    }

    @Override
    public String descripcion()
    {
        return "Recorre el cementerio de punta a punta antes de que "
            + "el sepulturero te vea.";
    }

    @Override
    public String habilidadPremiada()
    {
        return "Volar";
    }

    @Override
    public double calcularPotencial(Monstruo monstruo)
    {
        return monstruo.aceptar(this);
    }

    @Override
    public double visitar(Zombi zombi)
    {
        return (double) zombi.getVelocidad() * zombi.getResistencia()
            / 100;
    }

    @Override
    public double visitar(Vampiro vampiro)
    {
        return (vampiro.getVelocidad()
            + 2.0 * vampiro.getVelocidadDeVuelo()) / 2;
    }

    @Override
    public double visitar(Momia momia)
    {
        return (double) momia.getVelocidad() * 100
            / (100 + momia.getLongitudVendajes());
    }

    @Override
    public double visitar(HombreLobo hombreLobo)
    {
        return (hombreLobo.getVelocidad()
            + hombreLobo.getFerocidad() / 5.0)
            * hombreLobo.getFaseLunar() / 4.0;
    }
}
