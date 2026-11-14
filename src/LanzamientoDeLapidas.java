public class LanzamientoDeLapidas implements Minijuego
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
