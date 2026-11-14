import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema
{
    public static final int SIN_PARTIDAS = 0;
    public static final int JUGADOR_NUEVO = 1;
    public static final int RECORD_SUPERADO = 2;
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

    public double calcularPotencial(Minijuego minijuego, Monstruo monstruo)
    {
        return monstruo.aceptar(minijuego);
    }

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

        return primero.getNombre().compareTo(segundo.getNombre()) < 0;
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
