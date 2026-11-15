# Taller 03 — Spooky Season

Simulación de minijuegos de Halloween en Java. Cada jugador se transforma en
un monstruo (zombi, vampiro, momia u hombre lobo) para competir por el mejor
puntaje en cuatro desafíos.

El proyecto usa herencia, interfaces, colecciones y el patrón Visitor, con una
separación entre `Main` (entrada y salida) y `Sistema` (lógica y datos).

## Requisitos

- JDK 21 o superior.
- Eclipse (opcional) o consola.

## Estructura

```
src/        Clases del programa (paquete por defecto)
txt/        Archivos de entrada y registros
```

- `txt/Monstruos.txt`: catálogo de monstruos. Cada línea es un monstruo.
- `txt/Registros.txt`: récord de cada jugador. Se crea si no existe y se
  reescribe al terminar una sesión.

## Ejecución

El programa usa rutas relativas `txt/...`, por lo que **debe ejecutarse desde
la raíz del proyecto**.

### Consola

```bash
javac -d bin src/*.java
java -cp bin Main
```

### Eclipse

1. `File > Import > General > Existing Projects into Workspace`.
2. Seleccionar la carpeta del proyecto.
3. Ejecutar `Main.java` con `Run As > Java Application`.

## Formato de los archivos

`Monstruos.txt`:

```txt
Tipo;Vida;Velocidad;Fuerza;Resistencia;...
```

| Tipo | Campos adicionales |
|---|---|
| Zombi | `FuerzaDeMordida` |
| Vampiro | `VelocidadDeVuelo;ResistenciaAlSol` |
| Momia | `LongitudVendajes` |
| HombreLobo | `Ferocidad;FaseLunar` |

`Registros.txt`:

```txt
Jugador;PuntajeMaximo
```

Las líneas inválidas (tipo desconocido, campos faltantes o sobrantes, valores
no numéricos o fuera de rango) se descartan informando el número de línea.

## Uso

1. Ingresar un nombre de usuario (no vacío y sin `;`).
2. Elegir una opción del menú principal.
3. Al jugar un minijuego: elegir minijuego, monstruo y, opcionalmente, una
   habilidad. Cada minijuego y cada monstruo se pueden usar una sola vez por
   sesión.
4. Terminar sesión o salir: se guarda el puntaje si el récord fue superado.

## Funcionalidades

- Lectura de monstruos y jugadores desde archivo, con validación.
- Inicio de sesión para jugadores nuevos y existentes.
- Cuatro minijuegos con potencial calculado por Visitor.
- Habilidades mediante interfaces (`Mordedor`, `Volador`, `Regenerable`,
  `Furioso`) y multiplicador x1.5 cuando la habilidad es la premiada.
- Catálogo de monstruos con atributos y estado de uso.
- Historial de la sesión en `LinkedList`.
- Ranking ordenado manualmente por récord y, en empate, por nombre.
- Persistencia del récord en `Registros.txt`.
- Control de errores: entradas inválidas, archivos faltantes o corruptos e
  índices fuera de rango no detienen el programa.
