# TASkOcupado Core

**TASkOcupado Core** es el núcleo de un sistema de gestión y asignación de tareas extensible, escrito en Java. Permite organizar un conjunto de tareas y personas, asignar tareas a integrantes de un equipo y notificar automáticamente a observadores externos (correo electrónico, Telegram, etc.) mediante un sistema de plugins.

---

## ¿Qué hace?

1. **Carga tareas y personas** desde una fuente de datos configurable (implementada como plugin `ContentLoader`).
2. **Asigna tareas a personas**: registra qué persona tiene asignada cada tarea.
3. **Notifica a los observadores** registrados cada vez que se realiza una asignación, enviando el nombre de la tarea, el nombre de la persona y la marca de tiempo del evento.
4. **Descubre plugins de notificación automáticamente**: escanea un directorio de extensiones buscando JARs que implementen la interfaz `Observer` y los instancia en tiempo de ejecución.

---

## Arquitectura

El sistema se basa en tres pilares:

### 1. Modelo de dominio

| Clase | Descripción |
|-------|-------------|
| `Task` | Representa una tarea con su descripción. |
| `Person` | Representa una persona (integrante del equipo). |
| `TaskAssigner` | Mantiene el mapa de asignaciones `Task → Set<Person>`. |
| `TASkOcupado` | Clase principal observable; gestiona las colecciones de tareas y personas, delega las asignaciones a `TaskAssigner` y notifica a los observadores registrados. |

### 2. Patrón Observer

```
Observable (interfaz)
    └── TASkOcupado
            ├── addObserver(Observer)
            ├── removeObserver(Observer)
            └── notifyObservers(Object event)   ← se dispara en cada assignTask()

Observer (interfaz)
    └── update(Object event)   ← implementado por cada plugin de notificación
```

Cuando se llama a `TASkOcupado.assignTask(task, person)`, el sistema construye un mapa con los campos `Task`, `Name` y `Time`, y lo envía a cada `Observer` registrado.

### 3. Sistema de plugins

El proyecto distingue dos tipos de plugins:

#### ContentLoader
Responsable de proveer los conjuntos de `Task` y `Person` al sistema. La implementación concreta se especifica en el archivo de configuración `config.properties`. Ejemplo:

```properties
core.ContentLoader=com.ejemplo.MiContentLoader
```

#### Observer (extensiones)
Implementaciones de notificación (e.g., bot de Telegram, correo electrónico). Se colocan como JARs en el directorio `~/.TASkOcupado/Extensions/`. El `Discoverer` los carga automáticamente al iniciar la aplicación sin necesidad de modificar el código fuente.

---

## Estructura del proyecto

```
TASkOcupadoCore/
├── src/
│   ├── core/
│   │   ├── TASkOcupado.java         # Sistema principal (Observable)
│   │   ├── TASkOcupadoFactory.java  # Factory: crea y configura la instancia principal
│   │   ├── ContentLoader.java       # Interfaz para cargar tareas y personas
│   │   ├── TaskAssigner.java        # Lógica de asignación de tareas
│   │   ├── Task.java                # Entidad Tarea
│   │   ├── Person.java              # Entidad Persona
│   │   └── Settings.java            # Rutas de configuración y extensiones
│   ├── main/
│   │   └── Main.java                # Punto de entrada de la aplicación
│   ├── observer/
│   │   ├── Observable.java          # Interfaz Observable
│   │   └── Observer.java            # Interfaz Observer
│   └── tools/
│       ├── Discoverer.java          # Escanea JARs en Extensions/ e instancia Observers
│       └── PluginFactory.java       # Instancia plugins por nombre de clase desde properties
├── lib/                             # Dependencias externas (JARs)
├── build.xml                        # Script de construcción con Apache Ant
└── manifest.mf                      # Manifiesto del JAR de salida
```

---

## Configuración

La aplicación busca su configuración en el directorio del usuario:

| Ruta | Descripción |
|------|-------------|
| `~/.TASkOcupado/Resources/config.properties` | Archivo de propiedades principal |
| `~/.TASkOcupado/Extensions/` | Directorio donde se colocan los JARs de extensiones (Observers) |

### Ejemplo de `config.properties`

```properties
# Implementación del cargador de contenido (tareas y personas)
core.ContentLoader=com.ejemplo.MiContentLoader
```

---

## Dependencias

| Biblioteca | Versión | Uso |
|------------|---------|-----|
| [Telegram Bots](https://github.com/rubenlagus/TelegramBots) | 6.7.0 | Notificaciones vía Telegram (plugin) |
| [Gson](https://github.com/google/gson) | 2.11.0 | Serialización/deserialización JSON |
| Jakarta Mail | 2.0.3 | Notificaciones vía correo electrónico (plugin) |
| Jakarta Activation API | 2.1.3 | Soporte para adjuntos en correos |

---

## Compilación

El proyecto usa [Apache Ant](https://ant.apache.org/). Para compilar y generar el JAR:

```bash
ant clean build
```

El artefacto resultante se genera en `dist/TASkOcupadoCore.jar`.

---

## Ejecución

```bash
java -cp "dist/TASkOcupadoCore.jar:lib/*" main.Main
```

Al ejecutarse, la aplicación:
1. Lee `~/.TASkOcupado/Resources/config.properties` para instanciar el `ContentLoader` configurado.
2. Carga las tareas y personas desde dicho loader.
3. Escanea `~/.TASkOcupado/Extensions/` y registra todos los `Observer` encontrados.
4. Imprime por consola las tareas y personas cargadas.

---

## Extensibilidad

Para agregar una nueva forma de notificación:

1. Crear un proyecto Java que implemente la interfaz `observer.Observer`:
   ```java
   public class MiObserver implements Observer {
       @Override
       public void update(Object event) {
           // event es un Map<String, String> con claves "Task", "Name" y "Time"
       }
   }
   ```
2. Empaquetar el proyecto como JAR.
3. Copiar el JAR en `~/.TASkOcupado/Extensions/`.

El sistema lo detectará y registrará automáticamente en el próximo inicio, sin necesidad de recompilar el núcleo.

---

## Diagrama de flujo

```
TASkOcupadoFactory
    │
    ├── PluginFactory  ──► config.properties ──► instancia ContentLoader
    │       └── ContentLoader.loadSetOf(Task)   → Set<Task>
    │       └── ContentLoader.loadSetOf(Person) → Set<Person>
    │
    ├── Discoverer ──► ~/.TASkOcupado/Extensions/*.jar ──► Set<Observer>
    │
    └── TASkOcupado(tasks, people)
            └── addObserver(observer) × N
                    │
                    └── assignTask(task, person)
                            ├── TaskAssigner.assignTask(task, person)
                            └── notifyObservers({ Task, Name, Time })
                                    └── Observer.update(event) × N
```
