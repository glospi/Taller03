/**
 * Sobrevivir al Amanecer. Premia la habilidad de regenerarse.
 */
public class SobrevivirAlAmanecer implements Minijuego
{
    @Override
    public String nombre()
    {
        return "Sobrevivir al Amanecer";
    }

    @Override
    public String descripcion()
    {
        return "Aguanta en pie cuando salgan los primeros rayos de sol.";
    }

    @Override
    public String habilidadPremiada()
    {
        return "Regenerarse";
    }

    @Override
    public double visitar(Zombi zombi)
    {
        return (double) zombi.getResistencia() * zombi.getVida() / 100;
    }

    @Override
    public double visitar(Vampiro vampiro)
    {
        return (double) vampiro.getResistencia()
            * vampiro.getResistenciaAlSol() / 100;
    }

    @Override
    public double visitar(Momia momia)
    {
        return (momia.getResistencia() + momia.getVida()) / 2.0
            * (1 + momia.getLongitudVendajes() / 300.0);
    }

    @Override
    public double visitar(HombreLobo hombreLobo)
    {
        return (hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0
            * (5 - hombreLobo.getFaseLunar()) / 4.0;
    }
}
