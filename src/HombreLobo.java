public class HombreLobo extends Monstruo implements Mordedor, Furioso
{
    private int ferocidad;
    private int faseLunar;

    public HombreLobo(int vida, int velocidad, int fuerza, int resistencia,
                      int ferocidad, int faseLunar)
    {
        super(vida, velocidad, fuerza, resistencia);
        this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;
    }

    public int getFerocidad()
    {
        return ferocidad;
    }

    public int getFaseLunar()
    {
        return faseLunar;
    }
}
