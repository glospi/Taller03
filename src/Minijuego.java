/**
 * Minijuego del taller. Se describe a sí mismo y, como visitante, calcula el
 * potencial de cada monstruo.
 */
public interface Minijuego extends VisitorMinijuego
{
    String nombre();

    String descripcion();

    /**
     * Habilidad que otorga el multiplicador de puntaje.
     *
     * @return nombre de la habilidad premiada
     */
    String habilidadPremiada();
}
