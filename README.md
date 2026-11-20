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

## Clonación y ejecución

```bash
git clone https://github.com/glospi/Taller03.git
cd Taller03
javac -d bin src/*.java
java -cp bin Main
```

Recordar que el programa debe ejecutarse desde la raíz del repositorio, porque
lee `txt/Monstruos.txt` y escribe `txt/Registros.txt` con rutas relativas.

## Pruebas manuales

| Prueba | Acción | Resultado esperado |
|---|---|---|
| Archivo inválido | Agregar a `Monstruos.txt` una línea incompleta | Se descarta indicando número de línea y contenido |
| Nombre inválido | Ingresar un nombre vacío o con `;` | Se rechaza y se pide otro nombre |
| Repetir minijuego | Jugar dos veces el mismo minijuego | Se avisa que ya se jugó y no se deja elegir |
| Repetir monstruo | Usar dos veces el mismo monstruo | Aparece `[USADO]` y no se deja elegir |
| Habilidad premiada | Elegir la habilidad que premia el minijuego | Se informa `Puntaje x1.5` |
| Otra habilidad | Elegir una habilidad distinta | Se informa que no tuvo efecto |
| Sin monstruos | Usar los monstruos disponibles | Se avisa que no quedan disponibles |
| Récord | Terminar sesión con más puntaje que el récord | Se guarda el nuevo récord en `Registros.txt` |

## Diagrama de clases

El diagrama UML está en [`diagrama-uml.pdf`](diagrama-uml.pdf): jerarquía de
`Monstruo`, interfaces de habilidad, patrón Visitor, minijuegos y el modelo de
sesión. El código fuente LaTeX del diagrama queda fuera del repositorio.

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
