/**
 * Minijuego del taller. Se describe a sí mismo y calcula el potencial de un
 * monstruo delegando en su visitante.
 */
public interface Minijuego
{
    String nombre();

    String descripcion();

    /**
     * Habilidad que otorga el multiplicador de puntaje.
     *
     * @return nombre de la habilidad premiada
     */
    String habilidadPremiada();

    /**
     * Calcula el potencial de un monstruo en este minijuego.
     *
     * @param monstruo monstruo que compite
     * @return potencial del monstruo en este minijuego
     */
    double calcularPotencial(Monstruo monstruo);
}
