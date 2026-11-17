//Lucas Ramírez - 22.325.313-K - ITI

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Punto de entrada del taller. Lee los archivos, muestra los menús y
 * escribe el récord al terminar.
 */
public class Main
{
    private static final String RUTA_MONSTRUOS = "txt/Monstruos.txt";
    private static final String RUTA_REGISTROS = "txt/Registros.txt";

    private Scanner teclado;
    private Sistema sistema;
    private boolean finEntrada;

    /**
     * Crea la aplicación y ejecuta el ciclo principal.
     *
     * @param args argumentos de línea de comandos, no se usan
     */
    public static void main(String[] args)
    {
        Main aplicacion = new Main();
        aplicacion.ejecutar();
    }

    /**
     * Carga los datos, recorre las sesiones y cierra la entrada al salir.
     */
    public void ejecutar()
    {
        teclado = new Scanner(System.in);
        sistema = new Sistema();

        System.out.println("Cargando archivos...");
        cargarMonstruos();
        cargarJugadores();

        if (sistema.getMonstruos().isEmpty())
        {
            System.out.println("[!] No hay monstruos válidos. "
                + "El programa terminará.");
            teclado.close();
            return;
        }

        System.out.println();
        System.out.println("=========================================");
        System.out.println("             SPOOKY SEASON");
        System.out.println("=========================================");

        boolean salir = false;
        while (!salir && !finEntrada)
        {
            if (!iniciarSesion())
                break;

            salir = menuPrincipal();
        }

        teclado.close();
    }

    private void cargarMonstruos()
    {
        File archivo = new File(RUTA_MONSTRUOS);

        try
        {
            Scanner lector = new Scanner(archivo);
            int numeroLinea = 0;

            while (lector.hasNextLine())
            {
                numeroLinea++;
                String linea = lector.nextLine().trim();

                if (!agregarMonstruo(linea))
                {
                    System.out.println("[!] Línea " + numeroLinea
                        + " descartada: " + linea);
                }
            }

            lector.close();
            System.out.println(" > " + sistema.getMonstruos().size()
                + " monstruos cargados desde " + RUTA_MONSTRUOS);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[!] No se encontró " + RUTA_MONSTRUOS);
        }
    }

    private boolean agregarMonstruo(String linea)
    {
        String[] campos = linea.split(";", -1);

        for (int i = 0; i < campos.length; i++)
            campos[i] = campos[i].trim();

        if (campos.length < 2)
            return false;

        String tipo = campos[0];

        try
        {
            if (tipo.equals("Zombi"))
            {
                if (campos.length != 6)
                    return false;

                return sistema.agregarZombi(
                    Integer.valueOf(campos[1]),
                    Integer.valueOf(campos[2]),
                    Integer.valueOf(campos[3]),
                    Integer.valueOf(campos[4]),
                    Integer.valueOf(campos[5]));
            }
            else if (tipo.equals("Vampiro"))
            {
                if (campos.length != 7)
                    return false;

                return sistema.agregarVampiro(
                    Integer.valueOf(campos[1]),
                    Integer.valueOf(campos[2]),
                    Integer.valueOf(campos[3]),
                    Integer.valueOf(campos[4]),
                    Integer.valueOf(campos[5]),
                    Integer.valueOf(campos[6]));
            }
            else if (tipo.equals("Momia"))
            {
                if (campos.length != 6)
                    return false;

                return sistema.agregarMomia(
                    Integer.valueOf(campos[1]),
                    Integer.valueOf(campos[2]),
                    Integer.valueOf(campos[3]),
                    Integer.valueOf(campos[4]),
                    Integer.valueOf(campos[5]));
            }
            else if (tipo.equals("HombreLobo"))
            {
                if (campos.length != 7)
                    return false;

                return sistema.agregarHombreLobo(
                    Integer.valueOf(campos[1]),
                    Integer.valueOf(campos[2]),
                    Integer.valueOf(campos[3]),
                    Integer.valueOf(campos[4]),
                    Integer.valueOf(campos[5]),
                    Integer.valueOf(campos[6]));
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return false;
    }

    private void cargarJugadores()
    {
        File archivo = new File(RUTA_REGISTROS);

        if (!archivo.exists())
        {
            System.out.println(" > 0 jugadores cargados (no existe "
                + RUTA_REGISTROS + ")");
            return;
        }

        try
        {
            Scanner lector = new Scanner(archivo);
            int numeroLinea = 0;

            while (lector.hasNextLine())
            {
                numeroLinea++;
                String linea = lector.nextLine().trim();

                if (!agregarJugador(linea))
                {
                    System.out.println("[!] Línea " + numeroLinea
                        + " descartada: " + linea);
                }
            }

            lector.close();
            System.out.println(" > " + sistema.getJugadores().size()
                + " jugadores cargados desde " + RUTA_REGISTROS);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[!] No se pudo leer " + RUTA_REGISTROS);
        }
    }

    private boolean agregarJugador(String linea)
    {
        String[] campos = linea.split(";", -1);

        if (campos.length != 2)
            return false;

        try
        {
            int record = Integer.valueOf(campos[1].trim());
            return sistema.agregarJugador(campos[0].trim(), record);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    private boolean iniciarSesion()
    {
        while (!finEntrada)
        {
            System.out.println();
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = leerLinea();

            if (finEntrada)
                return false;

            if (sistema.iniciarSesion(nombre))
            {
                mostrarBienvenida();
                return true;
            }

            System.out.println("[!] El nombre no puede estar vacío "
                + "ni contener ';'.");
        }

        return false;
    }

    private void mostrarBienvenida()
    {
        System.out.println();

        if (sistema.esJugadorNuevo())
        {
            System.out.println("¡Bienvenido, "
                + sistema.getNombreJugador()
                + "! Es tu primera vez jugando.");
        }
        else
        {
            System.out.println("¡Bienvenido de vuelta, "
                + sistema.getNombreJugador()
                + "! Tu récord actual es de "
                + sistema.getRecordJugador() + " puntos.");
        }
    }

    private boolean menuPrincipal()
    {
        while (true)
        {
            System.out.println();
            System.out.println("------ MENÚ PRINCIPAL ("
                + sistema.getNombreJugador() + ") ------");
            System.out.println("1) Jugar un minijuego");
            System.out.println("2) Ver catálogo de monstruos");
            System.out.println("3) Ver historial de la sesión");
            System.out.println("4) Ver ranking de jugadores");
            System.out.println("5) Terminar sesión");
            System.out.println("6) Salir");

            int opcion = leerOpcion(1, 6);

            if (opcion == 1)
                jugarMinijuego();
            else if (opcion == 2)
                mostrarCatalogo();
            else if (opcion == 3)
                mostrarHistorial();
            else if (opcion == 4)
                mostrarRanking();
            else if (opcion == 5)
            {
                terminarSesion();
                return false;
            }
            else
            {
                terminarSesion();
                return true;
            }
        }
    }

    private void jugarMinijuego()
    {
        Minijuego elegido = null;

        while (elegido == null)
        {
            System.out.println();
            System.out.println("------ MINIJUEGOS ------");
            int numero = 1;

            for (Minijuego minijuego : sistema.getMinijuegos())
            {
                String estado;

                if (sistema.minijuegoJugado(minijuego))
                {
                    estado = "[JUGADO - "
                        + sistema.getPuntajeMinijuego(minijuego)
                        + " pts]";
                }
                else
                {
                    estado = "[PENDIENTE]";
                }

                System.out.println(numero + ") " + minijuego.nombre()
                    + "   " + estado);
                numero++;
            }

            System.out.println("0) Volver");
            int opcion = leerOpcion(0, sistema.getMinijuegos().size());

            if (opcion == 0)
                return;

            Minijuego minijuego =
                sistema.getMinijuegos().get(opcion - 1);

            if (sistema.minijuegoJugado(minijuego))
            {
                System.out.println("[!] Ya jugaste "
                    + minijuego.nombre() + " en esta sesión.");
            }
            else
            {
                elegido = minijuego;
            }
        }

        System.out.println();
        System.out.println(elegido.nombre() + ": "
            + elegido.descripcion());

        if (sistema.getMonstruosDisponibles().isEmpty())
        {
            System.out.println("[!] No quedan monstruos disponibles.");
            return;
        }

        System.out.println();
        System.out.println("Elige el monstruo en el que te transformarás:");

        int numeroMonstruo = 1;

        for (Monstruo monstruo : sistema.getMonstruosDisponibles())
        {
            System.out.println(numeroMonstruo + ") " + monstruo);
            numeroMonstruo++;
        }

        System.out.println("0) Volver");
        int opcionMonstruo =
            leerOpcion(0, sistema.getMonstruosDisponibles().size());

        if (opcionMonstruo == 0)
            return;

        Monstruo monstruo =
            sistema.getMonstruosDisponibles().get(opcionMonstruo - 1);

        System.out.println();
        System.out.println("Te comes un dulce de dudosa procedencia...");
        System.out.println("Potencial para " + elegido.nombre() + ": "
            + dosDecimales(sistema.calcularPotencial(elegido, monstruo)));

        String habilidad = elegirHabilidad(monstruo);

        if (habilidad != null)
        {
            if (habilidad.equals(elegido.habilidadPremiada()))
            {
                System.out.println("¡La habilidad fue efectiva! "
                    + "Puntaje x1.5");
            }
            else
            {
                System.out.println("La habilidad no tuvo efecto "
                    + "en este minijuego.");
            }
        }

        int puntaje = sistema.registrarJugada(elegido, monstruo,
            habilidad);

        System.out.println();
        System.out.println(">> Puntaje obtenido: " + puntaje + " puntos");
    }

    private String elegirHabilidad(Monstruo monstruo)
    {
        String[] nombres = new String[2];
        int cantidad = 0;

        if (monstruo instanceof Mordedor)
            nombres[cantidad++] = "Morder";
        if (monstruo instanceof Volador)
            nombres[cantidad++] = "Volar";
        if (monstruo instanceof Regenerable)
            nombres[cantidad++] = "Regenerarse";
        if (monstruo instanceof Furioso)
            nombres[cantidad++] = "Enfurecerse";

        System.out.println();
        System.out.println("¿Quieres usar una habilidad?");

        for (int i = 0; i < cantidad; i++)
            System.out.println((i + 1) + ") " + nombres[i]);

        System.out.println("0) No usar habilidad");
        int opcion = leerOpcion(0, cantidad);

        if (opcion == 0)
            return null;

        String habilidad = nombres[opcion - 1];

        if (habilidad.equals("Morder"))
            System.out.println(((Mordedor) monstruo).morder());
        else if (habilidad.equals("Volar"))
            System.out.println(((Volador) monstruo).volar());
        else if (habilidad.equals("Regenerarse"))
            System.out.println(((Regenerable) monstruo).regenerarse());
        else
            System.out.println(((Furioso) monstruo).enfurecerse());

        return habilidad;
    }

    private void mostrarCatalogo()
    {
        System.out.println();
        System.out.println("------ CATÁLOGO DE MONSTRUOS ------");
        int numero = 1;

        for (Monstruo monstruo : sistema.getMonstruos())
        {
            String estado;

            if (sistema.monstruoDisponible(monstruo))
                estado = "";
            else
                estado = " [USADO]";

            System.out.println(numero + ") " + monstruo + estado);
            System.out.println("   Habilidades: "
                + habilidadesDe(monstruo));
            numero++;
        }
    }

    private String habilidadesDe(Monstruo monstruo)
    {
        String texto = "";

        if (monstruo instanceof Mordedor)
            texto = agregarHabilidad(texto, "Morder");
        if (monstruo instanceof Volador)
            texto = agregarHabilidad(texto, "Volar");
        if (monstruo instanceof Regenerable)
            texto = agregarHabilidad(texto, "Regenerarse");
        if (monstruo instanceof Furioso)
            texto = agregarHabilidad(texto, "Enfurecerse");

        return texto;
    }

    private String agregarHabilidad(String texto, String nombre)
    {
        if (texto.isEmpty())
            return nombre;

        return texto + ", " + nombre;
    }

    private void mostrarHistorial()
    {
        System.out.println();
        System.out.println("------ HISTORIAL DE LA SESIÓN ------");
        if (sistema.getHistorial().isEmpty())
        {
            System.out.println("Todavía no has jugado ningún minijuego.");
            return;
        }

        int numero = 1;

        for (Resultado resultado : sistema.getHistorial())
        {
            String habilidad = "Ninguna";

            if (resultado.getHabilidad() != null)
                habilidad = resultado.getHabilidad();

            System.out.println(numero + ". "
                + resultado.getMinijuego().nombre() + " | "
                + resultado.getMonstruo().getTipo()
                + " | Habilidad: " + habilidad + " | "
                + resultado.getPuntaje() + " pts");
            numero++;
        }

        System.out.println("Puntaje acumulado: "
            + sistema.getPuntajeSesion() + " pts");
    }

    private void mostrarRanking()
    {
        System.out.println();
        System.out.println("------ RANKING ------");
        if (sistema.getRanking().isEmpty())
        {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        int puesto = 1;

        for (Jugador jugador : sistema.getRanking())
        {
            System.out.println(puesto + ". " + jugador.getNombre()
                + "   " + jugador.getRecord());
            puesto++;
        }
    }

    private void terminarSesion()
    {
        int recordAnterior = sistema.getRecordJugador();
        int puntaje = sistema.getPuntajeSesion();
        int estado = sistema.cerrarSesion();

        System.out.println();
        System.out.println("Sesión terminada. Puntaje total: "
            + puntaje + " puntos.");

        if (estado == Sistema.SIN_PARTIDAS)
        {
            System.out.println("No jugaste ningún minijuego, "
                + "no se guardó registro.");
            return;
        }

        if (estado == Sistema.JUGADOR_NUEVO)
        {
            System.out.println("¡Registro creado con " + puntaje
                + " puntos!");
        }
        else if (estado == Sistema.RECORD_SUPERADO)
        {
            System.out.println("¡Nuevo récord! Antes tenías "
                + recordAnterior + " puntos.");
        }
        else
        {
            System.out.println("No superaste tu récord ("
                + recordAnterior + "). ¡Suerte para la próxima!");
        }

        guardarRegistros();
    }

    private void guardarRegistros()
    {
        try
        {
            BufferedWriter escritor =
                new BufferedWriter(new FileWriter(RUTA_REGISTROS));

            for (Jugador jugador : sistema.getJugadores())
            {
                escritor.write(jugador.getNombre() + ";"
                    + jugador.getRecord());
                escritor.newLine();
            }

            escritor.close();
            System.out.println("Registros guardados en "
                + RUTA_REGISTROS + ".");
        }
        catch (IOException e)
        {
            System.out.println("[!] No se pudo guardar "
                + RUTA_REGISTROS + ".");
        }
    }

    private String leerLinea()
    {
        if (!teclado.hasNextLine())
        {
            finEntrada = true;
            return "";
        }

        return teclado.nextLine().trim();
    }

    private int leerOpcion(int minimo, int maximo)
    {
        while (true)
        {
            System.out.print("> ");
            String entrada = leerLinea();

            if (finEntrada)
                return 0;

            int opcion;

            try
            {
                opcion = Integer.valueOf(entrada);
            }
            catch (NumberFormatException e)
            {
                System.out.println("[!] Debe ingresar un número.");
                continue;
            }

            if (opcion < minimo || opcion > maximo)
            {
                System.out.println("[!] Opción fuera de rango. "
                    + "Intente nuevamente.");
                continue;
            }

            return opcion;
        }
    }

    private String dosDecimales(double valor)
    {
        long centesimas = Math.round(valor * 100);
        long parteEntera = centesimas / 100;
        long resto = centesimas % 100;

        return parteEntera + "."
            + (resto < 10 ? "0" : "") + resto;
    }
}
