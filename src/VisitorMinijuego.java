/**
 * Visitante que calcula el potencial de un monstruo en un minijuego.
 */
public interface VisitorMinijuego
{
    /**
     * Calcula el potencial de un zombi.
     *
     * @param zombi monstruo visitado
     * @return potencial en este minijuego
     */
    double visitar(Zombi zombi);

    /**
     * Calcula el potencial de un vampiro.
     *
     * @param vampiro monstruo visitado
     * @return potencial en este minijuego
     */
    double visitar(Vampiro vampiro);

    /**
     * Calcula el potencial de una momia.
     *
     * @param momia monstruo visitado
     * @return potencial en este minijuego
     */
    double visitar(Momia momia);

    /**
     * Calcula el potencial de un hombrelobo.
     *
     * @param hombreLobo monstruo visitado
     * @return potencial en este minijuego
     */
    double visitar(HombreLobo hombreLobo);
}
