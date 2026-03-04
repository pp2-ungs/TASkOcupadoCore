# TASkOcupado Core

**TASkOcupado Core** is the heart of an extensible task management and assignment system, written in Java. It allows you to organize a set of tasks and people, assign tasks to team members, and automatically notify external observers (e-mail, Telegram, etc.) through a plugin-based architecture.

© 2024 [Ebertz](https://github.com/xebertz), [López](https://github.com/Gonza-JL), [Rondelli](https://github.com/rondelli)

---

## What does it do?

1. **Loads tasks and people** from a configurable data source (implemented as a `ContentLoader` plugin).
2. **Assigns tasks to people**: records which person is responsible for each task.
3. **Notifies registered observers** every time an assignment is made, sending the task name, the person's name, and a timestamp.
4. **Auto-discovers notification plugins**: scans an extensions directory for JARs that implement the `Observer` interface and instantiates them at runtime.

---

## Architecture

The system is built on three pillars:

### 1. Domain model

| Class | Description |
|-------|-------------|
| `Task` | Represents a task with a description. |
| `Person` | Represents a team member. |
| `TaskAssigner` | Maintains the assignment map `Task → Set<Person>`. |
| `TASkOcupado` | Main observable class; manages the task and people collections, delegates assignments to `TaskAssigner`, and notifies registered observers. |

### 2. Observer pattern

```
Observable (interface)
    └── TASkOcupado
            ├── addObserver(Observer)
            ├── removeObserver(Observer)
            └── notifyObservers(Object event)   ← fired on every assignTask()

Observer (interface)
    └── update(Object event)   ← implemented by each notification plugin
```

When `TASkOcupado.assignTask(task, person)` is called, the system builds a map with the fields `Task`, `Name`, and `Time`, and sends it to every registered `Observer`.

### 3. Plugin system

The project supports two kinds of plugins:

#### ContentLoader
Responsible for supplying the sets of `Task` and `Person` objects to the system. The concrete implementation is specified in the `config.properties` configuration file. Example:

```properties
core.ContentLoader=com.example.MyContentLoader
```

#### Observer (extensions)
Notification implementations (e.g., Telegram bot, e-mail). They are placed as JARs in the `~/.TASkOcupado/Extensions/` directory. The `Discoverer` loads them automatically at startup without any changes to the core source code.

---

## Project structure

```
TASkOcupadoCore/
├── src/
│   ├── core/
│   │   ├── TASkOcupado.java         # Main system class (Observable)
│   │   ├── TASkOcupadoFactory.java  # Factory: builds and wires the main instance
│   │   ├── ContentLoader.java       # Interface for loading tasks and people
│   │   ├── TaskAssigner.java        # Task assignment logic
│   │   ├── Task.java                # Task entity
│   │   ├── Person.java              # Person entity
│   │   └── Settings.java            # Configuration and extension paths
│   ├── main/
│   │   └── Main.java                # Application entry point
│   ├── observer/
│   │   ├── Observable.java          # Observable interface
│   │   └── Observer.java            # Observer interface
│   └── tools/
│       ├── Discoverer.java          # Scans JARs in Extensions/ and instantiates Observers
│       └── PluginFactory.java       # Instantiates plugins by class name from properties
├── lib/                             # External dependencies (JARs)
├── build.xml                        # Apache Ant build script
└── manifest.mf                      # Output JAR manifest
```

---

## Configuration

The application looks for its configuration under the user's home directory:

| Path | Description |
|------|-------------|
| `~/.TASkOcupado/Resources/config.properties` | Main properties file |
| `~/.TASkOcupado/Extensions/` | Directory where extension JARs (Observers) are placed |

### `config.properties` example

```properties
# Content loader implementation (tasks and people)
core.ContentLoader=com.example.MyContentLoader
```

---

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| [Telegram Bots](https://github.com/rubenlagus/TelegramBots) | 6.7.0 | Telegram notifications (plugin) |
| [Gson](https://github.com/google/gson) | 2.11.0 | JSON serialization/deserialization |
| Jakarta Mail | 2.0.3 | E-mail notifications (plugin) |
| Jakarta Activation API | 2.1.3 | E-mail attachment support |

---

## Build

The project uses [Apache Ant](https://ant.apache.org/). To compile and generate the JAR:

```bash
ant clean build
```

The output artifact is produced at `dist/TASkOcupadoCore.jar`.

---

## Run

```bash
java -cp "dist/TASkOcupadoCore.jar:lib/*" main.Main
```

On startup, the application:
1. Reads `~/.TASkOcupado/Resources/config.properties` and instantiates the configured `ContentLoader`.
2. Loads tasks and people through that loader.
3. Scans `~/.TASkOcupado/Extensions/` and registers all discovered `Observer` instances.
4. Prints the loaded tasks and people to the console.

---

## Extensibility

To add a new notification channel:

1. Create a Java project that implements the `observer.Observer` interface:
   ```java
   public class MyObserver implements Observer {
       @Override
       public void update(Object event) {
           // event is a Map<String, String> with keys "Task", "Name", and "Time"
       }
   }
   ```
2. Package the project as a JAR.
3. Copy the JAR into `~/.TASkOcupado/Extensions/`.

The system will detect and register it automatically on the next startup, with no need to recompile the core.

---

## Flow diagram

```
TASkOcupadoFactory
    │
    ├── PluginFactory  ──► config.properties ──► instantiates ContentLoader
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
