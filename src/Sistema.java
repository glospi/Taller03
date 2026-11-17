import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Lógica y datos del taller: monstruos, jugadores, minijuegos, historial de
 * la sesión y récords. Los métodos {@code agregar...} validan los rangos de
 * los atributos y devuelven {@code false} si algún valor queda fuera.
 */
public class Sistema
{
    /** Estado de cierre cuando no se jugó ningún minijuego. */
    public static final int SIN_PARTIDAS = 0;
    /** Estado de cierre cuando el jugador se registra por primera vez. */
    public static final int JUGADOR_NUEVO = 1;
    /** Estado de cierre cuando el puntaje de la sesión supera el récord. */
    public static final int RECORD_SUPERADO = 2;
    /** Estado de cierre cuando el récord no se supera. */
    public static final int RECORD_MANTENIDO = 3;

    private ArrayList<Monstruo> monstruos;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Minijuego> minijuegos;
    private LinkedList<Resultado> historial;
    private ArrayList<Monstruo> monstruosDisponibles;
    private Jugador jugadorActual;
    private boolean jugadorNuevo;
    private int puntajeSesion;

    public Sistema()
    {
        monstruos = new ArrayList<Monstruo>();
        jugadores = new ArrayList<Jugador>();
        historial = new LinkedList<Resultado>();
        monstruosDisponibles = new ArrayList<Monstruo>();
        jugadorActual = null;
        jugadorNuevo = false;
        puntajeSesion = 0;

        minijuegos = new ArrayList<Minijuego>();
        minijuegos.add(new CarreraDelCementerio());
        minijuegos.add(new CazaDeCalabazas());
        minijuegos.add(new LanzamientoDeLapidas());
        minijuegos.add(new SobrevivirAlAmanecer());
    }

    public boolean agregarZombi(int vida, int velocidad, int fuerza,
                                int resistencia, int fuerzaDeMordida)
    {
        if (!rango(vida, 1, 200) || !rango(velocidad, 1, 100)
            || !rango(fuerza, 1, 100) || !rango(resistencia, 1, 100)
            || !rango(fuerzaDeMordida, 1, 100))
            return false;

        monstruos.add(new Zombi(vida, velocidad, fuerza, resistencia,
                                fuerzaDeMordida));
        return true;
    }

    public boolean agregarVampiro(int vida, int velocidad, int fuerza,
                                  int resistencia, int velocidadDeVuelo,
                                  int resistenciaAlSol)
    {
        if (!rango(vida, 1, 200) || !rango(velocidad, 1, 100)
            || !rango(fuerza, 1, 100) || !rango(resistencia, 1, 100)
            || !rango(velocidadDeVuelo, 1, 100)
            || !rango(resistenciaAlSol, 1, 100))
            return false;

        monstruos.add(new Vampiro(vida, velocidad, fuerza, resistencia,
                                  velocidadDeVuelo, resistenciaAlSol));
        return true;
    }

    public boolean agregarMomia(int vida, int velocidad, int fuerza,
                                int resistencia, int longitudVendajes)
    {
        if (!rango(vida, 1, 200) || !rango(velocidad, 1, 100)
            || !rango(fuerza, 1, 100) || !rango(resistencia, 1, 100)
            || !rango(longitudVendajes, 1, 300))
            return false;

        monstruos.add(new Momia(vida, velocidad, fuerza, resistencia,
                                longitudVendajes));
        return true;
    }

    public boolean agregarHombreLobo(int vida, int velocidad, int fuerza,
                                     int resistencia, int ferocidad,
                                     int faseLunar)
    {
        if (!rango(vida, 1, 200) || !rango(velocidad, 1, 100)
            || !rango(fuerza, 1, 100) || !rango(resistencia, 1, 100)
            || !rango(ferocidad, 1, 100) || !rango(faseLunar, 1, 4))
            return false;

        monstruos.add(new HombreLobo(vida, velocidad, fuerza, resistencia,
                                     ferocidad, faseLunar));
        return true;
    }

    public boolean agregarJugador(String nombre, int record)
    {
        if (!nombreValido(nombre) || record < 0)
            return false;

        jugadores.add(new Jugador(nombre, record));
        return true;
    }

    /**
     * Inicia una sesión para el jugador indicado. Si no existe, lo crea con
     * récord cero. Reinicia historial, puntaje y monstruos disponibles.
     *
     * @param nombre nombre del jugador
     * @return {@code true} si el nombre es válido
     */
    public boolean iniciarSesion(String nombre)
    {
        if (!nombreValido(nombre))
            return false;

        jugadorActual = buscarJugador(nombre);
        jugadorNuevo = jugadorActual == null;

        if (jugadorNuevo)
            jugadorActual = new Jugador(nombre, 0);

        historial = new LinkedList<Resultado>();
        monstruosDisponibles = new ArrayList<Monstruo>(monstruos);
        puntajeSesion = 0;
        return true;
    }

    /**
     * Cierra la sesión, actualiza el récord si corresponde y descarta al
     * jugador actual.
     *
     * @return uno de {@link #SIN_PARTIDAS}, {@link #JUGADOR_NUEVO},
     *         {@link #RECORD_SUPERADO} o {@link #RECORD_MANTENIDO}
     */
    public int cerrarSesion()
    {
        int estado = SIN_PARTIDAS;

        if (!historial.isEmpty())
        {
            if (jugadorNuevo)
            {
                jugadorActual.setRecord(puntajeSesion);
                jugadores.add(jugadorActual);
                estado = JUGADOR_NUEVO;
            }
            else if (puntajeSesion > jugadorActual.getRecord())
            {
                jugadorActual.setRecord(puntajeSesion);
                estado = RECORD_SUPERADO;
            }
            else
            {
                estado = RECORD_MANTENIDO;
            }
        }

        jugadorActual = null;
        jugadorNuevo = false;
        return estado;
    }

    public ArrayList<Monstruo> getMonstruos()
    {
        return monstruos;
    }

    public ArrayList<Jugador> getJugadores()
    {
        return jugadores;
    }

    public ArrayList<Minijuego> getMinijuegos()
    {
        return minijuegos;
    }

    public LinkedList<Resultado> getHistorial()
    {
        return historial;
    }

    /**
     * Entrega los monstruos que todavía no se usaron en la sesión.
     *
     * @return copia de la lista, modificarla no altera el sistema
     */
    public ArrayList<Monstruo> getMonstruosDisponibles()
    {
        return new ArrayList<Monstruo>(monstruosDisponibles);
    }

    public String getNombreJugador()
    {
        return jugadorActual.getNombre();
    }

    public int getRecordJugador()
    {
        return jugadorActual.getRecord();
    }

    public boolean esJugadorNuevo()
    {
        return jugadorNuevo;
    }

    public int getPuntajeSesion()
    {
        return puntajeSesion;
    }

    public boolean jugoAlgunMinijuego()
    {
        return !historial.isEmpty();
    }

    public boolean minijuegoJugado(Minijuego minijuego)
    {
        for (Resultado resultado : historial)
        {
            if (resultado.getMinijuego() == minijuego)
                return true;
        }
        return false;
    }

    /**
     * Busca el puntaje de un minijuego en el historial de la sesión.
     *
     * @param minijuego minijuego buscado
     * @return puntaje obtenido, o {@code -1} si no se jugó
     */
    public int getPuntajeMinijuego(Minijuego minijuego)
    {
        for (Resultado resultado : historial)
        {
            if (resultado.getMinijuego() == minijuego)
                return resultado.getPuntaje();
        }
        return -1;
    }

    public boolean monstruoDisponible(Monstruo monstruo)
    {
        return monstruosDisponibles.contains(monstruo);
    }

    /**
     * Calcula el potencial de un monstruo en un minijuego.
     *
     * @param minijuego minijuego en el que se compite
     * @param monstruo monstruo que compite
     * @return potencial calculado por el minijuego
     */
    public double calcularPotencial(Minijuego minijuego, Monstruo monstruo)
    {
        return monstruo.aceptar(minijuego);
    }

    /**
     * Registra una jugada y acumula su puntaje. Aplica el multiplicador 1.5
     * cuando la habilidad elegida es la premiada por el minijuego.
     *
     * @param minijuego minijuego jugado
     * @param monstruo monstruo usado
     * @param habilidad habilidad elegida, o {@code null} si no se usó
     * @return puntaje obtenido, o {@code -1} si la jugada no es válida
     */
    public int registrarJugada(Minijuego minijuego, Monstruo monstruo,
                               String habilidad)
    {
        if (minijuego == null || monstruo == null
            || minijuegoJugado(minijuego)
            || !monstruoDisponible(monstruo))
            return -1;

        double multiplicador = 1.0;
        if (habilidad != null
            && habilidad.equals(minijuego.habilidadPremiada()))
            multiplicador = 1.5;

        double potencial = calcularPotencial(minijuego, monstruo);
        int puntaje = (int) Math.round(potencial * 10 * multiplicador);

        historial.add(new Resultado(minijuego, monstruo, habilidad,
                                    puntaje));
        monstruosDisponibles.remove(monstruo);
        puntajeSesion += puntaje;
        return puntaje;
    }

    /**
     * Arma el ranking sin modificar la lista original.
     *
     * @return jugadores por récord descendente y, en empate, por nombre
     *         ascendente
     */
    public ArrayList<Jugador> getRanking()
    {
        ArrayList<Jugador> ranking = new ArrayList<Jugador>(jugadores);

        for (int i = 1; i < ranking.size(); i++)
        {
            Jugador actual = ranking.get(i);
            int j = i - 1;

            while (j >= 0 && vaAntes(actual, ranking.get(j)))
            {
                ranking.set(j + 1, ranking.get(j));
                j--;
            }
            ranking.set(j + 1, actual);
        }

        return ranking;
    }

    private boolean vaAntes(Jugador primero, Jugador segundo)
    {
        if (primero.getRecord() != segundo.getRecord())
            return primero.getRecord() > segundo.getRecord();

        return sinTildes(primero.getNombre()).compareToIgnoreCase(
            sinTildes(segundo.getNombre())) < 0;
    }

    private String sinTildes(String texto)
    {
        String conTilde = "áéíóúüñÁÉÍÓÚÜÑ";
        String sinTilde = "aeiouunAEIOUUN";
        String normal = "";

        for (int i = 0; i < texto.length(); i++)
        {
            char letra = texto.charAt(i);
            int posicion = conTilde.indexOf(letra);

            if (posicion >= 0)
                normal += sinTilde.charAt(posicion);
            else
                normal += letra;
        }

        return normal;
    }

    private Jugador buscarJugador(String nombre)
    {
        for (Jugador jugador : jugadores)
        {
            if (jugador.getNombre().equals(nombre))
                return jugador;
        }
        return null;
    }

    private boolean nombreValido(String nombre)
    {
        return nombre != null && !nombre.isEmpty()
            && nombre.indexOf(';') < 0;
    }

    private boolean rango(int valor, int minimo, int maximo)
    {
        return valor >= minimo && valor <= maximo;
    }
}
